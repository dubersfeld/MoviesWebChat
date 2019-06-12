package com.dub.spring.movies;

import java.util.Date;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.dub.spring.entities.DisplayMovie;
import com.dub.spring.entities.Movie;

@PreAuthorize("hasAuthority('VIEW')")
public interface MovieServices {

	List<DisplayMovie> getAllMovies();
	long numberOfMovies();
	
	Movie getMovie(long id);
	
	// should be unique
	DisplayMovie getMovie(String title, Date releaseDate);
		
	// can give several matches
	List<DisplayMovie> getMovie(String title);
	
	@PreAuthorize("hasAuthority('DELETE')")
	void deleteMovie(long id);
	
	@PreAuthorize("hasAuthority('CREATE')")
	void createMovie(Movie movie);	
	
	@PreAuthorize("hasAuthority('UPDATE')")
	void updateMovie(Movie movie);	

}
