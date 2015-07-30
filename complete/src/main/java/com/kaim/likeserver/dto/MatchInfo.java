package com.kaim.likeserver.dto;

import java.sql.Timestamp;

public class MatchInfo {
	
	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getMatchCode() {
		return matchCode;
	}

	public void setMatchCode(String matchCode) {
		this.matchCode = matchCode;
	}

	public String getMatchLeague() {
		return matchLeague;
	}

	public void setMatchLeague(String matchLeague) {
		this.matchLeague = matchLeague;
	}

	public Timestamp getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Timestamp matchTime) {
		this.matchTime = matchTime;
	}

	public String getTeamHost() {
		return teamHost;
	}

	public void setTeamHost(String teamHost) {
		this.teamHost = teamHost;
	}

	public String getTeamAway() {
		return teamAway;
	}

	public void setTeamAway(String teamAway) {
		this.teamAway = teamAway;
	}

	public Integer getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(Integer matchStatus) {
		this.matchStatus = matchStatus;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public Integer getScoreHost() {
		return scoreHost;
	}

	public void setScoreHost(Integer scoreHost) {
		this.scoreHost = scoreHost;
	}

	public Integer getScoreAway() {
		return scoreAway;
	}

	public void setScoreAway(Integer scoreAway) {
		this.scoreAway = scoreAway;
	}

	public String getHalfScores() {
		return halfScores;
	}

	public void setHalfScores(String halfScores) {
		this.halfScores = halfScores;
	}

	public Float getConcede() {
		return concede;
	}

	public void setConcede(Float concede) {
		this.concede = concede;
	}

	public String getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(String messageCount) {
		this.messageCount = messageCount;
	}

	public boolean isTotal() {
		return isTotal;
	}

	public void setTotal(boolean isTotal) {
		this.isTotal = isTotal;
	}
	public String getTeamHostUrl() {
		return teamHostUrl;
	}

	public void setTeamHostUrl(String teamHostUrl) {
		this.teamHostUrl = teamHostUrl;
	}

	public String getTeamAwayUrl() {
		return teamAwayUrl;
	}

	public void setTeamAwayUrl(String teamAwayUrl) {
		this.teamAwayUrl = teamAwayUrl;
	}

	private String matchId;
	private String matchCode;
	private String matchLeague;
	private Timestamp matchTime;
	private String teamHost;
	private String teamAway;
	private String teamHostUrl;
	private String teamAwayUrl;
	private Integer matchStatus;
	private String scores;
	private Integer scoreHost;
	private Integer scoreAway;
	private String halfScores;
	private Float concede;
	private String messageCount;
	private boolean isTotal;
}
