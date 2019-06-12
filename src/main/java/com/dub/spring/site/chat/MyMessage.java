package com.dub.spring.site.chat;

//POJO
public class MyMessage {
	 
 private String from;// sender username
 private String text;
 
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

}