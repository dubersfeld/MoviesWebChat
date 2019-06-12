package com.dub.spring.actors;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.dub.spring.exceptions.PhotoNotFoundException;


@Repository
public class JdbcPhotoDAO implements PhotoDAO 
{	
	@Resource 
	private DataSource dataSource;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplateObject;
	private SimpleJdbcInsert insertPhoto;
	private JdbcTemplate jdbcTemplateObject;
	
	@PostConstruct
	public void setDataSource() {
		this.jdbcTemplateObject = 
	    		  new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplateObject = 
	    		  new NamedParameterJdbcTemplate(dataSource);
		this.insertPhoto =
	    		  new SimpleJdbcInsert(dataSource)
	      				.withTableName("actorImages")
	      				.usingGeneratedKeyColumns("imageId");
	}

	
	public void create(ActorPhoto photo)  
	{
		SqlParameterSource parameters = new MapSqlParameterSource()			
				.addValue("actorId", photo.getActorId())
				.addValue("data", photo.getImageData());		 	
	
		// actual insertion		   
		Number newId = insertPhoto.executeAndReturnKey(parameters);		   			   		   	
		photo.setImageId(newId.longValue());
	}
	

	@Override
	public void delete(long id) 
	{	
		String SQL = "DELETE FROM actorImages WHERE imageId = :id";
		SqlParameterSource namedParameters = 
			   new MapSqlParameterSource("id", Long.valueOf(id));		   
		namedParameterJdbcTemplateObject.update(SQL, namedParameters);
	}
	
	@Override
	public ActorPhoto getPhoto(long id) 
	{
		String SQL = "SELECT * FROM actorImages WHERE imageId = :id";	   		   
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Long.valueOf(id));  		   
		try {
			ActorPhoto photo = namedParameterJdbcTemplateObject.queryForObject(
		    				SQL, namedParameters, new PhotoMapper());	   	  
			return photo;
		} catch (EmptyResultDataAccessException e) {
			throw new PhotoNotFoundException();
		}
	}	
	
	@Override
	public List<Long> getAllPhotoIds(long actorId) {
		return jdbcTemplateObject.queryForList(
				"SELECT imageId FROM actorImages WHERE actorId = ?", 
				Long.class, actorId);
	}

}
