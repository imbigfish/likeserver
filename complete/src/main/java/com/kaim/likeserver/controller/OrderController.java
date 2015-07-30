package com.kaim.likeserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kaim.likeserver.dao.OrderDao;
import com.kaim.likeserver.dao.UserDao;
import com.kaim.likeserver.dto.OrderInfo;
import com.kaim.likeserver.result.OrderListResult;
import com.kaim.likeserver.result.OrderResult;
import com.kaim.likeserver.result.StatusResult;

@RestController
@RequestMapping("/order")
public class OrderController {
  @Autowired
  private OrderDao orderDao;

  @Autowired
  private UserDao userDao;

  @RequestMapping(method = RequestMethod.POST, value = "/create/{userId}/{accessToken}")
  public StatusResult createOrder(@RequestBody OrderInfo order,
      @PathVariable String userId, @PathVariable String accessToken) {

    try {
      if (userDao.checkUserSession(userId, accessToken)) {
        if (orderDao.createOrder(order, userId)) {
          return new StatusResult(1000, "Success");
        } else {
          return new StatusResult(1003, "Create Order Failed");
        }
      } else {
        return new StatusResult(1001, "Invalid Token");
      }
    } catch (Exception e) {
      e.printStackTrace();

      return new StatusResult(1008, "Unknown server error");
    }
  }

  @RequestMapping(method = RequestMethod.GET, value = "/detail/{orderId}")
  public OrderResult getOrderInfoById(@PathVariable Integer orderId) {
    try {
      return new OrderResult(orderDao.getOrderById(orderId));
    } catch (Exception e) {
      e.printStackTrace();
      return new OrderResult(new StatusResult(108, "Database error"));
    }
  }

  @RequestMapping(method = RequestMethod.GET, value = "/test")
  public OrderResult getTestOrder() {
    OrderResult order = new OrderResult(new StatusResult());

    OrderInfo o = new OrderInfo();
    o.setAverageProfit("1.1");
    o.setBetWay("my");
    o.setDeadline("dd");
    o.setGameEn("d");
    order.setOrder(o);

    return order;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}/{page}/{accessToken}")
  public OrderListResult getOrderInfoByUserId(@PathVariable String userId,
      @PathVariable Integer page, @PathVariable String accessToken) {
    try {
      if (userDao.checkUserSession(userId, accessToken)) {
        return orderDao.getOrderByUserId(userId, page);
      } else {
        return new OrderListResult(new StatusResult(103, "Invalid Token"));
      }
    } catch (Exception e) {
      e.printStackTrace();
      return new OrderListResult(new StatusResult(108, "DB error"));
    }
  }

  // 今日稳单
  @RequestMapping(method = RequestMethod.GET, value = "/dailyorder/{page}")
  public OrderListResult getDailyOrderInfo(@PathVariable Integer page) {
    try {
      return orderDao.getDailyOrder(page);
    } catch (Exception e) {
      e.printStackTrace();
      return new OrderListResult(new StatusResult(108, "DB error"));
    }
  }
}
