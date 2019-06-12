package com.dub.spring.site.chat;


// POJO holds an outbound message
public class OutputMessage {
	 
    private String from;// sender
    private String text;
    private String time;
    private Code code;// service code
    
    public OutputMessage(String from, String text, String time, Code code) {
    	this.from = from;
    	this.text = text;
    	this.time = time;
    	this.code = code;
    }
    
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	
	
	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}


	static enum Code {
		NORMAL, JOINED, LEFT
	}
}