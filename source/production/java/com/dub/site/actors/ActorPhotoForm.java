package com.dub.site.actors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/* Command object */

public class ActorPhotoForm {
	
	@NotNull(message = "{validate.imageFile.required}")
	@Size(min = 1, message = "{validate.imageFile.required}")
	private String imageFile;
	
	@Min(value = 1, message = "{validate.min.actorId}")
	@NotNull(message = "{validate.required.actorId}")
	private long actorId;
	
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	
	public long getActorId() {
		return actorId;
	}
	public void setActorId(long actorId) {
		this.actorId = actorId;
	}
	
}