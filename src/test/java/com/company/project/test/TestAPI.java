package com.company.project.test;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.project.base.BaseTest;
import com.company.project.client.RestClient;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;



 public class TestAPI extends BaseTest{
    String jwttoken=null;
    final static Logger log=Logger.getLogger(TestAPI.class);
    
	@Test(priority=1, enabled=true)
	public void TestAllUserRecords(){
		
		log.info("Post Auth call:");
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("username", "rupeek");
        hm.put("password", "password");
        
        JSONObject jsonObjectBody = new JSONObject(hm);
        String payload=jsonObjectBody.toString();
	   
		Response responsePostAPI = RestClient.postCallWithToken(urlAuthenticate, payload);
		int responseCodePost= responsePostAPI.getStatusCode();
		Assert.assertEquals(responseCodePost, RESPONSE_STATUS_CODE_200);
		
		String responsebodyPost= responsePostAPI.asString();
		log.info("responsebodyPost" + responsebodyPost);
		
	    JsonPath jp = new JsonPath(responsebodyPost);
	    jwttoken=jp.getString("jwttoken");
	 
	    log.info("Get call:");
	    Response responseAllUserGetAPI = RestClient.getCall(urlUser, jwttoken);
	    
	    int responseCodeGet= responsePostAPI.getStatusCode();
		Assert.assertEquals(responseCodeGet, RESPONSE_STATUS_CODE_200);
		
		String responsebodyGet= responseAllUserGetAPI.asString();
		System.out.println(responsebodyGet);
		log.info("responsebodyGet" + responsebodyGet);
		
		
			
	}
	

	
	
	
	
	
	
	
	
}
