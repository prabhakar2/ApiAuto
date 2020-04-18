package com.company.project.test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

public class RestClient {



    public static Response postCallWithToken(String endpoint,  String payload,  String accessToken) {
        return RestAssured.given().contentType(ContentType.JSON).body(payload).log().everything().when().post(endpoint);

    }
    
    public static Response getCall( String endpoint, String key, String val, String accessToken) {
        return RestAssured.given().contentType(ContentType.JSON).header(new Header("Authorization", "Bearer " + accessToken))
        		.request().queryParameters(key,val).log().everything().when().get(endpoint);

    }
   
}
