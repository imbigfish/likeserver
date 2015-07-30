package com.kaim.likeserver.dto;

public class LeagueInfo {
	public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}

	public Integer getUefaChampionLeagueNumer() {
		return uefaChampionLeagueNumer;
	}

	public void setUefaChampionLeagueNumer(Integer uefaChampionLeagueNumer) {
		this.uefaChampionLeagueNumer = uefaChampionLeagueNumer;
	}

	public Integer getUefaEuropeLeagueNumer() {
		return uefaEuropeLeagueNumer;
	}

	public void setUefaEuropeLeagueNumer(Integer uefaEuropeLeagueNumer) {
		this.uefaEuropeLeagueNumer = uefaEuropeLeagueNumer;
	}

	public Integer getRelegationNumber() {
		return relegationNumber;
	}

	public void setRelegationNumber(Integer relegationNumber) {
		this.relegationNumber = relegationNumber;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	private Integer leagueId;
	private Integer uefaChampionLeagueNumer;
	private Integer uefaEuropeLeagueNumer;
	private Integer relegationNumber;
	private String leagueName;

}
