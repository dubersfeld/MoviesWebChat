package com.dub.spring.movies;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dub.spring.repositories.DirectorRepository;
import com.dub.spring.repositories.MovieRepository;

import com.dub.spring.entities.Director;
import com.dub.spring.entities.DisplayMovie;
import com.dub.spring.entities.Movie;
import com.dub.spring.exceptions.DirectorNotFoundException;
import com.dub.spring.exceptions.MovieNotFoundException;

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
			Optional<Director> director = 
					directorRepository.findById(movie.getDirectorId());
			if (director.isPresent()) {
				String name = 
						director.get().getFirstName() + " " + director.get().getLastName();
				DisplayMovie movieDisplay = new DisplayMovie(movie);
				movieDisplay.setDirectorName(name);
				list.add(movieDisplay);		
			} else {
				throw new DirectorNotFoundException();
			}
				
		}	
		return list;
	}

	@Override
	public long numberOfMovies() {		
		return movieRepository.count();
	}

	@Override
	public List<DisplayMovie> getMovie(String title) {
				
		List<Movie> movies = this.movieRepository.findByTitle(title);
		List<DisplayMovie> list = new ArrayList<>();
		
		for (Movie movie : movies) {		
			Optional<Director> director = 
					directorRepository.findById(movie.getDirectorId());
			if (director.isPresent()) {
				String name = 
						director.get().getFirstName() + " " + director.get().getLastName();
				DisplayMovie movieDisplay = new DisplayMovie(movie);
				movieDisplay.setDirectorName(name);
				list.add(movieDisplay);
			} else {
				throw new DirectorNotFoundException();
			}
						
		}
		
		return list;// not null				
	}

	@Override
	public void createMovie(Movie movie) {
		this.movieRepository.save(movie);	
	}

	@Override
	public DisplayMovie getMovie(String title, Date releaseDate) {
		List<Movie> list = movieRepository
							.findByTitleAndReleaseDate(title, releaseDate);
							
		if (!list.isEmpty()) {
			Movie movie = list.get(0);
			Optional<Director> director = 
				directorRepository.findById(movie.getDirectorId());
			if (director.isPresent()) {
				String name = director.get().getFirstName() + " " + director.get().getLastName();
				DisplayMovie displayMovie = new DisplayMovie(movie);
				displayMovie.setDirectorName(name);
				return displayMovie;
			} else {
				throw new DirectorNotFoundException();
			}
			
	
		} else {
			throw new MovieNotFoundException();
		}
	}
	
	@Override
	public void deleteMovie(long id) {
		try {
			movieRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new MovieNotFoundException();
		}
	}
	
	@Override
	public void updateMovie(Movie movie) {
		if (movieRepository.existsById(movie.getId())) {
			movieRepository.save(movie);
		} else {
			throw new MovieNotFoundException();
		}
	}

	@Override
	public Movie getMovie(long id) {
		if (movieRepository.existsById(id)) {
			return movieRepository.findById(id).get();
		} else {
			throw new MovieNotFoundException();
		}
	}
	
}
