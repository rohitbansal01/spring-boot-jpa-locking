package com.spring.jpa.locking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jpa.locking.domain.Movie;
import com.spring.jpa.locking.domain.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	@Transactional
	public void addMovie(final Movie movie) {
		Movie movieSearched = this.findByTitle(movie.getTitle());
		if (movieSearched == null) {
			movieRepository.save(movie);
		} else {
			this.updateRating(movieSearched);
		}
	}

	@Override
	@Transactional
	public void updateRating(final Movie movie) {
		movie.setRating(movie.getRating() + 1);
		movieRepository.save(movie);
	}

	@Override
	@Transactional
	public long count() {
		return movieRepository.count();
	}

	@Override
	@Transactional
	public Movie findByTitle(final String title) {
		return movieRepository.findByTitle(title);
	}

}
