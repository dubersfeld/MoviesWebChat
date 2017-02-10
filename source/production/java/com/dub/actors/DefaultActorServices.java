package com.dub.actors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dub.repositories.ActorPhotoRepository;
import com.dub.repositories.ActorRepository;
import com.dub.entities.Actor;
import com.dub.entities.ActorPhoto;
import com.dub.site.actors.CreateActorPhoto;
import com.dub.exceptions.ActorNotFoundException;
import com.dub.exceptions.PhotoNotFoundException;

@Service
public class DefaultActorServices implements ActorServices {
	
	
	@Resource
	private ActorRepository actorRepository;
	
	@Resource
	private ActorPhotoRepository photoRepository;
	
	@Override
	public List<Actor> getAllActors() {
		return (List<Actor>)actorRepository.findAll();
	}

	@Override
	public Actor getActor(long id) {	
		if (actorRepository.exists(id)) {
			return actorRepository.findOne(id);
		} else {
			throw new ActorNotFoundException();
		}
	}

	@Override
	public Actor getActor(String firstName, String lastName) {
		
		List<Actor> list = actorRepository
				.findByFirstNameAndLastName(firstName, lastName);
		
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			throw new ActorNotFoundException();
		}	
	}

	@Override
	public void deleteActor(long id) {
		try {
			actorRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ActorNotFoundException();
		}
	}

	@Override
	public void addActor(Actor actor) {
		actorRepository.save(actor);
	}

	@Override
	public void updateActor(Actor actor) {
		// check existence
		if (actorRepository.exists(actor.getId())) {
			actorRepository.save(actor);
		} else {
			throw new ActorNotFoundException();
		}	
	}

	@Override
	public long numberOfActors() {
		return actorRepository.count();
	}

	@Override
	public byte[] getPhotoData(long id) {
		if (photoRepository.exists(id)) {
			byte[] data = photoRepository.findOne(id).getImageData();
			if (data != null) {
				return data;
			} else {
				throw new PhotoNotFoundException();
			}
		} else {
			throw new PhotoNotFoundException();
		}
	}


	@Override
	public long getPhotoId(Actor actor) {
		List<Long> list = getAllPhotoIds(actor);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			throw new PhotoNotFoundException();
		}
	}
	
	
	@Override
	public List<Long> getAllPhotoIds(Actor actor) {
		List<ActorPhoto> photos = photoRepository
									.findByActorId(actor.getId());
		List<Long> photoIds = new ArrayList<Long>();
		for (ActorPhoto photo : photos) {
			photoIds.add(photo.getId());
		}
		return photoIds;// not null
	}

	@Override
	public void createActorPhoto(CreateActorPhoto crPhoto) 
	throws FileNotFoundException, IOException 
        {	
		ActorPhoto photo = new ActorPhoto();
		File blobIn = new File(crPhoto.getImageFile());
		InputStream blobIs = new FileInputStream(blobIn);
		
		try {
			photo.setActorId(crPhoto.getActorId());	
			byte[] data = new byte[blobIs.available()];
			blobIs.read(data);
			photo.setImageData(data);
			photoRepository.save(photo);
		} catch (Exception e) {
			String ex = ExceptionUtils.getRootCauseMessage(e);
			if (ex.contains("FOREIGN KEY")) {
				throw new ActorNotFoundException();
			} else {
				throw e;
			}
		} finally {
			blobIs.close();
		}
	}
	
	@Override
	public void deletePhoto(long id) {
		try {
			photoRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new PhotoNotFoundException();
		}
		
	}

	@Override
	public ActorPhoto getPhoto(long id) {	
		if (photoRepository.exists(id)) {
			return photoRepository.findOne(id);	
		} else {
			throw new PhotoNotFoundException();
		}
	}

}
