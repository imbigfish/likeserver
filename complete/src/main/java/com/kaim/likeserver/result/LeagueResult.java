package com.kaim.likeserver.result;

import java.util.List;

import com.kaim.likeserver.dto.LeagueInfo;
import com.kaim.likeserver.dto.MatchInfo;

public class LeagueResult {

	public List<TeamInfoGroup> getRankInfos() {
		return rankInfos;
	}
	public void setRankInfos(List<TeamInfoGroup> rankInfo) {
		this.rankInfos = rankInfo;
	}
	public List<MatchInfo> getKickoutInfo() {
		return kickoutInfo;
	}
	public void setKickoutInfo(List<MatchInfo> kickoutInfo) {
		this.kickoutInfo = kickoutInfo;
	}
	public StatusResult getStatus() {
		return status;
	}
	public void setStatus(StatusResult status) {
		this.status = status;
	}
	public LeagueInfo getLeagueInfo() {
		return leagueInfo;
	}
	public void setLeagueInfo(LeagueInfo leagueInfo) {
		this.leagueInfo = leagueInfo;
	}
	
	public boolean isCup() {
		return isCup;
	}
	public void setCup(boolean isCup) {
		this.isCup = isCup;
	}

	private boolean isCup = false;
	private List<TeamInfoGroup> rankInfos;
	private List<MatchInfo> kickoutInfo;
	private LeagueInfo leagueInfo;
	private StatusResult status = new StatusResult();
}
