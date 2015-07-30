package com.kaim.likeserver.result;

import java.util.Date;
import java.util.List;

import com.kaim.likeserver.dto.MatchInfo;

/*
class MatchInfo {
	private Match match;
	
}
*/

public class MatchResult {
	public List<MatchInfo> getMatchInfosByDay() {
		return matchInfosByDay;
	}
	public void setMatchInfosByDay(List<MatchInfo> matchInfosByDay) {
		this.matchInfosByDay = matchInfosByDay;
	}
	public StatusResult getStatus() {
		return status;
	}
	public void setStatus(StatusResult status) {
		this.status = status;
	}
	public Date getMatchDay() {
		return matchDay;
	}
	public void setMatchDay(Date matchDay) {
		this.matchDay = matchDay;
	}
	public MatchResult(StatusResult sr)
	{
		this.status = sr;
	}

	private List<MatchInfo> matchInfosByDay = null;
	private StatusResult status = new StatusResult(100, "success");
	private Date matchDay = new Date();
	
	public MatchResult(List<MatchInfo> m)
	{
		this.matchInfosByDay = m;
	}


}
