package com.dub.site.chat;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.websocket.Session;


public class ChatRoom
{
	private long chatRoomId;
	private String name;
	
	private Map<String,Session> activeSessions = new HashMap<>();
	private Map<String,Locale> activeLocales = new HashMap<>();
	
	public long getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(long chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
	public Map<String, Session> getActiveSessions() {
		return activeSessions;
	}

	public void setActiveSessions(Map<String, Session> activeSessions) {
		this.activeSessions = activeSessions;
	}

	public Map<String, Locale> getActiveLocales() {
		return activeLocales;
	}

	public void setActiveLocales(Map<String, Locale> activeLocales) {
		this.activeLocales = activeLocales;
	}
  
}
