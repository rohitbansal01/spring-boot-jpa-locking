package com.spring.jpa.locking.service;

import com.spring.jpa.locking.domain.Movie;

public interface MovieService {
	
	public void addMovie(Movie movie);

	public void updateRating(Movie movie);

	public Movie findByTitle(String title);

	public long count();

}
