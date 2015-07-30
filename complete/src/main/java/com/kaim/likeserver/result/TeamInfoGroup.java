package com.kaim.likeserver.result;

import java.util.List;

import com.kaim.likeserver.dto.TeamInfo;

public class TeamInfoGroup {
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<TeamInfo> getTeamInfos() {
		return teamInfos;
	}
	public void setTeamInfos(List<TeamInfo> teamInfo) {
		this.teamInfos = teamInfo;
	}
	private String groupName = "";
	private List<TeamInfo> teamInfos;
}
