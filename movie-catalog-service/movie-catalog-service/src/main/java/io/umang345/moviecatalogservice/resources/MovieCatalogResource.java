package io.umang345.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource 
{
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
	{ 
		
		//Get all rated movie IDS
		UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId
				, UserRating.class);
		
		return ratings.getUserRating().stream().map(rating -> 
		{
			//For each movie Id, call movie info service and get details
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			
			//Put them all together
			return new CatalogItem(movie.getName(),"Desc",rating.getRating());
		})
		.collect(Collectors.toList());
		 
	}

}















