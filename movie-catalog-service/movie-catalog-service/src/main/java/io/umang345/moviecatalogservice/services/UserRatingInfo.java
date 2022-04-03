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

public class  UserRatingInfo
{
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(String userId)
	{
		 return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId
					, UserRating.class);
	}
	
	public UserRating getFallbackUserRating(String userId)
	{
		 UserRating userRating = new UserRating();
		 userRating.setUserId(userId);
		 userRating.setUserRating(Arrays.asList(
			new Rating("0",0)	 
		 ));
		 return userRating;
	}
}
