package com.kaim.likeserver.dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.kaim.likeserver.dto.UserInfo;
import com.kaim.likeserver.result.UserResult;

class UserInfoRowMapper implements RowMapper<UserInfo> {
	@Override
	public UserInfo mapRow(ResultSet rs, int num) throws SQLException {
		UserInfo user = new UserInfo();

		user.setEmail(rs.getString("email"));
		user.setNickName(rs.getString("nickName"));
		user.setPersonId(rs.getString("loginId"));
		user.setUserId(rs.getInt("userId"));
		
		try {
			user.setFollowerStatus(rs.getBoolean("followerStatus"));
			user.setFollowingStatus(rs.getBoolean("followingStatus"));
		} catch (Exception e) {
			//Ignore
		}

		return user;
	}
}

@Component
public class UserDao {
	private JdbcTemplate jdbcTemplate;
	private static Integer ItemPerPage = 20;
	private static long tokenValidInterval = 1000L * 60 * 60 * 24 * 30; // One
																		// month
	private static long salt = 123321123123L;

	@Autowired
	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public UserInfo getUserInfoByLoginId(String loginId) {
		try {
			return jdbcTemplate.queryForObject(
					"select * from userinfo where loginId = ?",
					new Object[] { loginId }, new UserInfoRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserInfo getUserInfoByEmail(String email) {
		try {
			return jdbcTemplate.queryForObject(
					"select * from userinfo where email = ?",
					new Object[] { email }, new UserInfoRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserInfo loginUser(String loginId, String password) {
		try {
			UserInfo user = jdbcTemplate
					.queryForObject(
							"select * from userinfo where loginId = ? and password = ?",
							new Object[] { loginId, password }, new UserInfoRowMapper());

			user.setToken(updateUerSession(loginId));
			return user;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserInfo createUser(final UserInfo user) {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection
							.prepareStatement(
									"insert into userinfo (loginId, nickName, email, password) values (?, ?, ?, ?)",
									Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, user.getPersonId());
					ps.setString(2, user.getNickName());
					ps.setString(3, user.getEmail());
					ps.setString(4, user.getPassword());
					return ps;
				}
			}, keyHolder);
			user.setPassword("");
			user.setUserId(keyHolder.getKey().intValue());
			user.setToken(this.createUserSession(user.getPersonId()));
			return user;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<UserInfo> getAllUser() {
		try {
			return jdbcTemplate.query("select * from userinfo", new UserInfoRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserResult getUserFollower(String loginId, Integer page) {
		try {
			Integer total = jdbcTemplate
					.queryForObject(
							"select count(1) from userinfo as u join userrelationship as r on u.userId = r.userId join userinfo f  on r.followerId = f.userId where u.loginId = ?",
							new Object[] { loginId }, Integer.class);
			List<UserInfo> userList = jdbcTemplate
					.query("select f.*, 1 as followingStatus, "
							+ "(select count(1) from userrelationship rr where rr.followerId = u.userId and rr.userId = f.userId) as followerStatus "
							+ "from userinfo as u join userrelationship as r on u.userId = r.userId join userinfo f  on r.followerId = f.userId where u.loginId = ? limit ?, ?",
							new Object[] { loginId, (page - 1) * ItemPerPage,
									page * ItemPerPage }, new UserInfoRowMapper());

			return new UserResult(userList, page, (total / ItemPerPage)
					+ (((total % ItemPerPage) == 0) ? 0 : 1));

		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserResult getUserFollowing(String loginId, Integer page) {
		try {
			Integer total = jdbcTemplate
					.queryForObject(
							"select count(1) from userinfo as u join userrelationship as r on u.userId = r.userId join userinfo f  on r.followerId = f.userId where u.loginId = ?",
							new Object[] { loginId }, Integer.class);
			List<UserInfo> userList = jdbcTemplate
					.query("select f.*, 1 as followerStatus, "
							+ "(select count(1) from userrelationship rr where rr.userId = u.userId and rr.followerId = f.userId) as followingStatus "
							+ "from userinfo as u join userrelationship as r on u.userId = r.followerId join userinfo f  on r.userId = f.userId where u.loginId = ? limit ?, ?",
							new Object[] { loginId, (page - 1) * ItemPerPage,
									page * ItemPerPage }, new UserInfoRowMapper());

			return new UserResult(userList, page, (total / ItemPerPage)
					+ (((total % ItemPerPage) == 0) ? 0 : 1));

		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserInfo loginUsingSession(String loginId, String token) {
		if (checkUserSession(loginId, token)) {
			UserInfo user = getUserInfoByLoginId(loginId);
			user.setToken(token);
			return user;
		} else {
			return null;
		}
	}

	public boolean checkUserSession(String loginId, String token) {
		Timestamp time = new Timestamp(System.currentTimeMillis()
				- tokenValidInterval);
		return jdbcTemplate
				.queryForObject(
						"select count(1) from userSession where loginId = ? and userToken = ? and tokenGenTime > ?",
						new Object[] { loginId, token, time }, Integer.class)
				.equals(1);
	}

	public boolean logoutUser(String loginId, String token) {
		return jdbcTemplate
				.update("update userSession set userToken = '', tokenGenTime = null where loginId = ? and userToken = ?",
						loginId, token) == 1;
	}

	public String updateUerSession(String loginId) {
		try {
			long currentTimestamp = System.currentTimeMillis();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(loginId.getBytes());
			md.update(Long.toBinaryString(currentTimestamp).getBytes());
			md.update(Long.toHexString(salt).getBytes());

			String token = md.digest().toString();
			jdbcTemplate
					.update("update userSession set userToken = ?, tokenGenTime = ? where loginId = ?",
							new Object[] { token,
									new Timestamp(currentTimestamp), loginId });

			return token;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String createUserSession(String loginId) {
		jdbcTemplate.update("insert into userSession (loginId) values (?)",
				new Object[] { loginId });
		return updateUerSession(loginId);
	}
	
	/*
	public static void main(String[] args) throws Exception
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update("123456".getBytes());
		byte[] m = md.digest();
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < m.length; i++)
		{
			sb.append(m[i]);
		}
		
		System.out.println(sb.toString());
	}
	*/
}