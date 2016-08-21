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
	
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getAd_content() {
		return ad_content;
	}
	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}
	public long getAdEndTime() {
		return adEndTime;
	}
	public void setAdEndTime(long adEndTime) {
		this.adEndTime = adEndTime;
	}
	
	
}
