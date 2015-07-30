package com.kaim.likeserver.result;

import java.util.List;

import com.kaim.likeserver.dto.OrderInfo;

public class OrderListResult {
	public StatusResult getStatus() {
		return status;
	}
	public void setStatus(StatusResult status) {
		this.status = status;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<OrderInfo> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<OrderInfo> orderList) {
		this.orderList = orderList;
	}

	private StatusResult status = new StatusResult(100, "success");
	private Integer currentPage = 0;
	private Integer totalPage = 0;
	private List<OrderInfo> orderList = null;
	
	public OrderListResult(List<OrderInfo> orderList, Integer currentPage, Integer totalPage)
	{
		this.orderList = orderList;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
	}
	
	public OrderListResult(StatusResult st)
	{
		this.status = st;
	}
}
