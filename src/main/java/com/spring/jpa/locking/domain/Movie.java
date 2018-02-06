package com.spring.jpa.locking.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_movie")
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private Integer version;

	private String title;

	private Integer rating;

	/**
	 *  Empty constructor for (de)serialization
	 */
	public Movie() {
	}

	public Movie(String title, Integer rating) {
		this.title = title;
		this.rating = rating;
	}

	public Integer getId() {
		return id;
	}

	public Movie setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public Movie setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Movie setTitle(String title) {
		this.title = title;
		return this;
	}

	public Integer getRating() {
		return rating;
	}

	public Movie setRating(Integer rating) {
		this.rating = rating;
		return this;
	}
}