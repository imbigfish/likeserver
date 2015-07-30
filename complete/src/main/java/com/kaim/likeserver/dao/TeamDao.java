package com.kaim.likeserver.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kaim.likeserver.dto.TeamInfo;

class TeamInfoMapper implements RowMapper<TeamInfo> {

	@Override
	public TeamInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		TeamInfo team = new TeamInfo();
		
		team.setTeamId(rs.getInt("teamid"));
		team.setTeamName(rs.getString("teamname"));
		team.setTeamUrl(rs.getString("teamUrl"));
		team.setLeagueId(rs.getInt("leagueId"));
		team.setScores(rs.getInt("scores"));
		team.setRank(rs.getInt("scores"));
		team.setRoundSum(rs.getInt("roundSum"));
		team.setMatchesPlayed(rs.getInt("matchesPlayed"));
		team.setWins(rs.getInt("wins"));
		team.setLosses(rs.getInt("loses"));
		team.setPoints(rs.getInt("points"));
		team.setDraws(rs.getInt("draws"));
		
		return team;
	}
	
}

@Component
public class TeamDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public TeamDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<TeamInfo> getRankedTeamByLeagueId(Integer leagueId)
	{
		return jdbcTemplate.query("select * from teaminfo where leagueId = ? order by rank", new Object[] {leagueId}, new TeamInfoMapper());
	}
}
