package com.kaim.likeserver.result;

import java.util.List;

import com.kaim.likeserver.dto.StatisticsInfo;

public class StatisticsResult {
	public StatusResult getStatus() {
		return status;
	}
	public void setStatus(StatusResult status) {
		this.status = status;
	}
	public List<StatisticsInfo> getStatistics() {
		return statistics;
	}
	public void setStatistics(List<StatisticsInfo> statistics) {
		this.statistics = statistics;
	}
	private StatusResult status = new StatusResult();
	private List<StatisticsInfo> statistics;
}
