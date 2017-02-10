package com.dub.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dub.entities.Director;



public interface DirectorRepository extends CrudRepository<Director, Long>
{
	List<Director> findByFirstNameAndLastName(String firstName, String lastName);
}
