package com.soumya.microservices.models;

public class Movie {
	
	private String movieId;
	private String name;
	
	
	public Movie() {
		
	}

	public Movie(String movieId2, String name) {
		super();
		this.movieId = movieId2;
		this.name = name;
	}
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
