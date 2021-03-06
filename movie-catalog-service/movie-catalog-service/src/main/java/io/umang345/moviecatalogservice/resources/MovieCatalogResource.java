package io.umang345.moviecatalogservice.resources;

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

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource 
{
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
	{ 
		
		//Get all rated movie IDS
		UserRating ratings = movieInfo.getUserRating(userId);
		
		return ratings.getUserRating().stream().map(rating -> 
		{
			return userRatingInfo.getCatalogItem(rating);
		})
		.collect(Collectors.toList());
		 
	}
}















