package com.kaim.likeserver.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kaim.likeserver.dto.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kaim.likeserver.dto.MatchInfo;
import com.kaim.likeserver.dto.OddInfo;

class MatchRowMapper implements RowMapper<MatchInfo> {
	@Override
	public MatchInfo mapRow(ResultSet rs, int num) throws SQLException {
		MatchInfo m = new MatchInfo();
		
		m.setMatchId(rs.getString("matchId"));
		m.setMatchCode(rs.getString("matchCode"));
		m.setMatchLeague(rs.getString("matchLeague"));
		m.setMatchTime(rs.getTimestamp("matchTime"));
		m.setTeamHost(rs.getString("teamHost"));
		m.setTeamHostUrl(rs.getString("teamHostUrl"));
		m.setTeamAway(rs.getString("teamAway"));
		m.setTeamAwayUrl(rs.getString("teamAwayUrl"));
		m.setMatchStatus(rs.getInt("matchStatus"));
		m.setScoreHost(rs.getInt("scoreHost"));
		m.setScoreAway(rs.getInt("scoreAway"));
		m.setConcede(rs.getFloat("concede"));

		return m;
	}
}

class OddRowMapper implements RowMapper<OddInfo> {
	@Override
	public OddInfo mapRow(ResultSet rs, int num) throws SQLException {
		OddInfo odd = new OddInfo();
		if(rs.getInt("matchId")>0) {
			odd.setMatchId(rs.getInt("matchId"));
			odd.setUpdateTime(rs.getTimestamp("updateTime"));
			odd.setOddWin(rs.getFloat("oddWin"));
			odd.setOddDraw(rs.getFloat("oddDraw"));
			odd.setOddlose(rs.getFloat("oddLose"));
			odd.setProvider(rs.getString("provider"));
			return odd;
		}else{
			return null;
		}
	}
}

class RecommendationRowMapper implements RowMapper<Recommendation> {
	@Override
	public Recommendation mapRow(ResultSet rs, int num) throws SQLException {
		Recommendation rec = new Recommendation();
		if(rs.getInt("id")>0) {
			rec.setMatchId(rs.getInt("matchId"));
			rec.setLogoUrl(rs.getString("logoUrl"));
			rec.setTitle(rs.getString("title"));
			rec.setSource(rs.getString("source"));
			rec.setTime(rs.getTimestamp("time"));
			rec.setContenxtUrl(rs.getString("contenxtUrl"));
			return rec;
		}else{
			return null;
		}
	}
}


@Component
public class MatchDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public MatchDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<MatchInfo> getTodayMatchByType(String matchType)
	{
		return jdbcTemplate.query("SELECT m.*, h.teamName as teamHost, a.teamName as teamAway, h.teamUrl as teamHostUrl, a.teamUrl as teamAwayUrl FROM matchinfo m join teaminfo h on m.teamHostId = h.teamId join teaminfo a on m.teamAwayId = a.teamId where m.matchCode = ? and DATE(m.matchTime) = CURDATE()", new Object[]{matchType}, new MatchRowMapper());
	}

	public List<MatchInfo> getTodayMatchByLeagueId(Integer leagueId)
	{
		return jdbcTemplate.query("SELECT m.*, h.teamName as teamHost, a.teamName as teamAway, h.teamUrl as teamHostUrl, a.teamUrl as teamAwayUrl FROM matchinfo m join teaminfo h on m.teamHostId = h.teamId join teaminfo a on m.teamAwayId = a.teamId where m.leagueId = ? and DATE(m.matchTime) = CURDATE()", new Object[]{leagueId}, new MatchRowMapper());
	}

	public List<MatchInfo> getMatch(String matchType,String date)
	{
		return jdbcTemplate.query("SELECT m.*, h.teamName as teamHost, a.teamName as teamAway, h.teamUrl as teamHostUrl, a.teamUrl as teamAwayUrl FROM matchinfo m join teaminfo h on m.teamHostId = h.teamId join teaminfo a on m.teamAwayId = a.teamId where m.matchCode = ? and DATE(m.matchTime) = ? ", new Object[]{matchType,date}, new MatchRowMapper());
	}

	public OddInfo getMatchOddByMatchId(int matchId){
		return jdbcTemplate.queryForObject("SELECT * FROM matchodd WHERE matchId = ? AND provider='zgzc' ORDER BY updateTime DESC LIMIT 1", new Object[]{matchId}, new OddRowMapper());
	}

	public List<MatchInfo> getRecentMatch(String matchType){
		return jdbcTemplate.query("SELECT m.*, h.teamName as teamHost, a.teamName as teamAway, h.teamUrl as teamHostUrl, a.teamUrl as teamAwayUrl FROM matchinfo m join teaminfo h on m.teamHostId = h.teamId join teaminfo a on m.teamAwayId = a.teamId where m.matchCode = ? and DATE(m.matchTime) >= CURDATE() and DATE(m.matchTime)<=DATE_ADD(CURDATE(),INTERVAL 4 DAY) ORDER BY m.matchTime ASC", new Object[]{matchType}, new MatchRowMapper());
	}

	public List<Recommendation> getMatchRecommendationByMatchId(int matchId){
		return jdbcTemplate.query("SELECT * FROM recommendation WHERE matchId = ? ", new Object[]{matchId}, new RecommendationRowMapper());
	}
}
