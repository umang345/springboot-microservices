package io.umang345.moviecatalogservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MovieCatalogServiceConfig 
{
	@Bean
	public RestTemplate getRestTemplate()
	{
		 return new RestTemplate();
	}
}
