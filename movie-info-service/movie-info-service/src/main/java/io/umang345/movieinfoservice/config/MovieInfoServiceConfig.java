package io.umang345.movieinfoservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MovieInfoServiceConfig 
{
	@Bean
	public RestTemplate getRestTemplate()
	{
		 return new RestTemplate();
	}
}
