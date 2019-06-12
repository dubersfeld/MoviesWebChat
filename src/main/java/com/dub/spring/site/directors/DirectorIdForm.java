package com.dub.spring.site.directors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



public class DirectorIdForm {

	@Min(value  = 1, message = "{validate.min.directorId}")
	@NotNull(message = "{validate.required.directorId}")
	Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}