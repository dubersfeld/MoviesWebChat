package com.dub.spring.directors;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class DirectorMapper implements RowMapper<Director> {

	public Director mapRow(ResultSet rs, int rowNum) throws SQLException {

		Director director = new Director();

		director.setId(rs.getLong("directorId"));
		director.setFirstName(rs.getString("firstname"));
		director.setLastName(rs.getString("lastname"));
		director.setBirthDate(rs.getDate("birthdate"));
		
		return director;
   }// mapRow
}// class
