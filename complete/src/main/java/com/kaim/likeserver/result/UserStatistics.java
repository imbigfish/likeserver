package com.kaim.likeserver.result;

public class UserStatistics {
	public Integer getFollowCnt() {
		return followCnt;
	}
	public void setFollowCnt(Integer followCnt) {
		this.followCnt = followCnt;
	}
	public Integer getFansCnt() {
		return fansCnt;
	}
	public void setFansCnt(Integer fansCnt) {
		this.fansCnt = fansCnt;
	}
	public Integer getOrderCnt() {
		return orderCnt;
	}
	public void setOrderCnt(Integer orderCnt) {
		this.orderCnt = orderCnt;
	}
	public Integer getMsgCnt() {
		return msgCnt;
	}
	public void setMsgCnt(Integer msgCnt) {
		this.msgCnt = msgCnt;
	}

	private Integer followCnt;
	private Integer fansCnt;
	private Integer orderCnt;
	private Integer msgCnt;
}
