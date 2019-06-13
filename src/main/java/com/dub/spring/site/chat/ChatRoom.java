package com.dub.spring.site.chat;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.websocket.Session;


public class ChatRoom {
	
	private long chatRoomId;
	private String name;
		
	private Map<String, String> connectedUsers = new HashMap<>();

	
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
	public Map<String, String> getConnectedUsers() {
		return connectedUsers;
	}
	public void setConnectedUsers(Map<String, String> connectedUsers) {
		this.connectedUsers = connectedUsers;
	}
	
	public void addUser(String sessionId, String username) {
		connectedUsers.put(sessionId, username);
	}
	
	public void removeUserBySessionId(String sessionId) {
		connectedUsers.remove(sessionId);
	} 
	
	// for debugging only
	public void display() {
		for (String key : connectedUsers.keySet()) {
			System.out.println(key + ": " + connectedUsers.get(key));
		}
	} 
  
}
