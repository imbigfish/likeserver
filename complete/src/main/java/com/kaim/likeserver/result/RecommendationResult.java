package com.kaim.likeserver.result;

import java.util.List;

import com.kaim.likeserver.dto.Recommendation;

public class RecommendationResult {
	public StatusResult getStatus() {
		return status;
	}
	public void setStatus(StatusResult status) {
		this.status = status;
	}
	public List<Recommendation> getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(List<Recommendation> recommendations) {
		this.recommendations = recommendations;
	}
	private StatusResult status = new StatusResult();
	private  List<Recommendation> recommendations;
}
