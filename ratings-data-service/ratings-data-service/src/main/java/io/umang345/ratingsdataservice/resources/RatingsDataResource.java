package io.umang345.ratingsdataservice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.umang345.ratingsdataservice.models.Rating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource 
{
	@GetMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId)
	{
		 return new Rating(movieId,4);
	}

}
