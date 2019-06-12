package com.dub.spring.site.actors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



public class PhotoIdForm {

	@Min(value  = 1, message = "{validate.min.photoId}")
	@NotNull(message = "{validate.required.photoId}")
	Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}