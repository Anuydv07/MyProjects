package com.weconnect.request;

import com.weconnect.models.User;

public class CreateChatRequest {

	private Integer userId;
	
	public CreateChatRequest(Integer userId) {
		super();
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public CreateChatRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	 
}
