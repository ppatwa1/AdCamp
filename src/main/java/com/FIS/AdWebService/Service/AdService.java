package com.FIS.AdWebService.Service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.FIS.AdWebService.Entity.AdRequest;
import com.FIS.AdWebService.Entity.AdResponse;
import com.FIS.AdWebService.Entity.Constants;

public class AdService {

	private ConcurrentHashMap<String, List<AdRequest>> adMap = new ConcurrentHashMap<String, List<AdRequest>>();

	//Method to retrieve active Ad information for a partner based on partnerId
	public AdResponse getAd(String partnerId) {
		List<AdRequest> adList = new ArrayList<AdRequest>();
		AdResponse response = new AdResponse();
		if (adMap.containsKey(partnerId)) {
			//If Ad already exists for the partner, check if the Ad is active
			if (getActiveAdForPartner(partnerId) != null) {
				adList.add(getActiveAdForPartner(partnerId));
				response.setAdInfo(adList);
				response.setRespMsg("SUCCESS: Ad Campaign found");
				response.setRespCd(Constants.SUCCESS_CD);
			} else {
				response.setRespMsg("SUCCESS: No Active Ad Campaign found for partnerId -" + partnerId);
				response.setRespCd(Constants.SUCCESS_CD);
			}
		} else {
			response.setRespMsg("ERROR: Partner Id not found");
			response.setRespCd(Constants.ERROR_CD);
		}
		return response;
	}

	//Method to create a new Ad, also supports adding multiple Ads for a partner
	public AdResponse createAd(AdRequest newAd) {
		AdResponse response = new AdResponse();
		List<AdRequest> adList = new ArrayList<AdRequest>();
		String partnerId = newAd.getPartnerId();
		long adEndTime = (System.currentTimeMillis() / 1000) + newAd.getDuration();
		newAd.setAdEndTime(adEndTime);
		if (adMap.containsKey(partnerId)) {
			if (getActiveAdForPartner(partnerId) != null) {
				response.setRespMsg("WARNING: A campaign with Partner Id -" + partnerId
						+ "  is already running. Please trying creating the campaign later.");
				response.setRespCd(Constants.ERROR_CD);
			} else {
				adList = adMap.get(partnerId);
				adList.add(newAd);
				adMap.put(partnerId, adList);
				response.setRespMsg("SUCCESS: Ad Campaign was not active so created a new campaign.");
				response.setRespCd(Constants.SUCCESS_CD);
			}
		} else {
			adList.add(newAd);
			adMap.put(partnerId, adList);
			response.setRespMsg("SUCCESS: New Ad Campaign created.");
			response.setRespCd(Constants.SUCCESS_CD);
		}
		return response;
	}

	//Method to get data for all campaigns
	public AdResponse getAllAd() {
		AdResponse response = new AdResponse();
		List<AdRequest> adReq = new ArrayList<AdRequest>();
		for (List<AdRequest> value : adMap.values()) {
			Iterator<AdRequest> adItr = value.iterator();
			while (adItr.hasNext()) {
				adReq.add(adItr.next());
			}
			response.setAdInfo(adReq);
	//		response.setRespMsg("SUCCESS: Ad Campaigns fetched.");
	//		response.setRespCd(Constants.SUCCESS_CD);
		}
		return response;
	}
	
	//Method to verify if a Ad is active based on currentTime
	public boolean isAdActive(AdRequest newAd) {
		long currentTimeInSec = System.currentTimeMillis() / 1000;
		return (currentTimeInSec < newAd.getAdEndTime());
	}

	//Method to rerieve active Ad if a partner has multiple Ads
	private AdRequest getActiveAdForPartner(String partnerId) {
		AdRequest activeAd;
		List<AdRequest> ad = new ArrayList<AdRequest>();
		ad = adMap.get(partnerId);
		Iterator<AdRequest> adItr = ad.iterator();
		while (adItr.hasNext()) {
			activeAd = adItr.next();
			if (isAdActive(activeAd))
				return activeAd;
		}
		return null;
	}
}
