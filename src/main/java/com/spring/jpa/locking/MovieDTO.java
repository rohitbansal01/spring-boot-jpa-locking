package com.spring.jpa.locking;

import java.io.Serializable;

public class MovieDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer version;
	private String title;
	private Integer rating;

	public Integer getId() {
		return id;
	}

	public MovieDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public MovieDTO setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public MovieDTO setTitle(String title) {
		this.title = title;
		return this;
	}

	public Integer getRating() {
		return rating;
	}

	public MovieDTO setRating(Integer rating) {
		this.rating = rating;
		return this;
	}
}
