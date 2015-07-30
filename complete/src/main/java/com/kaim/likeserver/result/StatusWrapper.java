package com.kaim.likeserver.result;

public class StatusWrapper {
	private StatusResult status;

	public StatusResult getStatus() {
		return status;
	}

	public void setStatus(StatusResult status) {
		this.status = status;
	}
	
	public StatusWrapper(StatusResult status)
	{
		this.status = status;
	}
}
