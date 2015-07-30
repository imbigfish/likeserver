package com.kaim.likeserver.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class RelationshipDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public RelationshipDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public boolean followUser(String loginId, String followingLoginId)
	{
		try {
			return jdbcTemplate.update("insert into userrelationship (userId, followerID) values ( (select userId from userInfo where loginId = ?), (select userId from userInfo where loginId = ?))", followingLoginId, loginId) == 1;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean unfollowUser(String loginId, String followingLoginId)
	{
		try {
			return jdbcTemplate.update("delete from userrelationship where userId = (select userId from userInfo where loginId = ? ) and followerId = (select userId from userInfo where loginId = ?)", new Object[] {followingLoginId, loginId}) == 1;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
