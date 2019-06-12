package com.dub.spring.site.actors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/* Command object*/
public class ActorMovieForm {
	
	@Min(value  = 1, message = "{validate.min.actorId}")
	@NotNull(message = "{validate.required.actorId}")
	private Integer actorId;
	
	@Min(value  = 1, message = "{validate.min.directorId}")
	@NotNull(message = "{validate.required.directorId}")
	private Integer movieId;
	
	
	public Integer getActorId() {
		return actorId;
	}
	public void setActorId(Integer actorId) {
		this.actorId = actorId;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
		
}
