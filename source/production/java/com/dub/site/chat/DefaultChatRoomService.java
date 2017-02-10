package com.dub.site.chat;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import com.dub.exceptions.ChatRoomNotFoundException;

import javax.annotation.PostConstruct;
import javax.websocket.Session;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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
		chatRooms.put(chatRoom.getChatRoomId(), chatRoom);
		
		ChatRoom chatRoom2 = new ChatRoom();
		chatRoom2.setName("Brad Pitt");
		chatRoom2.setChatRoomId(2);
		chatRooms.put(chatRoom2.getChatRoomId(), chatRoom2);
	}

	@Override
	public Map<Long, ChatRoom> getChatRooms() {
		return chatRooms;
	}


	@Override
	public ChatRoom getChatRoom(long id) {
		return chatRooms.get(id);
	}

	@Override
	public MultiJoinResult joinChatRoom(long chatRoomId, String user) 
	{	
		if (chatRooms.get(chatRoomId) == null) {
			return null;
		}
	    ChatMessage message = new ChatMessage();
	    message.setUser(user);
	    message.setTimestamp(Instant.now());
	    message.setType(ChatMessage.Type.JOINED);
	    message.setContentCode("message.chat.joined.session");
	    message.setContentArguments(user);

	    return new MultiJoinResult(this.getChatRoom(chatRoomId), message);
	}

	@Override
	public boolean isConnected(String username, long chatRoomId) {
		return getChatRoom(chatRoomId)
								.getActiveSessions().containsKey(username);
	}

	@Override
	public Locale getLocale(String username, long chatRoomId) {
		return getChatRoom(chatRoomId).getActiveLocales().get(username);
	}

	@Override
	public Set<String> getConnectedUsers(long chatRoom) {
		return getChatRoom(chatRoom).getActiveSessions().keySet();
	}

	@Override
	public Collection<Session> getConnectedSessions(long chatRoomId) {
		return getChatRoom(chatRoomId).getActiveSessions().values();
	}

	@Override
	public void addSession(String username, Session session, long chatRoomId) {
		
		getChatRoom(chatRoomId).getActiveSessions()
											.put(username, session);
	}

	@Override
	public void addLocale(String username, Locale locale, long chatRoomId) {
		getChatRoom(chatRoomId)
							.getActiveLocales().put(username, locale);
		
	}

	@Override
	public Session getSession(String username, long chatRoomId) {
		return getChatRoom(chatRoomId).getActiveSessions().get(username);
	}
	
	@Override
	public void removeSession(String username, long chatRoomId) {
		getChatRoom(chatRoomId).getActiveSessions()
													.remove(username);	
	}

	@Override
	public void removeLocale(String username, long chatRoomId) {
		getChatRoom(chatRoomId).getActiveLocales()
													.remove(username);		
	}
	
	@Override
	public ChatMessage leaveChatRoom(String user, ReasonForLeaving reason) 
	{  
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
	

	@Override
	public String getChatRoomName(long chatRoomId) {
		if (chatRooms.containsKey(chatRoomId)) {
			return chatRooms.get(chatRoomId).getName();
		} else {
			throw new ChatRoomNotFoundException();
		}
	}

}
