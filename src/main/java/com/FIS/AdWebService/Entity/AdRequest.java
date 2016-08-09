package com.FIS.AdWebService.Entity;

public class AdRequest {

	String partnerId;
	int duration;
	String adContent;
	long adEndTime;
	
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getAdContent() {
		return adContent;
	}
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}
	public long getAdEndTime() {
		return adEndTime;
	}
	public void setAdEndTime(long adEndTime) {
		this.adEndTime = adEndTime;
	}
	
	
}
