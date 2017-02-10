package com.dub.site.chat;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dub.config.annotation.WebController;
import com.dub.exceptions.ChatRoomNotFoundException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;

@WebController
@RequestMapping("chatRoom")
public class ChatRoomController
{
    @Inject ChatRoomService chatRoomService;
    
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Map<String, Object> model, HttpServletRequest request)
    {
        Map<Long,ChatRoom> chatRooms = this.chatRoomService.getChatRooms();
    	
    	model.put("chatrooms", chatRooms);
        
    	HttpSession session = request.getSession();
    	session.setAttribute("locale", LocaleContextHolder.getLocale());
    	
        return "chat/chatRoomList";
    }

    
    @RequestMapping(value = "join/{chatRoomId}", method = RequestMethod.GET)
    public String joinChatGet(Map<String, Object> model, HttpServletResponse response,
                           @PathVariable("chatRoomId") long chatRoomId)
    { 
    	try {
    		this.setNoCacheHeaders(response);
    		model.put("chatRoomId", chatRoomId);
    		model.put("chatRoomName", chatRoomService.getChatRoomName(chatRoomId));
    	
    		return "chat/chatRoom";
    	} catch (ChatRoomNotFoundException e) {
    		return "error";
    	}
    }
     
   
    private void setNoCacheHeaders(HttpServletResponse response)
    {
        response.setHeader("Expires", "Thu, 1 Jan 1970 12:00:00 GMT");
        response.setHeader("Cache-Control","max-age=0, must-revalidate, no-cache");
        response.setHeader("Pragma", "no-cache");
    }
   
}
