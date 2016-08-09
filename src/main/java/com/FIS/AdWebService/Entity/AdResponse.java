package com.FIS.AdWebService.Entity;

import java.util.List;

public class AdResponse {

	String respMsg;
	int respCd;
	List<AdRequest> adInfo;
	
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public int getRespCd() {
		return respCd;
	}
	public void setRespCd(int respCd) {
		this.respCd = respCd;
	}
	public List<AdRequest> getAdInfo() {
		return adInfo;
	}
	public void setAdInfo(List<AdRequest> adInfo) {
		this.adInfo = adInfo;
	}
}
