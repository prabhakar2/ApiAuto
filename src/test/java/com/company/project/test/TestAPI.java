package com.company.project.test;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
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
		log.info("responsebodyPost: " + responsebodyPost);
		
	    JsonPath jp = new JsonPath(responsebodyPost);
	    jwttoken=jp.getString("token");
	 
	    log.info("Get call:");
	    Response responseAllUserGetAPI = RestClient.getCall(urlUser, jwttoken);
	    
	    int responseCodeGet= responsePostAPI.getStatusCode();
		Assert.assertEquals(responseCodeGet, RESPONSE_STATUS_CODE_200);
		
		String responsebodyGet= responseAllUserGetAPI.asString();
		
		log.info("responsebodyGet: " + responsebodyGet);
		
		JSONArray jsonArray = new JSONArray(responsebodyGet);
		JSONObject jsonObject = (JSONObject) jsonArray.get(0);
		System.out.println(jsonObject.getString("career"));
		
		Assert.assertEquals(jsonObject.getString("first_name"), "Aliko");
		Assert.assertEquals(jsonObject.getString("last_name"), "Dangote");
		Assert.assertEquals(jsonObject.getString("career"), "Billionaire Industrialist");
		Assert.assertEquals(jsonObject.getString("phone"), "8037602400");	
		
			
	}
	

	
	
	
	
	
	
	
	
}
