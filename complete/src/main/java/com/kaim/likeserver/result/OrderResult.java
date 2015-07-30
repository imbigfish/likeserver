package com.kaim.likeserver.result;

import com.kaim.likeserver.dto.OrderInfo;

public class OrderResult {
	public StatusResult getStatus() {
		return status;
	}
	public void setStatus(StatusResult status) {
		this.status = status;
	}
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
	}

	private StatusResult status = new StatusResult(100, "Success");
	private OrderInfo order = null;
	
	public OrderResult(StatusResult rs)
	{
		this.status = rs;
	}

	public OrderResult(OrderInfo o)
	{
		this.order = o;
	}
}
