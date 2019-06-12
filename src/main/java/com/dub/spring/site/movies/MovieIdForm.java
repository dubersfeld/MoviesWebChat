package com.dub.spring.site.movies;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



public class MovieIdForm {

	@Min(value  = 1, message = "{validate.min.movieId}")
	@NotNull(message = "{validate.required.movieId}")
	Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}