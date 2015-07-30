package com.kaim.likeserver.result;

import com.kaim.likeserver.dto.UserInfo;

public class SingleUser {
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public StatusResult getStatus() {
		return status;
	}
	public void setStatus(StatusResult status) {
		this.status = status;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accesstoKen) {
		this.accessToken = accesstoKen;
	}
	
	public SingleUser(UserInfo userInfo, StatusResult status)
	{
		this.userInfo = userInfo;
		this.status = status;
		
		this.accessToken = this.userInfo.getToken();
	}
	
	private UserInfo userInfo;
	private StatusResult status;
	private String accessToken;

}
