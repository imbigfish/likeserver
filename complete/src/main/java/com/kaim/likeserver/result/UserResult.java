package com.kaim.likeserver.result;

import java.util.ArrayList;
import java.util.List;

import com.kaim.likeserver.dto.UserInfo;

public class UserResult {
	public List<UserInfo> getUserList() {
		return userList;
	}
	public void setUserList(List<UserInfo> userInfo) {
		this.userList = userInfo;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public StatusResult getStatus() {
		return status;
	}
	public void setStatus(StatusResult status) {
		this.status = status;
	}
	
	private List<UserInfo> userList = new ArrayList<UserInfo>();
	private Integer currentPage = 0;
	private Integer totalPage = 0;
	private StatusResult status = new StatusResult(100, "Success");
	
	public UserResult(List<UserInfo> userInfo, Integer currentPage, Integer totalPage)
	{
		this.userList = userInfo;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
	}

	public UserResult(StatusResult status)
	{
		this.status = status;
	}
}
