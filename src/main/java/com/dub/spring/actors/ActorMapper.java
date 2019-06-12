package com.dub.spring.actors;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ActorMapper implements RowMapper<Actor> {
	
	@Override
	public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {

		Actor actor = new Actor();

		actor.setId(rs.getLong("actorId"));
		actor.setFirstName(rs.getString("firstname"));
		actor.setLastName(rs.getString("lastname"));
		actor.setBirthDate(rs.getDate("birthdate"));
		
		return actor;
   }
	
}