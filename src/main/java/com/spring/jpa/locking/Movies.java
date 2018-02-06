package com.spring.jpa.locking;

import com.spring.jpa.locking.domain.Movie;

public class Movies {

	public static MovieDTO of(Movie movie) {
		return new MovieDTO().setId(movie.getId()).setVersion(movie.getVersion()).setTitle(movie.getTitle())
				.setRating(movie.getRating());
	}

	public static Movie of(MovieDTO movie) {
		return new Movie().setId(movie.getId()).setVersion(movie.getVersion()).setTitle(movie.getTitle())
				.setRating(movie.getRating());
	}
}
