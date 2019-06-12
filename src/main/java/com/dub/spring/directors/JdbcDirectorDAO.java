package com.dub.spring.directors;

import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.dub.spring.directors.Director;
import com.dub.spring.exceptions.DuplicateDirectorException;
import com.dub.spring.site.DateCorrect;
import com.dub.spring.exceptions.DirectorNotFoundException;


@Repository
public class JdbcDirectorDAO implements DirectorDAO {
	
	@Resource 
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplateObject;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplateObject;
	private SimpleJdbcInsert insertDirector;
	
	@PostConstruct
	public void setDataSource() {
		this.jdbcTemplateObject = 
	    		  new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplateObject = 
	    		  new NamedParameterJdbcTemplate(dataSource);
		this.insertDirector =
	    		  new SimpleJdbcInsert(dataSource)
	      				.withTableName("director")
	      				.usingGeneratedKeyColumns("directorId");
	}
	

	@Override
	public void update(Director director) {							
		
			String SQL = "UPDATE director SET firstname = :firstname, lastname = :lastname, " + 
			"birthdate = :birthdate WHERE directorId = :id";
			SqlParameterSource namedParameters;
			try {
				namedParameters = new MapSqlParameterSource()
					.addValue("firstname", director.getFirstName())
					.addValue("lastname", director.getLastName())
					.addValue("birthdate", DateCorrect.correctDate(director.getBirthDate()))			   			
					.addValue("id", director.getId());
				namedParameterJdbcTemplateObject.update(SQL, namedParameters);	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			 
		}// update
	
	
	@Override
	public void delete(long id) {
		 
			   String SQL = "DELETE FROM director WHERE directorId = :id";
			   SqlParameterSource namedParameters = 
					   new MapSqlParameterSource("id", Long.valueOf(id));		   
			   namedParameterJdbcTemplateObject.update(SQL, namedParameters);
			
	}// delete

	@Override
	public List<Director> listAllDirectors() {		
		String SQL = "SELECT * FROM director";
		List<Director> directors = jdbcTemplateObject.query(
										SQL, new DirectorMapper());
		return directors;
	}

	@Override
	public long getNumberOfDirectors() {
		String SQL = "SELECT COUNT(*) FROM director";
		long count = jdbcTemplateObject.queryForObject(SQL, Long.class);
		return count;
	}

	@Override
	public Director getDirector(long id) {
		String SQL = "SELECT * FROM director WHERE directorId = :id";	   		   
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Long.valueOf(id));  		   
		try {
			Director director = (Director) namedParameterJdbcTemplateObject.queryForObject(
		    		SQL, namedParameters, new DirectorMapper());	   	  
			return director;
		} catch (EmptyResultDataAccessException e) {
			throw new DirectorNotFoundException();
		} 
	}// getDirector

	@Override
	public void add(Director director) {
		SqlParameterSource parameters;
		try {
			parameters = new MapSqlParameterSource()
				.addValue("firstname", director.getFirstName())
				.addValue("lastname", director.getLastName())
				.addValue("birthdate", DateCorrect.correctDate(director.getBirthDate()));
			// actual insertion		   
			Number newId = insertDirector.executeAndReturnKey(parameters);		   			   		   	
			director.setId(newId.intValue());
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			String ex = ExceptionUtils.getRootCauseMessage(e);
			if (ex.contains("director_unique")) {
				throw new DuplicateDirectorException();
			} else {
				throw e;
			}
		}
	}

	@Override
	public Director getDirector(
								String firstName, 
								String lastName) {
		String SQL = "SELECT * FROM director WHERE firstName = :firstName AND lastName = :lastName";
		SqlParameterSource namedParameters = new MapSqlParameterSource()
						.addValue("firstName", firstName)
						.addValue("lastName", lastName);  		 
		try {
			Director director = namedParameterJdbcTemplateObject.queryForObject(
										SQL, namedParameters, new DirectorMapper());
			return director;
		} catch (EmptyResultDataAccessException e) {
			throw new DirectorNotFoundException();
		}
	}

}// class
