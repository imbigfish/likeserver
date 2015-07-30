package com.kaim.likeserver.dto;

import java.sql.Timestamp;

public class Recommendation {
	public Integer getMatchId() {
		return matchId;
	}
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getContenxtUrl() {
		return contenxtUrl;
	}
	public void setContenxtUrl(String contenxtUrl) {
		this.contenxtUrl = contenxtUrl;
	}
	private Integer matchId;
	private String logoUrl;
	private String title;
	private String source;
	private Timestamp time;
	private String contenxtUrl;
}
