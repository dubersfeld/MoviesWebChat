package com.dub.site.chat;

import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;

public interface ChatRoomService
{

	Map<Long, ChatRoom> getChatRooms();
	 
	 
	ChatRoom getChatRoom(long id);
	
	String getChatRoomName(long id);
	
	 
	@PreAuthorize("authentication.principal.username.equals(#user) and " +
	            "hasAuthority('CHAT')")
	MultiJoinResult joinChatRoom(long chatRoomId, @P("user") String user);
	 
	 
	@PreAuthorize("authentication.principal.username.equals(#user) and " +
            "hasAuthority('CHAT')")
	ChatMessage leaveChatRoom(@P("user") String user, ReasonForLeaving reason);
 	
 
	boolean isConnected(String username, long chatRoomId);
	 
	Locale getLocale(String username, long chatRoomId);
	
	Session getSession(String username, long chatRoomId);
	 
	Set<String> getConnectedUsers(long chatRoomId);
	 
	Collection<Session> getConnectedSessions(long chatRoomId);
	
	void addSession(String username, Session session, long chatRoomId);
	
	void removeSession(String username, long chatRoomId);
	
	void addLocale(String username, Locale locale, long chatRoomId);
	
	void removeLocale(String username, long chatRoomId);


    public static enum ReasonForLeaving
    {
        NORMAL,

        LOGGED_OUT,

        ERROR
    }
}
