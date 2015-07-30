package com.kaim.likeserver.dto;

import java.sql.Timestamp;

public class OddInfo {
	private int matchId;
	private Timestamp updateTime;
	private float oddWin;
	private float oddDraw;
	private float oddLose;
	private String provider;

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public float getOddWin() {
		return oddWin;
	}

	public void setOddWin(float oddWin) {
		this.oddWin = oddWin;
	}

	public float getOddDraw() {
		return oddDraw;
	}

	public void setOddDraw(float oddDraw) {
		this.oddDraw = oddDraw;
	}

	public float getOddLose() {
		return oddLose;
	}

	public void setOddlose(float oddLose) {
		this.oddLose = oddLose;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
}
