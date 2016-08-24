/*
 * @Author Prachi Patwa
 * 
 * The AdResponse class has structure of the output when GET request is received
 * 
 */
package com.FIS.AdWebService.Entity;

import java.util.List;

public class AdResponse {

	String respMsg;
	List<AdRequest> adInfo;
	
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public List<AdRequest> getAdInfo() {
		return adInfo;
	}
	public void setAdInfo(List<AdRequest> adInfo) {
		this.adInfo = adInfo;
	}
}
