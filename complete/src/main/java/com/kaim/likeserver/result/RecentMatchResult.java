package com.kaim.likeserver.result;

import com.kaim.likeserver.dto.MatchInfo;
import com.kaim.likeserver.dto.PackMatch;
import java.util.Date;
import java.util.List;


public class RecentMatchResult {
	private List<PackMatch> matchInfos = null;
	private StatusResult status = new StatusResult(100, "success");

	public List<PackMatch> getMatchInfos() {
		return matchInfos;
	}

	public void setMatchInfos(List<PackMatch> matchInfos) {
		this.matchInfos = matchInfos;
	}

	public StatusResult getStatus() {
		return status;
	}

	public void setStatus(StatusResult status) {
		this.status = status;
	}

	public RecentMatchResult(StatusResult status) {
		this.status = status;
	}

	public RecentMatchResult(List<PackMatch> matchInfos, StatusResult status) {
		this.matchInfos = matchInfos;
		this.status = status;
	}
}
