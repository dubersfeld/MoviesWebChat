package com.dub.spring.actors;

public class ActorPhoto {   
	private byte[] imageData;
	private long imageId;
	private long actorId;
	
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	public long getImageId() {
		return imageId;
	}
	public void setImageId(long id) {
		this.imageId = id;
	}
	public long getActorId() {
		return actorId;
	}
	public void setActorId(long actorId) {
		this.actorId = actorId;
	}
	
}