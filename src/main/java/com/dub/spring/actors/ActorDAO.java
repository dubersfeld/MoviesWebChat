package com.dub.spring.actors;

import java.util.List;


public interface ActorDAO {
	   	   	   
	   /** Used to update an existing actor */
	   void update(Actor actor);
	   
	   /** Used to delete an existing actor */
	   void delete(long id);
	   
	   /** Used to list all actors */
	   List<Actor> listAllActors();
	   
	   /** Used to get the number of actor in the table */
	   long getNumberOfActors();
	   
	   /** Used to get an actor by key */
	   Actor getActor(long id);
	   	   
	   /** Used to get an actor by name */
	   Actor getActor(String firstName, String lastName);
	   	   
	   /** Using automatically generated id */ 
	   void add(Actor actor);
}