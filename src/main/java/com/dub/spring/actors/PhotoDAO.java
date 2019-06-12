package com.dub.spring.actors;

import java.util.List;


public interface PhotoDAO 
{	       
	/** Using automatically generated id */  
	void create(ActorPhoto photo);
	     
	ActorPhoto getPhoto(long id);
	   
	List<Long> getAllPhotoIds(long actorId);
	
	void delete(long photoId);   	   
}