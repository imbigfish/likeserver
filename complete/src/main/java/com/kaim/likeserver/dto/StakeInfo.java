package com.kaim.likeserver.dto;

import java.util.List;

public class StakeInfo {
	public Integer getStakeId() {
		return stakeId;
	}
	public void setStakeId(Integer stakeId) {
		this.stakeId = stakeId;
	}
	public Integer getMatchId() {
		return matchId;
	}
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public List<StakeDetail> getStakeDetails() {
		return stakeDetails;
	}
	public void setStakeDetails(List<StakeDetail> stakeDetails) {
		this.stakeDetails = stakeDetails;
	}
	private Integer stakeId;
	private Integer matchId;
	private Integer orderId;
	private List<StakeDetail> stakeDetails;
}
