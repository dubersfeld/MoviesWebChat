package com.dub.site;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



public class IdForm {

	@Min(value  = 1, message = "{validate.min.actorId}")
	@NotNull(message = "{validate.required.actorId}")
	long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}