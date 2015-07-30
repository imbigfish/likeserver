package com.kaim.likeserver.dto;


public class TeamInfo {
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Integer getRoundSum() {
		return roundSum;
	}
	public void setRoundSum(Integer roundSum) {
		this.roundSum = roundSum;
	}
	public Integer getMatchesPlayed() {
		return matchesPlayed;
	}
	public void setMatchesPlayed(Integer matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}
	public Integer getWins() {
		return wins;
	}
	public void setWins(Integer wins) {
		this.wins = wins;
	}
	public Integer getDraws() {
		return draws;
	}
	public void setDraws(Integer draws) {
		this.draws = draws;
	}
	public Integer getLosses() {
		return losses;
	}
	public void setLosses(Integer losses) {
		this.losses = losses;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}

	public String getTeamUrl() {
		return teamUrl;
	}
	public void setTeamUrl(String teamUrl) {
		this.teamUrl = teamUrl;
	}

	private Integer teamId;
	private Integer leagueId;
	private String teamName;
	private String teamUrl;
	private Integer roundSum;
	private Integer matchesPlayed;
	private Integer wins;
	private Integer draws;
	private Integer losses;
	private Integer points;
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getScores() {
		return scores;
	}
	public void setScores(Integer scores) {
		this.scores = scores;
	}

	private Integer rank;
	private Integer scores;
}
