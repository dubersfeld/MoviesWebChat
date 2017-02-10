package com.dub.movies;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dub.repositories.DirectorRepository;
import com.dub.repositories.MovieRepository;
import com.dub.entities.Director;
import com.dub.entities.DisplayMovie;
import com.dub.entities.Movie;
import com.dub.exceptions.MovieNotFoundException;

@Service
public class DefaultMovieServices implements MovieServices {

	@Resource
	private MovieRepository movieRepository;
	
	@Resource
	private DirectorRepository directorRepository;
	
	
	@Override
	public List<DisplayMovie> getAllMovies() {
		List<Movie> movies = (List<Movie>)movieRepository.findAll();
				
		List<DisplayMovie> list = new ArrayList<>();
		
		for (Movie movie : movies) {		
			Director director = 
					directorRepository.findOne(movie.getDirectorId());
			String name = 
					director.getFirstName() + " " + director.getLastName();
			DisplayMovie movieDisplay = new DisplayMovie(movie);
			movieDisplay.setDirectorName(name);
			list.add(movieDisplay);			
		}	
		return list;
	}

	@Override
	public long numberOfMovies() {		
		return movieRepository.count();
	}

	@Override
	public List<DisplayMovie> getMovie(String title) {
				
		List<Movie> movies = movieRepository.findByTitle(title);
		List<DisplayMovie> list = new ArrayList<>();
		
		for (Movie movie : movies) {		
			Director director = 
					directorRepository.findOne(movie.getDirectorId());
			String name = 
					director.getFirstName() + " " + director.getLastName();
			DisplayMovie movieDisplay = new DisplayMovie(movie);
			movieDisplay.setDirectorName(name);
			list.add(movieDisplay);			
		}
		
		return list;// not null				
	}

	@Override
	public void createMovie(Movie movie) {
		movieRepository.save(movie);	
	}

	@Override
	public DisplayMovie getMovie(String title, Date releaseDate) {
		List<Movie> list = movieRepository
							.findByTitleAndReleaseDate(title, releaseDate);
							
		if (!list.isEmpty()) {
			Movie movie = list.get(0);
			Director director = 
				directorRepository.findOne(movie.getDirectorId());
			String name = director.getFirstName() + " " + director.getLastName();
			DisplayMovie displayMovie = new DisplayMovie(movie);
			displayMovie.setDirectorName(name);
			return displayMovie;
		} else {
			throw new MovieNotFoundException();
		}
	}
	
	@Override
	public void deleteMovie(long id) {
		try {
			movieRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new MovieNotFoundException();
		}
	}
	
	@Override
	public void updateMovie(Movie movie) {
		if (movieRepository.exists(movie.getId())) {
			movieRepository.save(movie);
		} else {
			throw new MovieNotFoundException();
		}
	}

	@Override
	public Movie getMovie(long id) {
		if (movieRepository.exists(id)) {
			return movieRepository.findOne(id);
		} else {
			throw new MovieNotFoundException();
		}
	}
	
}
