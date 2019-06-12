package com.dub.spring.site;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



public class IdForm {

	@Min(value  = 1, message = "{validate.min.actorId}")
	@NotNull(message = "{validate.required.actorId}")
	Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}