package com.soumya.microservices.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.soumya.microservices.model.Movie;
import com.soumya.microservices.model.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.key}")
	private String apiKey;
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		
		MovieSummary summary = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey, 
				MovieSummary.class
		);
		
		return new Movie(movieId, summary.getTitle());
	}
}
