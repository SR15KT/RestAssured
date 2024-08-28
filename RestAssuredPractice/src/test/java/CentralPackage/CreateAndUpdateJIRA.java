package CentralPackage;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.io.File;

public class CreateAndUpdateJIRA {
 @Test(enabled=false)
 public void testCase01_createBug() {
	 RestAssured.baseURI="https://sr15ktdev.atlassian.net";
	 given().header("content-type","application/json").header("Authorization","Basic c3IxNWt0LmRldkBnbWFpbC5jb206QVRBVFQzeEZmR0YwQXdJUWhqZ18yR3EyWXJuX3pmeWZZZ1Q4TWpBSEJ4UWdwem5pc0t6YWJlalRGRFlCa0dYLXVYOGhTYWlKSWpRX3ppRVJ6S2pSeDRfblFKclQyeGR1U2h3T2pBRTlpUXZid2hvaFJMbHpMTFBaRlc0RE1kSVY3NHdhZFdaU0VVQzQ0TGpsS2pPVUdlZzJwSk5pTF8zTVIyVXRhTHZiWGd5a1JjaEpSRWNfWXFjPTNEOTU2QkU5")
	 .body("{\r\n"
	 		+ "    \"fields\": {\r\n"
	 		+ "       \"project\":\r\n"
	 		+ "       {\r\n"
	 		+ "          \"key\": \"RP\"\r\n"
	 		+ "       },\r\n"
	 		+ "       \"summary\": \"Language is not Changed 2 \",\r\n"
	 		+ "       \"description\": \"in the home page right below the profile section\",\r\n"
	 		+ "       \"issuetype\": {\r\n"
	 		+ "          \"name\": \"Bug\"\r\n"
	 		+ "       }\r\n"
	 		+ "   }\r\n"
	 		+ "}")
	 .when().post("/rest/api/2/issue")
	 .then().log().all().assertThat().statusCode(201);
 }
 
 @Test()
 public void addAttachments() {
	 RestAssured.baseURI="https://sr15ktdev.atlassian.net";
	 given().header("X-Atlassian-Token","no-check")
	 .header("Authorization","Basic c3IxNWt0LmRldkBnbWFpbC5jb206QVRBVFQzeEZmR0YwQXdJUWhqZ18yR3EyWXJuX3pmeWZZZ1Q4TWpBSEJ4UWdwem5pc0t6YWJlalRGRFlCa0dYLXVYOGhTYWlKSWpRX3ppRVJ6S2pSeDRfblFKclQyeGR1U2h3T2pBRTlpUXZid2hvaFJMbHpMTFBaRlc0RE1kSVY3NHdhZFdaU0VVQzQ0TGpsS2pPVUdlZzJwSk5pTF8zTVIyVXRhTHZiWGd5a1JjaEpSRWNfWXFjPTNEOTU2QkU5")
	 //.multiPart("file",new File("\\Users\\2304119\\eclipse-workspace\\HackProject\\reports\\TestNGReports.html"))	 
	 .multiPart("file",new File("\\Users\\2304119\\eclipse-workspace\\HackProject\\reports\\cucumberReports.html"))
	 .when().post("/rest/api/2/issue/RP-8/attachments")
	 .then().log().all().assertThat().statusCode(200);
 }
 
 
}
