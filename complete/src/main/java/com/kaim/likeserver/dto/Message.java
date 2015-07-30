package com.kaim.likeserver.dto;

public class Message {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Message(String id, String content)
	{
		this.id = id;
		this.content = content;
	}

	private String id;
	private String content;
}
