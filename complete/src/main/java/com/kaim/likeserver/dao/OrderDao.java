package com.kaim.likeserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.kaim.likeserver.dto.ChuanInfo;
import com.kaim.likeserver.dto.OrderInfo;
import com.kaim.likeserver.dto.StakeDetail;
import com.kaim.likeserver.dto.StakeInfo;
import com.kaim.likeserver.dto.UserInfo;
import com.kaim.likeserver.result.OrderListResult;


class OrderMapper implements RowMapper<OrderInfo> {

	@Override
	public OrderInfo mapRow(ResultSet rs, int num) throws SQLException {
		OrderInfo order = new OrderInfo();
		
		order.setGameEn(rs.getString("gameEn"));
		order.setBetWay(rs.getString("betWay"));
		order.setAverageProfit(rs.getString("averageProfit"));
		order.setOrderId(rs.getInt("orderId"));
		order.setOrderStatus(rs.getInt("orderStatus"));
		
		UserInfo user = new UserInfoRowMapper().mapRow(rs, num);
		
		order.setOwnerInfo(user);
		return order;
	}
	
}

class ChuanMapper implements RowMapper<ChuanInfo> {

	@Override
	public ChuanInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		ChuanInfo chuan = new ChuanInfo();
		
		chuan.setChuanId(rs.getInt("chuanId"));
		chuan.setInput(rs.getInt("input"));
		chuan.setOutput(rs.getInt("output"));
		chuan.setOrderId(rs.getInt("orderId"));
		chuan.setRange(rs.getInt("range"));
		
		return chuan;
		
	}
}

class StakeInfoMapper implements RowMapper<StakeInfo> {

	@Override
	public StakeInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		StakeInfo stake = new StakeInfo();
		
		stake.setMatchId(rs.getInt("matchId"));
		stake.setOrderId(rs.getInt("orderId"));
		stake.setStakeId(rs.getInt("stakeId"));
		
		return stake;
	}
}

class StakeDetailMapper implements RowMapper<StakeDetail> {

	@Override
	public StakeDetail mapRow(ResultSet rs, int arg1) throws SQLException {
		StakeDetail stakeDetail = new StakeDetail();
		
		stakeDetail.setStakeDetailId(rs.getInt("stakeDetailId"));
		stakeDetail.setStakeId(rs.getInt("stakeId"));
		stakeDetail.setRule(rs.getString("rule"));
		stakeDetail.setChoice(rs.getInt("choice"));
		
		return stakeDetail;
	}
	
}

@Component
public class OrderDao {
	private JdbcTemplate jdbcTemplate;
	private static Integer orderPerPage = 30;

	@Autowired
	public OrderDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public boolean createOrder(final OrderInfo order, String userId)
	{
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator(){
				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement("insert into orderInfo (ownerId, gameEn, betWay, averageProfit, orderStatus, dealine, isOptimization, gentime) values (?, ?, ?, ?, ?, ?, ? now())");
					
					ps.setString(1, order.getOwnerInfo().getPersonId());
					ps.setString(2, order.getGameEn());
					ps.setString(3, order.getBetWay());
					ps.setString(4, order.getAverageProfit());
					ps.setInt(5, order.getOrderStatus());
					ps.setString(6, order.getDeadline());
					ps.setInt(7, order.isOptimization()? 1 : 0);
					
					return ps;
				}
			}, keyHolder);
			
			for(ChuanInfo chuan : order.getChuanList())
			{
				jdbcTemplate.update("insert into chuanInfo (orderId, range, input, output) values (?, ?, ?, ?)", new Object[] {keyHolder.getKey().intValue(), chuan.getRange(), chuan.getInput(), chuan.getOutput()});
			}
			
			for(final StakeInfo stake : order.getStakeInfos())
			{
				KeyHolder stakeInfoKey = new GeneratedKeyHolder();
				final KeyHolder orderHolder = keyHolder;
				
				jdbcTemplate.update(new PreparedStatementCreator(){

					@Override
					public PreparedStatement createPreparedStatement(Connection connection)
							throws SQLException {
						PreparedStatement ps = connection.prepareStatement("insert into chuanInfo (matchId, orderId) values (?, ?)");
						
						ps.setInt(1, stake.getMatchId());
						ps.setInt(2, orderHolder.getKey().intValue());
						
						return ps;
					}
					
				}, stakeInfoKey);
				
				for(final StakeDetail detail : stake.getStakeDetails())
				{
					jdbcTemplate.update("insert into stakeDetail (rule, stakeId, choice) values (?, ?, ?)", detail.getRule(), detail.getStakeId(), detail.getChoice());
				}
			}
			
			return true;
		} catch (InvalidDataAccessApiUsageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public OrderInfo getOrderById(Integer orderId)
	{
		OrderInfo order = jdbcTemplate.queryForObject("select * from orderInfo oi, userInfo ui where oi.ownerId = ui.loginId and oi.orderId = ?", new Object[] {orderId}, new OrderMapper());
		
		enrichOrder(order);
		
		return order;
	}
	
	private void enrichOrder(OrderInfo order)
	{
		List<ChuanInfo> chuanList = jdbcTemplate.query("select * from chuanInfo where orderId = ?", new Object[]{order.getOrderId()}, new ChuanMapper());
		List<StakeInfo> stakeList = jdbcTemplate.query("select * from stakeInfo where orderId = ?", new Object[] {order.getOrderId()}, new StakeInfoMapper());
		
		order.setChuanList(chuanList);
		for(StakeInfo stake : stakeList)
		{
			List<StakeDetail> stakeDetails = jdbcTemplate.query("select * from stakeDetail where stakeId = ?", new Object[] {stake.getStakeId()}, new StakeDetailMapper());
			stake.setStakeDetails(stakeDetails);
		}
		order.setStakeInfos(stakeList);
	}
	
	public OrderListResult getOrderByUserId(String userId, Integer page)
	{
		Integer total = jdbcTemplate.queryForObject("select count(1) from orderInfo where ownerId = ?", new Object[] {userId}, Integer.class);
		List<OrderInfo> orderList = jdbcTemplate.query("select * from orderInfo oi, userInfo ui where oi.ownerId = ui.loginId and oi.ownerId = ? limit ?, ?", new Object[] {userId, (page - 1) * orderPerPage, page * orderPerPage}, new OrderMapper());
		
		for(OrderInfo order : orderList)
		{
			enrichOrder(order);
		}
		
		return new OrderListResult(orderList, page, total);
	}

  public OrderListResult getDailyOrder(Integer page) {
    Integer total = jdbcTemplate.queryForObject(
        "select count(1) from orderInfo", Integer.class);
    List<OrderInfo> orderList = jdbcTemplate
        .query("select * from orderInfo limit ?, ?", new Object[] {
            (page - 1) * orderPerPage, page * orderPerPage }, new OrderMapper());

    for (OrderInfo order : orderList) {
      enrichOrder(order);
    }

    return new OrderListResult(orderList, page, total);
  }
}
