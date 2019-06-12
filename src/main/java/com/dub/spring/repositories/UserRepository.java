package com.dub.spring.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dub.spring.entities.UserPrincipal;

public interface UserRepository extends CrudRepository<UserPrincipal, Long>
{
	UserPrincipal getByUsername(String username);
	
	List<UserPrincipal> findAll();
}
