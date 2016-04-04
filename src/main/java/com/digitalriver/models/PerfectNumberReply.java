package com.digitalriver.models;

public class PerfectNumberReply {

	private String requestNo;
	
	private boolean result;

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	@Override
	public String toString() {
		if(result)
			return requestNo + " is perfect number";
		else
			return requestNo + " is not perfect number";
	}
}
