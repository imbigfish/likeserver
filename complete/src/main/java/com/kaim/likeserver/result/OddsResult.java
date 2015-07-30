package com.kaim.likeserver.result;


import com.kaim.likeserver.dto.OddInfo;

import java.sql.Timestamp;

public class OddsResult {
	private int matchId;
	private Timestamp updateTime;
	private float oddWin;
	private float oddDraw;
	private float oddLose;
	private String provider;
	private StatusResult status;

	public OddsResult(StatusResult status){
		this.status = status;
	}

	public OddsResult(OddInfo odd, StatusResult status){
		this.matchId = odd.getMatchId();
		this.updateTime = odd.getUpdateTime();
		this.oddWin = odd.getOddWin();
		this.oddDraw = odd.getOddDraw();
		this.oddLose = odd.getOddLose();
		this.provider = odd.getProvider();
		this.status = status;
	}

	public StatusResult getStatus() {
		return status;
	}

	public void setStatus(StatusResult status) {
		this.status = status;
	}

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

	public void setOddLose(float oddLose) {
		this.oddLose = oddLose;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}



}
