package com.dub.spring.advanced;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.dub.spring.actors.Actor;
import com.dub.spring.actors.ActorDAO;
import com.dub.spring.actors.ActorMapper;
import com.dub.spring.directors.Director;
import com.dub.spring.directors.DirectorMapper;
import com.dub.spring.movies.Movie;
import com.dub.spring.movies.MovieDAO;
import com.dub.spring.movies.MovieMapper;
import com.dub.spring.site.DateCorrect;
import com.dub.spring.exceptions.ActorNotFoundException;
import com.dub.spring.exceptions.DuplicateEntryException;
import com.dub.spring.exceptions.MovieNotFoundException;


@Repository
public class JdbcAdvancedDAO implements AdvancedDAO {
	
	@Resource 
	private DataSource dataSource;
	
	@Resource
	private ActorDAO actorDAO;
	
	@Resource
	private MovieDAO movieDAO;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplateObject;
	
	@PostConstruct
	public void setDataSource() {
		this.namedParameterJdbcTemplateObject = 
	    		  new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Movie> getMoviesByActorName(String firstName, String lastName) {
			
		String SQL = 
				"SELECT m.* FROM movie m JOIN actorFilm am Join actor a ON " + 
	            "(m.movieId = am.filmId AND am.actorId = a.actorId) " + 
				"WHERE a.firstName = :firstName AND a.lastName = :lastName";	   			
			
		SqlParameterSource namedParameters = new MapSqlParameterSource()
						.addValue("firstName", firstName)
						.addValue("lastName", lastName);
		List<Movie> movies = namedParameterJdbcTemplateObject.query(
				SQL, namedParameters, new MovieMapper());	
		
		
		return movies;
	}// getMoviesByActorName
		
	@Override
	public List<Actor> getActorsByMovie(String title, Date releaseDate) {
		
		String SQL = "SELECT a.* FROM actor a JOIN actorFilm am JOIN movie m ON " + 
		"(a.actorId = am.actorId AND am.filmId = m.movieId) " + 
		"WHERE m.title = :title AND m.releaseDate = :releaseDate";
					
		SqlParameterSource namedParameters;
		try {
			namedParameters = new MapSqlParameterSource()
							.addValue("title", title)
							.addValue("releaseDate", DateCorrect.correctDate(releaseDate));
			List<Actor> actors = namedParameterJdbcTemplateObject.query(
					SQL, namedParameters, new ActorMapper());
			return actors;		
		} catch (ParseException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		
		
	}// getActorsByMovie
		
	@Override
	public List<Movie> getMoviesByDirectorName(String firstName, String lastName) {
		String SQL = 
				"SELECT m.* FROM movie m JOIN director d ON m.directorId = d.directorId " + 
	            "WHERE d.firstName = :firstName AND d.lastName = :lastName";	   		
		
		SqlParameterSource namedParameters = new MapSqlParameterSource()
						.addValue("firstName", firstName)
						.addValue("lastName", lastName);
		List<Movie> movies = namedParameterJdbcTemplateObject.query(
				SQL, namedParameters, new MovieMapper());	
		
		return movies;
	}// getMoviesByDirectorName
		
	@Override
	public List<Actor> getActorsByDirectorName(String firstName, String lastName) {
		String SQL = "SELECT * FROM actor WHERE actorId IN " +  
				     "(SELECT actorId FROM actorFilm WHERE filmId IN " +
					 "(SELECT movieId FROM movie WHERE directorId IN " +
					 "(SELECT directorId FROM director WHERE firstName = :firstName AND lastName = :lastName)))";
		
		SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue("firstName", firstName)
					.addValue("lastName", lastName);
		
		List<Actor> actors = namedParameterJdbcTemplateObject.query(
				SQL, namedParameters, new ActorMapper());	
		
		return actors;
	}
	
	@Override
	public List<Director> getDirectorsByActorName(String firstName,
			String lastName) {
		String SQL = "SELECT * FROM director WHERE directorId IN " +  
			     "(SELECT directorId FROM movie WHERE movieId IN " +
				 "(SELECT filmId FROM actorFilm WHERE actorId IN " +
				 "(SELECT actorId FROM actor WHERE firstName = :firstName AND lastName = :lastName)))";
	
		SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue("firstName", firstName)
					.addValue("lastName", lastName);
	
		List<Director> directors = namedParameterJdbcTemplateObject.query(
						SQL, namedParameters, new DirectorMapper());	
	
		return directors;	
	}

	@Override
	public void createActorFilm(Long actorId, Long movieId) 
	{
		String SQL = "INSERT INTO actorFilm (actorId, filmId)" + 
	  				 " VALUES (:actorId, :movieId)";		
		try {
			Map<String, Object> namedParameters = new HashMap<>();
			namedParameters.put("actorId", actorId);
			namedParameters.put("movieId", movieId);	    	  	    	  
			namedParameterJdbcTemplateObject.update( SQL, namedParameters);   	  		
		} catch (Exception e) {
			String ex = ExceptionUtils.getRootCauseMessage(e);	
			if (ex.contains("FOREIGN KEY") && ex.contains("actorId")) {
				throw new ActorNotFoundException();
			} else if (ex.contains("FOREIGN KEY") && ex.contains("filmId")) {
				throw new MovieNotFoundException();
			} else if (ex.contains("Duplicate")) {
				throw new DuplicateEntryException();
			}
		}
	}

	
	@Override
	public void createActorFilm(
				Actor actor, 
				String title, Date releaseDate) 
	{
		Actor act = actorDAO.getActor(
					actor.getFirstName(), actor.getLastName());
			
		Movie movie;
		try {
			movie = movieDAO.getMovie(title, DateCorrect.correctDate(releaseDate));
			if (movie != null) {
				this.createActorFilm(act.getId(), movie.getId());
				} else {
					throw new MovieNotFoundException();
				}
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
	
	}

}// class
