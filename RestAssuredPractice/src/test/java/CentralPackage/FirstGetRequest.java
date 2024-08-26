package CentralPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import  io.restassured.RestAssured;
import io.restassured.response.Response;

public class FirstGetRequest {
// https://reqres.in/api/users/2
	@Test()
	void testCase01() {
		Response res=RestAssured.get("https://reqres.in/api/users/2");
		System.out.println(res.asString());
		System.out.println("status code : "+ res.getStatusCode());
	}
	@Test
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
		//hello this is day 1
		
		
	}
}
