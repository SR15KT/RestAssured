package CentralPackage;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class OAuthExample {

		@Test()
		public void generateAccessToken() {
			//RestAssured.baseURI="";
			String response=given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
			formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
			.formParam("grant_type","client_credentials")
			.formParam("scope", "trust").log().all().
			when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
			JsonPath js=new JsonPath(response);
			String accessToken=js.getString("access_token");
			System.out.println(accessToken);
			
			
			
			String response2=given().log().all().
			queryParam("access_token",accessToken).
			when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails?access_token=NKfRLGLaxHVROGwjrbb9Ew==").asString();
			System.out.println(response2);
		}
		
		
}


