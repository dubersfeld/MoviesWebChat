package com.dub.spring.advanced;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;




public class ActorMovieMapper implements RowMapper<ActorMovie> {

	@Override
	public ActorMovie mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ActorMovie am = new ActorMovie();

		am.setActorId(rs.getLong("actorId"));
		am.setMovieId(rs.getLong("filmId"));

		
		return am;
	}

}
