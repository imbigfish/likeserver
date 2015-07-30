package com.kaim.likeserver.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kaim.likeserver.dto.LeagueInfo;

class LeagueMapper implements RowMapper<LeagueInfo>
{

	@Override
	public LeagueInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		LeagueInfo league = new LeagueInfo();
		
		league.setLeagueId(rs.getInt("leagueId"));
		league.setUefaChampionLeagueNumer(rs.getInt("uefaChampionLeagueNum"));
		league.setUefaEuropeLeagueNumer(rs.getInt("uefaEuropeLeagueNum"));
		league.setRelegationNumber(rs.getInt("relegationNum"));
		league.setLeagueName(rs.getString("leagueName"));
		
		return league;
	}
}
@Component
public class LeagueDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public LeagueDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public LeagueInfo getLeagueInfoById(Integer LeagueId)
	{
		try {
			return jdbcTemplate.queryForObject("select * from leagueInfo where leagueId = ?", new Object[] {LeagueId}, new LeagueMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}
