package com.kaim.likeserver.dto;

import java.util.List;

public class OrderInfo {
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public UserInfo getOwnerInfo() {
		return ownerInfo;
	}

	public void setOwnerInfo(UserInfo ownerInfo) {
		this.ownerInfo = ownerInfo;
	}

	public String getGameEn() {
		return gameEn;
	}

	public void setGameEn(String gameEn) {
		this.gameEn = gameEn;
	}

	public String getBetWay() {
		return betWay;
	}

	public void setBetWay(String betWay) {
		this.betWay = betWay;
	}

	public String getAverageProfit() {
		return averageProfit;
	}

	public void setAverageProfit(String averageProfit) {
		this.averageProfit = averageProfit;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public List<ChuanInfo> getChuanList() {
		return chuanList;
	}

	public void setChuanList(List<ChuanInfo> chuanList) {
		this.chuanList = chuanList;
	}

	public List<StakeInfo> getStakeInfos() {
		return stakeInfos;
	}

	public void setStakeInfos(List<StakeInfo> stakeInfos) {
		this.stakeInfos = stakeInfos;
	}

	public boolean isOptimization() {
		return isOptimization;
	}

	public void setOptimization(boolean isOptimization) {
		this.isOptimization = isOptimization;
	}

	private Integer orderId;
	private UserInfo ownerInfo;
	private String gameEn;
	private String betWay;
	private String averageProfit;
	private int orderStatus;
	private String deadline;
	private List<ChuanInfo> chuanList;
	private List<StakeInfo> stakeInfos;
	private boolean isOptimization;
}
