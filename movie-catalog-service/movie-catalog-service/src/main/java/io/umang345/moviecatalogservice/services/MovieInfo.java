package io.umang345.moviecatalogservice.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.umang345.moviecatalogservice.models.CatalogItem;
import io.umang345.moviecatalogservice.models.Movie;
import io.umang345.moviecatalogservice.models.Rating;
import io.umang345.moviecatalogservice.models.UserRating;

public class MovieInfo 
{
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating)
	{
		//For each movie Id, call movie info service and get details
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
		
		//Put them all together
		return new CatalogItem(movie.getName(),"Desc",rating.getRating());
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating)
	{
		return new CatalogItem("Movie name not found","",rating.getRating());
	}
}
