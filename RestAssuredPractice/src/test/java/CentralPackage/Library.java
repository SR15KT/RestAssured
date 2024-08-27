package CentralPackage;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import JSONData.PayLoad;
import io.restassured.RestAssured;

public class Library {
	@Test(dataProvider="data",enabled=true)
	void testCase01_postBook(String name,String id) {
		RestAssured.baseURI="http://216.10.245.166";
		String res=given().header("content-type","application/json").body(PayLoad.libraryAPIData(name,id))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(res);	
	}
	@Test(dataProvider="data")
	void testCase02_deleteBook(String name,String id) {
		// delete book
		RestAssured.baseURI="http://216.10.245.166";

		String res2=given().header("content-type","application/json")
		.body(PayLoad.postBook(name,id))
		.when().post("/Library/DeleteBook.php")
		.then().extract().response().asString();
	
	System.out.println(res2);
	}
	
	@DataProvider(name="data")
	public Object[][] getData() {
		return new Object[][] {{"shiva","1"},{"Rama","2"},{"krishna","3"}};
		
	}
	
	
}
