package com.soumya.microservices.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soumya.microservices.models.CatelogItem;
import com.soumya.microservices.models.Rating;
import com.soumya.microservices.models.UserRating;
import com.soumya.microservices.services.MovieInfo;
import com.soumya.microservices.services.RatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private RatingInfo ratingInfo;
	
	
	@RequestMapping("/{userId}")
	public List<CatelogItem> getCatalog(@PathVariable("userId") String userId){

		// Get all rated movie IDs
		UserRating userRating = ratingInfo.getUserRating(userId);
		List<Rating> ratings = userRating.getUserRating();
		
		// For each movie ID call movie info service
		return ratings.stream()
				.map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());

	}
	

	
}
