package com.FIS.AdWebService.Test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.FIS.AdWebService.Application;
import com.FIS.AdWebService.Entity.AdRequest;
import com.FIS.AdWebService.Entity.AdResponse;
import com.FIS.AdWebService.Service.AdService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Category(IntegrationTest.class)
public class AdTest {

	private AdService adService = new AdService();
	
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
	
	@Test
	public void getSingleAd() throws InterruptedException{
		addAd1Success();
		addAd1Fail();
		addAd1AgainSuccess();
		getAdSuccess();
	}

	
	public void addAd1Success() {
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner_1");
		request.setDuration(10);
		request.setAdContent("TEST AD1");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(), 0);
	}
	
	
	public void addAd1Fail() {
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner_1");
		request.setDuration(20);
		request.setAdContent("TEST AD12");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(), 1);
	}
	
	
	public void addAd1AgainSuccess() throws InterruptedException{
		Thread.sleep(12000);
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner_1");
		request.setDuration(100);
		request.setAdContent("TEST AD Success");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(), 0);
	}
	
	
	
	public void addAd2Success() {
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner_2");
		request.setDuration(20);
		request.setAdContent("TEST AD2");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(), 0);
	}
	
	
	public void addAd3Success() {
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner_3");
		request.setDuration(55);
		request.setAdContent("TEST AD3");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(), 0);
	}
	

	public void addAd4Success() {
		AdRequest request = new AdRequest();
		request.setPartnerId("Partner_4");
		request.setDuration(70);
		request.setAdContent("TEST AD4");
		AdResponse resp = new AdResponse();
		resp = adService.createAd(request);
		assertEquals(resp.getRespCd(), 0);
	}
	
	@Test
	public void isAdActive(){
		AdRequest adRequest = new AdRequest();
		adRequest.setAdEndTime(System.currentTimeMillis()/1000 + 20);
		boolean isActive = adService.isAdActive(adRequest);
		assertEquals(isActive,true);
	}
	
	@Test
	public void isAdInactive(){
		AdRequest adRequest = new AdRequest();
		adRequest.setAdEndTime(System.currentTimeMillis()/1000 - 20);
		boolean isActive = adService.isAdActive(adRequest);
		assertEquals(isActive,false);
	}
	
	public void getAllAd()
	{
		AdResponse resp = new AdResponse();
		resp = adService.getAllAd();
		assertNotNull(resp);
		assertEquals(resp.getAdInfo().size(),5);
	}
	
	public void getAdSuccess()
	{
		AdResponse resp = new AdResponse();
		resp = adService.getAd("Partner_1");
		assertNotNull(resp);
		assertEquals(resp.getAdInfo().size(),1);
	}

}