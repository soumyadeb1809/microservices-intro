package com.soumya.microservices.models;

import java.util.List;

public class UserRating {
	
	private List<Rating> userRating;
	
	

	public UserRating() {
		
	}

	public UserRating(List<Rating> userRating) {
		super();
		this.userRating = userRating;
	}

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}
	
	

}
