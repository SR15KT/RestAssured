package ecomProjectRA;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
public class EcomProject {
	
	public static String token;
	public static String userId;
	public static String productId;
	@Test(priority=1,enabled=true)
	public void login() {
		RequestSpecification loginReqHead=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
		
		Login_POJO_Request login=new Login_POJO_Request();
		login.setUserEmail("sr15kt.dev@gmail.com");
		login.setUserPassword("Shiva@2001");
		
		RequestSpecification loginReqBody=given().log().all().spec(loginReqHead).body(login);
		Login_POJO_Response loginRes=loginReqBody.when().post("/api/ecom/auth/login")
		.then().log().all().extract().response().as(Login_POJO_Response.class);
		token=loginRes.getToken();
		userId=loginRes.getUserId();
		System.out.println("**********Post Response************");
				System.out.println(token);
				System.out.println(userId);
		
	}
	@Test(priority=2,enabled=true)
	public void addProduct() {
		RequestSpecification addProductHead=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();
		
		RequestSpecification addProductBody = given().log().all().spec(addProductHead).param("productName", "Laptop")
				.param("productAddedBy", userId).param("productCategory", "fashion")
				.param("productSubCategory", "shirts").param("productPrice", "11500")
				.param("productDescription", "Lenova").param("productFor", "men")
				.multiPart("productImage",new File("\\Users\\2304119\\OneDrive - Cognizant\\Pictures\\Screenshots\\Screenshot 2024-05-03 162310.png"));
						
		String response=addProductBody.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
		JsonPath js = new JsonPath(response);
		productId =js.get("productId");
		System.out.println(productId);
		
		

	}
	@Test(priority=3)
	public void deleteProduct() {
	RequestSpecification deleteProductHead=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("Authorization", token)
			.setContentType(ContentType.JSON)
			.build();
	
	given().log().all().spec(deleteProductHead).pathParam("productId", productId).
			when().delete("/api/ecom/product/delete-product/{productId}").then().log().all();
	}
	
	public static void method() {
		RequestSpecification request = RestAssured.given()
        		.pathParam("resultId", "1");
        		request.auth().preemptive().basic("jahnavidevi.esamsetti@wickes.co.uk", ".5ynRv2La29rn0nj23i9-R0eR3iFql4a4IGum4pfn")
        		.header("Content-Type", "multipart/form-data")
        		.multiPart("attachment", new File("\\Users\\2304119\\OneDrive - Cognizant\\Pictures\\Screenshots\\Screenshot (22).png"));

        String response=request.post("https://jahnavi.testrail.io/index.php?/api/v2/add_attachment_to_result/{resultId}")
        	   .then().log().all().assertThat().statusCode(200).extract().response().toString();
        
        System.out.println(response);
       
	}
	
}
