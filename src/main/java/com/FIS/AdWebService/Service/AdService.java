/*
 * @Author Prachi Patwa
 * 
 * Contains createAd(), getAd(), getAllAds() 
 * 
 */
package com.FIS.AdWebService.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.FIS.AdWebService.Entity.AdRequest;
import com.FIS.AdWebService.Entity.AdResponse;

public class AdService {

	private ConcurrentHashMap<String, List<AdRequest>> adMap = new ConcurrentHashMap<String, List<AdRequest>>();

	// Method to retrieve active Ad information for a partner based on partnerId
	public ResponseEntity getAd(String partnerId) {
		List<AdRequest> adList = new ArrayList<AdRequest>();
		AdResponse response = new AdResponse();
		if (adMap.containsKey(partnerId)) {
			// If Ad already exists for the partner, check if the Ad is active
			if (getActiveAdForPartner(partnerId) != null) {
				adList.add(getActiveAdForPartner(partnerId));
				response.setAdInfo(adList);
				return new ResponseEntity<>(response,HttpStatus.OK);
			} else {
				response.setRespMsg("No Active Ad Campaign found for partnerId -" + partnerId);
				return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
			}
		} else {
			response.setRespMsg("ERROR: Partner Id not found");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}

	// Method to create a new Ad, also supports adding multiple Ads for a
	// partner
	public ResponseEntity createAd(AdRequest newAd) {
		AdResponse response = new AdResponse();
		if (newAd.getDuration() == 0 || newAd.getAd_content() == null || newAd.getPartner_id() == null) {
			response.setRespMsg("Please enter duration greater than 0");
			return new ResponseEntity<>(response,HttpStatus.METHOD_NOT_ALLOWED);

		} else {
			if (newAd.getPartner_id().toLowerCase() == "all") {
				response.setRespMsg("Please enter patner_id other than all");
				return new ResponseEntity<>(response,HttpStatus.METHOD_NOT_ALLOWED);
			} else {
				List<AdRequest> adList = new ArrayList<AdRequest>();
				String partnerId = newAd.getPartner_id();
				long adEndTime = (System.currentTimeMillis() / 1000) + newAd.getDuration();
				newAd.setAdEndTime(adEndTime);
				if (adMap.containsKey(partnerId)) {
					if (getActiveAdForPartner(partnerId) != null) {
						response.setRespMsg("A campaign with Partner Id -" + partnerId
								+ "  is already running. Please trying creating the campaign later.");
						return new ResponseEntity<>(response,HttpStatus.METHOD_NOT_ALLOWED);
					} else {
						adList = adMap.get(partnerId);
						adList.add(newAd);
						adMap.put(partnerId, adList);
						response.setRespMsg(
								"Ad Campaign was not active so created a new campaign at - http://localhost:8080/ad/"
										+ newAd.getPartner_id());
						return new ResponseEntity<>(response,HttpStatus.CREATED);
					}
				} else {
					adList.add(newAd);
					adMap.put(partnerId, adList);
					response.setRespMsg(
							"New Ad Campaign created at - http://localhost:8080/ad/" + newAd.getPartner_id());
					return new ResponseEntity<>(response,HttpStatus.CREATED);
				}
			}
		}
	}

	// Method to get data for all campaigns
	public ResponseEntity getAllAd() {
		AdResponse response = new AdResponse();
		List<AdRequest> adReq = new ArrayList<AdRequest>();
		for (List<AdRequest> value : adMap.values()) {
			Iterator<AdRequest> adItr = value.iterator();
			while (adItr.hasNext()) {
				adReq.add(adItr.next());
			}
		}
		response.setAdInfo(adReq);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	// Method to verify if a Ad is active based on currentTime
	public boolean isAdActive(AdRequest newAd) {
		long currentTimeInSec = System.currentTimeMillis() / 1000;
		return (currentTimeInSec < newAd.getAdEndTime());
	}

	// Method to rerieve active Ad if a partner has multiple Ads
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
