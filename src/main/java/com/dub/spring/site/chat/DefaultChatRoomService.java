package com.dub.spring.site.chat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.websocket.Session;

import org.springframework.stereotype.Service;

import com.dub.spring.exceptions.ChatRoomNotFoundException;

@Service
public class DefaultChatRoomService implements ChatRoomService
{
	
	private Map<Long, ChatRoom> chatRooms = new HashMap<>();
	
	@PostConstruct
	public void init()
	{
		
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setName("Woody Allen");
		chatRoom.setChatRoomId(1);
		this.chatRooms.put(chatRoom.getChatRoomId(), chatRoom);
		
		ChatRoom chatRoom2 = new ChatRoom();
		chatRoom2.setName("Brad Pitt");
		chatRoom2.setChatRoomId(2);
		this.chatRooms.put(chatRoom2.getChatRoomId(), chatRoom2);
	}

	@Override
	public Map<Long, ChatRoom> getChatRooms() {
		return this.chatRooms;
	}


	@Override
	public ChatRoom getChatRoom(long id) {
		return this.chatRooms.get(id);
	}

	@Override
	public boolean isConnected(String username, long chatRoomId) {
		return this.getChatRoom(chatRoomId)
								.getActiveSessions().containsKey(username);
	}

	@Override
	public Locale getLocale(String username, long chatRoomId) {
		return this.getChatRoom(chatRoomId).getActiveLocales().get(username);
	}

	@Override
	public Set<String> getConnectedUsers(long chatRoom) {
		return this.getChatRoom(chatRoom).getActiveSessions().keySet();
	}

	@Override
	public Collection<Session> getConnectedSessions(long chatRoomId) {
		return this.getChatRoom(chatRoomId).getActiveSessions().values();
	}

	@Override
	public void addSession(String username, Session session, long chatRoomId) {
		
		this.getChatRoom(chatRoomId).getActiveSessions()
											.put(username, session);
	}

	@Override
	public void addLocale(String username, Locale locale, long chatRoomId) {
		this.getChatRoom(chatRoomId)
							.getActiveLocales().put(username, locale);
		
	}

	@Override
	public Session getSession(String username, long chatRoomId) {
		return this.getChatRoom(chatRoomId).getActiveSessions().get(username);
	}
	
	@Override
	public void removeSession(String username, long chatRoomId) {
		this.getChatRoom(chatRoomId).getActiveSessions()
													.remove(username);	
	}

	@Override
	public void removeLocale(String username, long chatRoomId) {
		this.getChatRoom(chatRoomId).getActiveLocales()
													.remove(username);		
	}
	
	/*
	@Override
	public ChatMessage leaveChatRoom(String user, ReasonForLeaving reason) {  
		ChatMessage message = new ChatMessage();
		message.setUser(user);
		message.setTimestamp(Instant.now());
		if (reason == ReasonForLeaving.ERROR) {
			message.setType(ChatMessage.Type.ERROR);
			message.setContentCode("message.chat.left.chat.error");
		} else if (reason == ReasonForLeaving.LOGGED_OUT)  {
			message.setType(ChatMessage.Type.LEFT);
			message.setContentCode("message.chat.logged.out");
		} else {
			message.setContentCode("message.chat.left.chat.normal");
		}
	        
		message.setContentArguments(user);
		return message;
	 
	}
	*/

	@Override
	public String getChatRoomName(long chatRoomId) {
		if (this.chatRooms.containsKey(chatRoomId)) {
			return this.chatRooms.get(chatRoomId).getName();
		} else {
			throw new ChatRoomNotFoundException();
		}
	}

}
