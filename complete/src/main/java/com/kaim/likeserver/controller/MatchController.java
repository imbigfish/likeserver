package com.kaim.likeserver.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import com.kaim.likeserver.dto.OddInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kaim.likeserver.dao.MatchDao;
import com.kaim.likeserver.dto.MatchInfo;
import com.kaim.likeserver.dto.PackMatch;
import com.kaim.likeserver.dto.Recommendation;
import com.kaim.likeserver.dto.StatisticsInfo;
import com.kaim.likeserver.result.MatchResult;
import com.kaim.likeserver.result.RecentMatchResult;
import com.kaim.likeserver.result.OddsResult;
import com.kaim.likeserver.result.RecommendationResult;
import com.kaim.likeserver.result.StatisticsResult;
import com.kaim.likeserver.result.StatusResult;

@RestController
@RequestMapping("/match")
public class MatchController {
	@Autowired
	private MatchDao matchDao;

	@RequestMapping(method=RequestMethod.GET, value="/type/{matchType}")
    public RecentMatchResult getMatchByMatchType(@PathVariable String matchType) {
		try {
			//jqzq or jclq
			List<MatchInfo> matches = matchDao.getRecentMatch(matchType);

			List<PackMatch> packMatches = new ArrayList<PackMatch>();

			Date d = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

			d.setHours(0);
			d.setMinutes(0);
			d.setSeconds(0);
			//初始化五日的数组
			packMatches.add(new PackMatch(sdf.format(new Date(d.getTime())), new ArrayList<MatchInfo>()));
			packMatches.add(new PackMatch(sdf.format(new Date(d.getTime()+24*60*60*1000)),new ArrayList<MatchInfo>()));
			packMatches.add(new PackMatch(sdf.format(new Date(d.getTime()+24*60*60*1000*2)),new ArrayList<MatchInfo>()));
			packMatches.add(new PackMatch(sdf.format(new Date(d.getTime()+24*60*60*1000*3)),new ArrayList<MatchInfo>()));
			packMatches.add(new PackMatch(sdf.format(new Date(d.getTime()+24*60*60*1000*4)),new ArrayList<MatchInfo>()));
			//按照日期分组比赛
			long dif = 0;

			for (int i = 0; i < matches.size(); i++) {
				//System.out.println(matches.get(i).getMatchTime().getTime());
				dif = matches.get(i).getMatchTime().getTime() - d.getTime();

				if(dif<24*60*60*1000){
					packMatches.get(0).add(matches.get(i));
				}else if(dif<24*60*60*1000*2){
					packMatches.get(1).add(matches.get(i));
				}else if(dif<24*60*60*1000*3){
					packMatches.get(2).add(matches.get(i));
				}else if(dif<24*60*60*1000*4){
					packMatches.get(3).add(matches.get(i));
				}else {
					packMatches.get(4).add(matches.get(i));
				}

			}

			if (packMatches.size()>0) {
				RecentMatchResult RecentMatchResult = new RecentMatchResult(packMatches,new StatusResult(100,"success"));
				return RecentMatchResult;
			}else{
				return new RecentMatchResult(new StatusResult(106, "no result"));
			}


		} catch (Exception e) {
			e.printStackTrace();
			
			return new RecentMatchResult(new StatusResult(105, "Datebase Error"));
		}
    }

	@RequestMapping(method=RequestMethod.GET, value="/{matchId}/odds")
	public OddsResult getMatchOddById(@PathVariable Integer matchId)
	{
		OddInfo odd = matchDao.getMatchOddByMatchId(matchId);
		if(odd!=null) {
			return new OddsResult(odd,null);
		}else{
			return new OddsResult(new OddInfo(), new StatusResult(101, "no match odd record found"));
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{matchId}/statistics")
	public StatisticsResult getMatchStatistics(@PathVariable Integer matchId)
	{
		StatisticsResult result = new StatisticsResult();
		List<StatisticsInfo> infoList = new ArrayList<StatisticsInfo>();
		
		for(int i = 0; i < 5; i ++)
		{
			StatisticsInfo info = new StatisticsInfo();
			info.setDataAway("244");
			info.setDataHome("44");
			info.setTitle("something" + i);
			info.setMatchId(matchId);
			
			infoList.add(info);
		}
		
		result.setStatistics(infoList);
		
		return result;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{matchId}/recommendations")
	public RecommendationResult getMatchRecommendationsById(@PathVariable Integer matchId)
	{
		RecommendationResult result = new RecommendationResult();
		
		List<Recommendation> recommendations = new ArrayList<Recommendation>();

		recommendations = matchDao.getMatchRecommendationByMatchId(matchId);

		if(recommendations.size()>0){
			result.setRecommendations(recommendations);
		}else{
			result.setStatus(new StatusResult(107,"no recommendation found"));
		}

		return result;
	}
}

