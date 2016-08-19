/*
 * @Author Prachi Patwa
 * 
 */
package com.FIS.AdWebService.Test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
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
		request.setAdContent("Partner Id missing");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(),405);
	}
	
	@Test
	public void addAdFailure2(){
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner 1");
		request.setDuration(5);
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(),405);
	}
	
	@Test
	public void addAdFailure3(){
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner 1");
		request.setAdContent("Partner Id missing");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(),405);
	}
	
	@Test
	public void addAdFailure4(){
		AdRequest request = new AdRequest();
		request.setDuration(5);
		request.setPartnerId("all");
		request.setAdContent("Partner Id missing");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(),405);
	}
	
	public void addAd1Success() {
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner_1");
		request.setDuration(10);
		request.setAdContent("TEST AD1");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
	//	System.out.println("Response URL:"  + resp.getRespMsg());
		assertEquals(resp.getRespCd(), 201);
	}
	
	
	public void addAd1Fail() {
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner_1");
		request.setDuration(20);
		request.setAdContent("TEST AD12");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(), 405);
	}
	
	
	public void addAd1AgainSuccess() throws InterruptedException{
		Thread.sleep(12000);
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner_1");
		request.setDuration(100);
		request.setAdContent("TEST AD Success");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(), 201);
	}
	
	
	
	public void addAd2Success() {
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner_2");
		request.setDuration(20);
		request.setAdContent("TEST AD2");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(), 201);
	}
	
	
	public void addAd3Success() {
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner_3");
		request.setDuration(55);
		request.setAdContent("TEST AD3");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(), 201);
	}
	

	public void addAd4Success() {
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner_4");
		request.setDuration(70);
		request.setAdContent("TEST AD4");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(), 201);
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
		AdResponse resp = new AdResponse();
		resp = adService.getAllAd();
		assertNotNull(resp);
		assertEquals(resp.getAdInfo().size(),5);
	}
	
	//Method to retrieve a single Ad by passing partnerId
	public void getAdSuccess()
	{
		AdResponse resp = new AdResponse();
		resp = adService.getAd("Partner_1");
		assertNotNull(resp);
		assertEquals(resp.getAdInfo().size(),1);
	}

}