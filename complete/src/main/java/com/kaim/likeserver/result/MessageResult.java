package com.kaim.likeserver.result;

import java.util.List;

import com.kaim.likeserver.dto.Message;

public class MessageResult {
	public List<Message> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public MessageResult(List<Message> _messages, int _currentPage, int totalPage)
	{
		this.messageList = _messages;
		this.totalPage = totalPage;
		this.currentPage = _currentPage;
		this.total = _messages.size();
	}

	private List<Message> messageList;
	private int total;
	private int currentPage;
	private int totalPage;
}
