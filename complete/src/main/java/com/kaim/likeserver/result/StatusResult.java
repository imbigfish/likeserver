package com.kaim.likeserver.result;

public class StatusResult {
	private Integer resultCode = 100;
	private String resultDesc = "Success";

	public Integer getResultCode() {
		return resultCode;
	}
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	
	public StatusResult(Integer resultCode, String resultDesc)
	{
		this.resultCode = resultCode;
		this.resultDesc = resultDesc;
	}
	
	public StatusResult()
	{}
}
