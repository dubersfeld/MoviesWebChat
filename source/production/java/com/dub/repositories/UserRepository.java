package com.dub.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dub.entities.UserPrincipal;

public interface UserRepository extends CrudRepository<UserPrincipal, Long>
{
	UserPrincipal getByUsername(String username);
	
	List<UserPrincipal> findAll();
}
