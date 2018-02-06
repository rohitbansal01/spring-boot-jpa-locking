package com.spring.jpa.locking.domain;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

	public Movie findByTitle(String title);

}
