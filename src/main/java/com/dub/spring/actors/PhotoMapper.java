package com.dub.spring.actors;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PhotoMapper implements RowMapper<ActorPhoto> {
	
	@Override
	public ActorPhoto mapRow(ResultSet rs, int rowNum) throws SQLException {

		ActorPhoto photo = new ActorPhoto();

		photo.setImageId(rs.getLong("imageId"));
		photo.setActorId(rs.getLong("actorId"));
		photo.setImageData(rs.getBytes("data"));
		
		return photo;
   }
	
}