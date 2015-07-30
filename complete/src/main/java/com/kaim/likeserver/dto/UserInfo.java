package com.kaim.likeserver.dto;

import java.util.Random;


public class UserInfo {
	static Random rand = new Random();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String loginId) {
		this.personId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static Random getRand() {
		return rand;
	}

	public static void setRand(Random rand) {
		UserInfo.rand = rand;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTotalOrderCount() {
		return totalOrderCount;
	}

	public void setTotalOrderCount(Integer totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}

	public Float getRedOrderRate() {
		return redOrderRate;
	}

	public void setRedOrderRate(Float redOrderRate) {
		this.redOrderRate = redOrderRate;
	}

	public Integer getRedOrderRank() {
		return redOrderRank;
	}

	public void setRedOrderRank(Integer redOrderRank) {
		this.redOrderRank = redOrderRank;
	}

	public Float getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(Float profitRate) {
		this.profitRate = profitRate;
	}

	public Integer getProfitRank() {
		return profitRank;
	}

	public void setProfitRank(Integer profitRank) {

		this.profitRank = profitRank;
	}

	public boolean isFollowingStatus() {
		return followingStatus;
	}

	public void setFollowingStatus(boolean followingStatus) {
		this.followingStatus = followingStatus;
	}

	public boolean isFollowerStatus() {
		return followerStatus;
	}

	public void setFollowerStatus(boolean followerStatus) {
		this.followerStatus = followerStatus;
	}

	private Integer userId = -1;
	private String email = "";
	private String nickName = "";
	private String personId = "";
	private String password;
	private String token = "";
	private String imgUrl = "http://img.hb.aicdn.com/15b1040b9e0178558c9e5d65869077da1c80f80214cad-UhBX5V_fw658";
	private String description = "";
	private Integer totalOrderCount = rand.nextInt();
	private Float redOrderRate = rand.nextFloat();
	private Integer redOrderRank = rand.nextInt();
	private Float profitRate = rand.nextFloat();
	private Integer profitRank = rand.nextInt();
	private boolean followingStatus = true;
	private boolean followerStatus = true;
}
