package com.dub.spring.movies;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MovieMapper implements RowMapper<Movie> {

	@Override
	public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
		Movie movie = new Movie();

		movie.setId(rs.getLong("movieId"));
		movie.setTitle(rs.getString("title"));
		movie.setReleaseDate(rs.getDate("releasedate"));
		movie.setDirectorId(rs.getLong("directorId"));	
		movie.setRunningTime(rs.getInt("runningtime"));
				
		return movie;
	}// mapRow

}// class
