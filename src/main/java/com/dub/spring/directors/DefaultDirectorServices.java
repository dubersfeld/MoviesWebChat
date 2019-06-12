package com.dub.spring.directors;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dub.spring.repositories.DirectorRepository;
import com.dub.spring.entities.Director;
import com.dub.spring.exceptions.DirectorNotFoundException;

@Service
public class DefaultDirectorServices implements DirectorServices {
	
	@Resource 
	DirectorRepository directorRepository;
	
	
	@Override
	public List<Director> getAllDirectors() {
		return (List<Director>)directorRepository.findAll();
	}

	@Override
	public Director getDirector(long id) {
		if (directorRepository.existsById(id)) {
			return directorRepository.findById(id).get();
		} else {
			throw new DirectorNotFoundException();
		}
	}

	@Override
	public Director getDirector(String firstName, String lastName) {
		
		List<Director> list = directorRepository
								.findByFirstNameAndLastName(firstName, lastName);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			throw new DirectorNotFoundException();
		}	
	}

	@Override
	public void deleteDirector(long id) {
		try {
			this.directorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new DirectorNotFoundException();
		}
	}

	@Override
	public void addDirector(Director director) {
		this.directorRepository.save(director);
	}

	@Override
	public void updateDirector(Director director) {
		if (directorRepository.existsById(director.getId())) {
			directorRepository.save(director);
		} else {
			throw new DirectorNotFoundException();
		}
	}

	@Override
	public long numberOfDirectors() {
		return directorRepository.count();
	}
	
}