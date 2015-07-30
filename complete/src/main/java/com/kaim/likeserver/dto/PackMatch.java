package com.kaim.likeserver.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class PackMatch{
	private String matchDay;
	private List<MatchInfo> matchInfosByDay = null;

	public String getMatchDay() {
		return matchDay;
	}

	public void setMatchDay(String matchDay) {
		this.matchDay = matchDay;
	}

	public List<MatchInfo> getMatchInfosByDay() {
		return matchInfosByDay;
	}

	public void setMatchInfosByDay(List<MatchInfo> matchInfosByDay) {
		this.matchInfosByDay = matchInfosByDay;
	}

	public PackMatch(String matchDay, List<MatchInfo> matchInfosByDay) {
		this.matchDay = matchDay;
		this.matchInfosByDay = matchInfosByDay;
	}

	public void add(MatchInfo mi){
		this.getMatchInfosByDay().add(mi);
	}
}

