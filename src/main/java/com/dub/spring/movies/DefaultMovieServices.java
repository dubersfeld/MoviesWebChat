package com.dub.spring.movies;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dub.spring.directors.Director;
import com.dub.spring.directors.DirectorDAO;


@Service
public class DefaultMovieServices implements MovieServices {

	private static SimpleDateFormat sdf 
		= new SimpleDateFormat("MM-dd-yyy Z");

	@Resource
	private MovieDAO movieDAO;
	
	@Resource
	private DirectorDAO directorDAO;
	
	@Override
	public List<DisplayMovie> getAllMovies() {
		List<Movie> movies = movieDAO.listAllMovies();		
		List<DisplayMovie> list = new ArrayList<>();
		
		for (Movie movie : movies) {		
			Director director = 
					directorDAO.getDirector(movie.getDirectorId());
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
		return movieDAO.getNumberOfMovies();
	}

	@Override
	public List<DisplayMovie> getMovie(String title) {
				
		List<Movie> movies = movieDAO.getMovie(title);
		List<DisplayMovie> list = new ArrayList<>();
		
		for (Movie movie : movies) {		
			Director director = 
					directorDAO.getDirector(movie.getDirectorId());
			String name = 
					director.getFirstName() + " " + director.getLastName();
			DisplayMovie movieDisplay = new DisplayMovie(movie);
			movieDisplay.setDirectorName(name);
			list.add(movieDisplay);			
		}
		
		return list;				
	}

	@Override
	public void createMovie(Movie movie) {
		movieDAO.create(movie);		
	}

	@Override
	public DisplayMovie getMovie(String title, Date releaseDate) {

		Movie movie = movieDAO.getMovie(title, releaseDate);
		Director director = 
					directorDAO.getDirector(movie.getDirectorId());
		String name = director.getFirstName() + " " + director.getLastName();
		DisplayMovie displayMovie = new DisplayMovie(movie);
		displayMovie.setDirectorName(name);
		return displayMovie;	
	}
	
	@Override
	public void deleteMovie(long id) {
		movieDAO.getMovie(id);
		movieDAO.delete(id);
	}
	
	@Override
	public void updateMovie(Movie movie) {
	
		movieDAO.getMovie(movie.id);
		movieDAO.update(movie);
	}

	@Override
	public Movie getMovie(long id) {	
		Movie movie = movieDAO.getMovie(id);
		return movie;
	}
	
}
