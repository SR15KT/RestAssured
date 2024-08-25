package CentralPackage;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostRequest {

	@Test
	void testCase01() {
		JSONObject jsonData=new JSONObject();
		jsonData.put("name", "Shiva");
		jsonData.put("job", "QA");
		// https://reqres.in/api/users/2
		RestAssured.baseURI="https://reqres.in/api/users/2";
		
		
		RestAssured.given().header("content-type","application/json").
		contentType(ContentType.JSON).
		body(jsonData.toJSONString()).
		when().post().
		then().statusCode(201).log().all();
		
		
	}
	
}
