package com.dub.spring.site.chat;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
//import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/** This ApplicationListener is used for logging only */

@Component
public class StompConnectListener implements ApplicationListener<SessionConnectEvent> {
	 
    private static final Logger log = LoggerFactory.getLogger(StompConnectListener.class);
  
	@Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        
    }
}