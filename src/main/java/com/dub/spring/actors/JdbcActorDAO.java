package com.dub.spring.actors;


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

import com.dub.spring.exceptions.ActorNotFoundException;
import com.dub.spring.exceptions.DuplicateActorException;
import com.dub.spring.site.DateCorrect;


@Repository
public class JdbcActorDAO implements ActorDAO {
	
	@Resource 
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplateObject;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplateObject;
	private SimpleJdbcInsert insertActor;
	
	
	@PostConstruct
	public void setDataSource() {
		this.jdbcTemplateObject = 
	    		  new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplateObject = 
	    		  new NamedParameterJdbcTemplate(dataSource);
		this.insertActor =
	    		  new SimpleJdbcInsert(dataSource)
	      				.withTableName("actor")
	      				.usingGeneratedKeyColumns("actorId");
	}

	@Override
	public void update(Actor actor) {						
			String SQL = "UPDATE actor SET firstname = :firstname, lastname = :lastname, " + 
			"birthdate = :birthdate WHERE actorId = :id";
			SqlParameterSource namedParameters;
			try {
				namedParameters = new MapSqlParameterSource()
					.addValue("firstname", actor.getFirstName())
					.addValue("lastname", actor.getLastName())
					.addValue("birthdate", DateCorrect.correctDate(actor.getBirthDate()))			   			
					.addValue("id", actor.getId());
				namedParameterJdbcTemplateObject.update(SQL, namedParameters);		
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			 
				}
	
	@Override
	public void delete(long id) {		 
			   String SQL = "DELETE FROM actor WHERE actorId = :id";
			   SqlParameterSource namedParameters = 
					   new MapSqlParameterSource("id", Long.valueOf(id));		   
			   namedParameterJdbcTemplateObject.update(SQL, namedParameters);
	}

	@Override
	public List<Actor> listAllActors() 
	{	
		String SQL = "SELECT * FROM actor";
		List<Actor> actors = jdbcTemplateObject.query(
										SQL, new ActorMapper());	
		return actors;
	}
	
	@Override
	public long getNumberOfActors() 
	{		
		String SQL = "SELECT COUNT(*) FROM actor";
		return (long) jdbcTemplateObject.queryForObject(SQL, Long.class);
	}

	@Override
	public Actor getActor(long id) {
		String SQL = "SELECT * FROM actor WHERE actorId = :id";	   		   
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Long.valueOf(id));  		   
		try {
			Actor actor = namedParameterJdbcTemplateObject.queryForObject(
		    				SQL, namedParameters, new ActorMapper());	   	  
			return actor;
		} catch (EmptyResultDataAccessException e) {
			throw new ActorNotFoundException();
		}
	}
	
	@Override
	public void add(Actor actor) {
		SqlParameterSource parameters;
		try {
			parameters = new MapSqlParameterSource()
				.addValue("firstname", actor.getFirstName())
				.addValue("lastname", actor.getLastName())
				.addValue("birthdate", DateCorrect.correctDate(actor.getBirthDate()));
			// actual insertion		   
			Number newId = insertActor.executeAndReturnKey(parameters);		   			   		   	
			actor.setId(newId.longValue());
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			String ex = ExceptionUtils.getRootCauseMessage(e);
			if (ex.contains("actor_unique")) {
				throw new DuplicateActorException();
			} else {
				throw e;
			}
		}
	}

	@Override
	public Actor getActor(String firstName, String lastName) {
		String SQL = "SELECT * FROM actor WHERE firstName = :firstName AND lastName = :lastName";
		SqlParameterSource namedParameters = new MapSqlParameterSource()
						.addValue("firstName", firstName) 
						.addValue("lastName", lastName);  		 
		try {
			Actor actor = namedParameterJdbcTemplateObject.queryForObject(
										SQL, namedParameters, new ActorMapper());
			return actor;
		} catch (EmptyResultDataAccessException e) {
			throw new ActorNotFoundException();
		}
	}
		
}
