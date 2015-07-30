package com.kaim.likeserver.dto;

public class StakeDetail {
	public Integer getStakeDetailId() {
		return stakeDetailId;
	}
	public void setStakeDetailId(Integer stakeDetailId) {
		this.stakeDetailId = stakeDetailId;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public Integer getStakeId() {
		return stakeId;
	}
	public void setStakeId(Integer stakeId) {
		this.stakeId = stakeId;
	}
	public Integer getChoice() {
		return choice;
	}
	public void setChoice(Integer choice) {
		this.choice = choice;
	}
	private Integer stakeDetailId;
	private String rule;
	private Integer stakeId;
	private Integer choice;
}
