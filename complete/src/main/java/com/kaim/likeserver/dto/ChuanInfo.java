package com.kaim.likeserver.dto;

public class ChuanInfo {
	public Integer getChuanId() {
		return chuanId;
	}
	public void setChuanId(Integer chuanId) {
		this.chuanId = chuanId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getRange() {
		return range;
	}
	public void setRange(Integer range) {
		this.range = range;
	}
	public Integer getInput() {
		return input;
	}
	public void setInput(Integer input) {
		this.input = input;
	}
	public Integer getOutput() {
		return output;
	}
	public void setOutput(Integer output) {
		this.output = output;
	}
	private Integer chuanId;
	private Integer orderId;
	private Integer range;
	private Integer input;
	private Integer output;
}
