package com.dub.site.chat;


//POJO
public class MultiJoinResult
{	
    private final ChatRoom chatRoom;
    private final ChatMessage joinMessage;

    public MultiJoinResult(ChatRoom chatRoom, ChatMessage joinMessage)
    {
        this.chatRoom = chatRoom;
        this.joinMessage = joinMessage;
    }
    
    public ChatRoom getChatRoom()
    {
        return chatRoom;
    }

    public ChatMessage getJoinMessage()
    {
        return joinMessage;
    }
}