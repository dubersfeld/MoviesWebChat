package com.dub.spring.movies;

import java.util.Date;


public class Movie {
	
	protected long id;
	
	protected String title;		
	protected Date releaseDate;	
	protected long directorId;
	protected int runningTime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public long getDirectorId() {
		return directorId;
	}
	public void setDirectorId(long directorId) {
		this.directorId = directorId;
	}
	
	public int getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
	
}// class
