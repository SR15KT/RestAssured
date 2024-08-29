package CentralPackage;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.Location;

public class Serialization {
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
	String rs=	given().queryParam("key","qaclick123")
		.header("content-type","application/json")
		.body(add)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
	}
}
