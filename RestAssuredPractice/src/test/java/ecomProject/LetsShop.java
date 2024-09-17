package ecomProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.*;

import org.testng.annotations.Test;
public class LetsShop {
	public static String token;
	public static String user_id;
	public static String productId;


	@Test(priority=1)
	public void logging() {
		RequestSpecification req= new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		
		PojoLoginRequest log=new PojoLoginRequest();
		log.setUserEmail("sr15kt.dev@gmail.com");
		log.setUserPassword("Shiva@2001");
		
		RequestSpecification reqLog=given().log().all().spec(req).body(log);
		POJOLoginResponse loginResponse =reqLog.when().post("/api/ecom/auth/login").then().extract().response().as(POJOLoginResponse.class);
		token=loginResponse.getToken();
		user_id=loginResponse.getUserId();
		System.out.println(user_id);
		System.out.println(token);

		System.out.println(loginResponse.getMessage());

	}
	@Test(priority=2,enabled=true)
	public void addProduct() {
	
	RequestSpecification addProductBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("Authorization", token)
			.build();
	
	RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq).param("productName", "Laptop")
	.param("productAddedBy", user_id).param("productCategory", "fashion")
	.param("productSubCategory", "shirts").param("productPrice", "11500")
	.param("productDescription", "Lenova").param("productFor", "men")
	.multiPart("productImage",new File("\\Users\\2304119\\OneDrive - Cognizant\\Pictures\\Screenshots\\Screenshot 2024-05-03 162310.png"));
	
	String addProductResponse =reqAddProduct.when().post("/api/ecom/product/add-product").
	then().log().all().extract().response().asString();
	JsonPath js = new JsonPath(addProductResponse);
	productId =js.get("productId");
	System.out.println(productId);
		
}	
	//@Test(priority=3)
	public void createOrder() {
		POJOOrderDetails orderDet=new POJOOrderDetails();
		orderDet.setCountry("India");
		orderDet.setProductOrderedId(productId);
		List<POJOOrderDetails>lists=new ArrayList<POJOOrderDetails>();
		lists.add(orderDet);
		POJOCreateOrder obj =new  POJOCreateOrder();
				
		
		obj.setOrders(lists);
		RequestSpecification addProductBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token)
				.build();
		given().log().all().spec(addProductBaseReq).body(obj)
		.when().post("/api/ecom/order/create-order")
		.then().log().all().extract().response().as(POJOCreateOrder.class);
	
	System.out.println();
	}


}
