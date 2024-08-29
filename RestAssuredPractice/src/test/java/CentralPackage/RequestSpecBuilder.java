package CentralPackage;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class RequestSpecBuilder {
	@Test
	public void serialization() {
		AddPlace add=new AddPlace();
		add.setAccuracy(50);
		add.setAddress("29, side layout, cohen 09");
		add.setLanguage("French-IN");
		add.setName("Frontline house");
		add.setPhone_number("(+91) 983 893 3937");
		add.setWebsite("http://google.com");
		List<String>lists=new ArrayList<String>();
		lists.add("shoe park");
		lists.add("shop");		
		add.setTypes(lists);
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		add.setLocation(l);
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		RequestSpecification req =new io.restassured.builder.RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification res= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		String rs=	given().spec(req)
		.body(add)
		.when().post("/maps/api/place/add/json")
		.then().log().all().spec(res).extract().response().asString();
	System.out.println(rs);
	}
}

