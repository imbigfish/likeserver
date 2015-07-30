package com.kaim.likeserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kaim.likeserver.dao.LeagueDao;
import com.kaim.likeserver.dao.MatchDao;
import com.kaim.likeserver.dao.TeamDao;
import com.kaim.likeserver.dto.TeamInfo;
import com.kaim.likeserver.result.LeagueResult;
import com.kaim.likeserver.result.TeamInfoGroup;
@RestController
@RequestMapping("/league")
public class LeagueController {

	@Autowired
	private LeagueDao leagueDao;
	
	@Autowired
	private MatchDao matchDao;
	
	@Autowired
	private TeamDao teamDao;

	@RequestMapping(method=RequestMethod.GET, value="/{leagueId}/rank")
    public LeagueResult getLeagueRankById(@PathVariable Integer leagueId) {
		LeagueResult result = new LeagueResult();
		
		result.setLeagueInfo(leagueDao.getLeagueInfoById(leagueId));
		result.setKickoutInfo(matchDao.getTodayMatchByLeagueId(leagueId));
		
		List<TeamInfo> teamInfos = teamDao.getRankedTeamByLeagueId(leagueId);
		TeamInfoGroup g = new TeamInfoGroup();
		g.setTeamInfos(teamInfos);
		List<TeamInfoGroup> rankInfo = new ArrayList<TeamInfoGroup>();
		
		rankInfo.add(g);
		
		result.setRankInfos(rankInfo);
		
		return result;
	}
}