package com.dub.spring.advanced;

import java.util.Date;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.dub.spring.entities.Actor;
import com.dub.spring.entities.Director;
import com.dub.spring.entities.DisplayMovie;


public interface AdvancedServices {
	
	@PreAuthorize("hasAuthority('VIEW')")
	public List<DisplayMovie> getMoviesWithActor(String firstName, String lastName);

	@PreAuthorize("hasAuthority('VIEW')")
	public List<Actor> getActorsInMovie(String title, Date releaseDate);

	@PreAuthorize("hasAuthority('VIEW')")
	public List<DisplayMovie> getMoviesByDirector(String firstName, String lastName);

	@PreAuthorize("hasAuthority('VIEW')")
	public List<Actor> getActorsByDirector(String firstName, String lastName);

	@PreAuthorize("hasAuthority('VIEW')")
	public List<Director> getDirectorsByActor(String firstName, String lastName);

	@PreAuthorize("hasAuthority('CREATE')")
	public void createActorFilm(long actorId, long movieId);

	@PreAuthorize("hasAuthority('CREATE')")
	public void createActorFilmSpecial(
			Actor actor, 
			String title, Date releaseDate);		 
}
