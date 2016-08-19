/*
 * @Author Prachi Patwa
 * 
 * AdResponse class has structure of request when an Ad is created and its details
 * 
 */
package com.FIS.AdWebService.Entity;

public class AdRequest {

	String partner_id;
	int duration;
	String ad_content;
	long adEndTime;
	
	public String getPartnerId() {
		return partner_id;
	}
	public void setPartnerId(String partnerId) {
		this.partner_id = partnerId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getAdContent() {
		return ad_content;
	}
	public void setAdContent(String adContent) {
		this.ad_content = adContent;
	}
	public long getAdEndTime() {
		return adEndTime;
	}
	public void setAdEndTime(long adEndTime) {
		this.adEndTime = adEndTime;
	}
	
	
}
