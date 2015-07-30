package com.kaim.likeserver.dto;

public class StatisticsInfo {
	public Integer getMatchId() {
		return matchId;
	}
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDataHome() {
		return dataHome;
	}
	public void setDataHome(String dataHome) {
		this.dataHome = dataHome;
	}
	public String getDataAway() {
		return dataAway;
	}
	public void setDataAway(String dataAway) {
		this.dataAway = dataAway;
	}
	private Integer matchId;
	private String title;
	private String dataHome;
	private String dataAway;
}
