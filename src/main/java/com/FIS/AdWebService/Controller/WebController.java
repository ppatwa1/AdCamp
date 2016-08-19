/*
 * @Author Prachi Patwa
 * 
 * The WebController class is annotated as a @RestController, meaning it’s 
 * ready for use by Spring MVC to handle web requests.
 * 
 */
package com.FIS.AdWebService.Controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.FIS.AdWebService.Entity.AdRequest;
import com.FIS.AdWebService.Entity.AdResponse;
import com.FIS.AdWebService.Service.AdService;

@RestController
@RequestMapping("/ad")
public class WebController {

	AdService adService = new AdService();

	@RequestMapping(method = RequestMethod.GET, value = "/{partnerId}")
	public AdResponse getAd(@PathVariable("partnerId") String partnerId) throws IOException {
		return adService.getAd(partnerId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAd")
	public AdResponse createAd(@RequestBody AdRequest newAd) throws IOException {
		return adService.createAd(newAd);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public AdResponse getAllAd() throws IOException {
		return adService.getAllAd();
	}
}
