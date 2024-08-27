package CentralPackage;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import JSONData.PayLoad;
import io.restassured.RestAssured;

public class LibraryAPI {
	@Test(priority=1,dataProvider="data",enabled=true)
	void testCase01_postBook(String name,String id) {
		RestAssured.baseURI="http://216.10.245.166";
		String res=given().header("content-type","application/json").body(PayLoad.libraryAPIData(name,id))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(res);
		
		//hiio
//		
//		
		// get book
		
//		given().log().all().header("content-type","application/json").
//		when().get("/Library/GetBook.php?AuthorName=John foe")
//		.then().log().all().assertThat().statusCode(200);
//	
		
	}
	@Test(priority=2,dataProvider="data")
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
		return new Object[][] {{"soojkhiva","1"},{"Ramiiig","2"},{"kr...ishna","3"}};
		
	}
	
	
}
