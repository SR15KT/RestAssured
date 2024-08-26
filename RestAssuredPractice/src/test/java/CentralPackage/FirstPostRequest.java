package CentralPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import JSONData.PayLoad;
import  io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
public class FirstPostRequest {
	public static String Place_id;
	public static String address;
	@Test(enabled=false)
	void testCase01_AddPlace() {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		
				//given - all input details 
				//when - Submit the API -resource,http method
				//Then - validate the response
		
		given().log().all().queryParam("key", "qaclick123")
		.header("content-type","application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "")
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)");
	}

	// Add place -> update place with new address -> Get Place to validate new address is present in response
		@Test(priority=2)
		void testCase02_UpdatePlace() {
			
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("content-type","application/json").body(PayLoad.addPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		//System.out.println(response);
		JsonPath js=new JsonPath(response);
		 Place_id=js.getString("place_id");
		 address="70 winter walk, USA";
		 System.out.println(Place_id); 
		
		
		given().log().all().queryParam("key", "qaclick123").header("content-type","application/json").body(PayLoad.updatePlace(Place_id))
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	}
		
		
		@Test(priority=3)
		void testCase03_GetPlace() {  
			RestAssured.baseURI="https://rahulshettyacademy.com";
			String response=given().log().all().queryParam("key", "qaclick123").queryParam( "place_id", Place_id)
			.when().get("maps/api/place/get/json")
			.then().log().all().assertThat().statusCode(200).extract().response().asString();
			
			
			JsonPath js=new JsonPath(response);
			System.out.println(js.getString("address"));
		} 
		

}


/*
//https://reqres.in/api/users/2
	//@Test()
	void testCase01() {
		Response res=RestAssured.get("https://reqres.in/api/users/2");
		System.out.println(res.asString());
		System.out.println("status code : "+ res.getStatusCode());
	}
	//@Test
	void testcase02() {
		// https://reqres.in/api/users?page=2
		Response res=RestAssured.get("https://reqres.in/api/users?page=2");
		System.out.println("Status code : "+res.getStatusCode());
		System.out.println("Response body : "+res.getBody());
		System.out.println("Response Header : "+res.getHeader("Content-Type"));
		System.out.println("Response Time : "+res.getTime());
		int ExpectedCode=200;
		int ActualCode=res.getStatusCode();
		Assert.assertEquals(ExpectedCode,ActualCode);
		
		
		//using given, when, then
		RestAssured.baseURI="https://reqres.in/api/users?page=2";
		RestAssured.given().queryParam("page", "2").when().get().then().statusCode(200);	
	}
	*/