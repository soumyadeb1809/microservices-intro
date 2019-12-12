package com.soumya.microservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.soumya.microservices.models.CatelogItem;
import com.soumya.microservices.models.Movie;
import com.soumya.microservices.models.Rating;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	//@Autowired
	//private WebClient.Builder webClientBuilder;
	
	@HystrixCommand(
			fallbackMethod = "getFallbackCatalogItem",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
			}
	)
	public CatelogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		
		/*
		 Movie movie = webClientBuilder.build()
			.get()
			.uri("http://localhost:8082/movies/" + rating.getMovieId())
			.retrieve()
			.bodyToMono(Movie.class)
			.block();	// Block execution till the execution of Mono is completed 
		*/
		
		return new CatelogItem(movie.getName(), "Some fancy movie description.", rating.getRating());
	}
	
	public CatelogItem getFallbackCatalogItem(Rating rating) {
		return new CatelogItem("Movie nam not found", "", rating.getRating());
	}

}
