/*
 * @Author Prachi Patwa
 * 
 */
package com.FIS.AdWebService.Test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.FIS.AdWebService.Application;
import com.FIS.AdWebService.Entity.AdRequest;
import com.FIS.AdWebService.Entity.AdResponse;
import com.FIS.AdWebService.Service.AdService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class AdTest {

	private AdService adService = new AdService();
	
	
	//Test to add multiple ads for one partner and retrieving all campaigns 
	@Test
	public void getAllAds() throws InterruptedException{
		addAd1Success();
		addAd1Fail();
		addAd1AgainSuccess();
		addAd2Success();
		addAd3Success();
		addAd4Success();
		getAllAd();
	}
	
	//Test to get active Ad given a partnerId
	@Test
	public void getSingleAd() throws InterruptedException{
		addAd1Success();
		addAd1Fail();
		addAd1AgainSuccess();
		getAdSuccess();
	}

	@Test
	public void addAdFailure1(){
		AdRequest request = new AdRequest();
		request.setDuration(5);
		request.setAd_content("Partner Id missing");
		AdResponse resp = new AdResponse();
		ResponseEntity<?> response = adService.createAd(request);
		assertEquals(response.getStatusCode().value(),405);
	}
	
	@Test
	public void addAdFailure2(){
		AdRequest request = new AdRequest();
		request.setPartner_id("Partner 1");
		request.setDuration(5);
		AdResponse resp = new AdResponse();
		ResponseEntity<?> response = adService.createAd(request);
		assertEquals(response.getStatusCode().value(),405);
	}
	
	@Test
	public void addAdFailure3(){
		AdRequest request = new AdRequest();
		request.setPartner_id("Partner 1");
		request.setAd_content("Partner Id missing");
		AdResponse resp = new AdResponse();
		ResponseEntity<?> response = adService.createAd(request);
		assertEquals(response.getStatusCode().value(),405);
	}
	
	@Test
	public void addAdFailure4(){
		AdRequest request = new AdRequest();
		request.setDuration(5);
		request.setPartner_id("all");
		request.setAd_content("Partner Id missing");
		ResponseEntity<?> response = adService.createAd(request);
		assertEquals(response.getStatusCode().value(),405);
	}
	
	public void addAd1Success() {
		AdRequest request = new AdRequest();
		request.setPartner_id("Partner_1");
		request.setDuration(10);
		request.setAd_content("TEST AD1");
		ResponseEntity<?> response = adService.createAd(request);
		assertEquals(response.getStatusCode().value(),201);
	}
	
	
	public void addAd1Fail() {
		AdRequest request = new AdRequest();
		request.setPartner_id("Partner_1");
		request.setDuration(20);
		request.setAd_content("TEST AD12");
		ResponseEntity<?> response = adService.createAd(request);
		assertEquals(response.getStatusCode().value(),405);
	}
	
	
	public void addAd1AgainSuccess() throws InterruptedException{
		Thread.sleep(12000);
		AdRequest request = new AdRequest();
		request.setPartner_id("Partner_1");
		request.setDuration(100);
		request.setAd_content("TEST AD Success");
		ResponseEntity<?> response = adService.createAd(request);
		assertEquals(response.getStatusCode().value(),201);
	}
	
	
	
	public void addAd2Success() {
		AdRequest request = new AdRequest();
		request.setPartner_id("Partner_2");
		request.setDuration(20);
		request.setAd_content("TEST AD2");
		ResponseEntity<?> response = adService.createAd(request);
		assertEquals(response.getStatusCode().value(),201);
	}
	
	
	public void addAd3Success() {
		AdRequest request = new AdRequest();
		request.setPartner_id("Partner_3");
		request.setDuration(55);
		request.setAd_content("TEST AD3");
		AdResponse resp = new AdResponse();
		ResponseEntity<?> response = adService.createAd(request);
		assertEquals(response.getStatusCode().value(),201);
	}
	

	public void addAd4Success() {
		AdRequest request = new AdRequest();
		request.setPartner_id("Partner_4");
		request.setDuration(70);
		request.setAd_content("TEST AD4");
		AdResponse resp = new AdResponse();
		ResponseEntity<?> response = adService.createAd(request);
		assertEquals(response.getStatusCode().value(),201);
	}
	
	//Test to verify if isAdActive returns true
	@Test
	public void isAdActive(){
		AdRequest adRequest = new AdRequest();
		adRequest.setAdEndTime(System.currentTimeMillis()/1000 + 20);
		boolean isActive = adService.isAdActive(adRequest);
		assertEquals(isActive,true);
	}
	
	//Test to verify isAdActive returns false
	@Test
	public void isAdInactive(){
		AdRequest adRequest = new AdRequest();
		adRequest.setAdEndTime(System.currentTimeMillis()/1000 - 20);
		boolean isActive = adService.isAdActive(adRequest);
		assertEquals(isActive,false);
	}
	
	//Method to retrieve all Ads
	public void getAllAd()
	{
		ResponseEntity<?> response = adService.getAllAd();
		assertNotNull(response);
	}
	
	//Method to retrieve a single Ad by passing partnerId
	public void getAdSuccess()
	{
		ResponseEntity<?> response = adService.getAd("Partner 1");
		assertNotNull(response);
	}
	
	@Test
	public void getAdNotFound()
	{
		ResponseEntity<?> response = adService.getAd("1234");
		assertEquals(response.getStatusCode().value(),404);
	}
	

}