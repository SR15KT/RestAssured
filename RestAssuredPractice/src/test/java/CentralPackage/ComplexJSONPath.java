package CentralPackage;

import org.testng.annotations.Test;

import JSONData.PayLoad;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
/* 
1. Print No of courses returned by API

2.Print Purchase Amount

3. Print Title of the first course

4. Print All course titles and their respective Prices

5. Print no of copies sold by RPA Course

6. Verify if Sum of all Course prices matches with Purchase Amount
 
 */
public class ComplexJSONPath {
	
	@SuppressWarnings("deprecation")
	@Test()
	void testCase01_JsonParsing() {		
	JsonPath js=new JsonPath(PayLoad.coursePrice());
	//1. Print No of courses returned by API
	int count=js.getInt("courses.size()");
	System.out.println("No of courses returned : "+count);
	
	// 2.Print Purchase Amount
	int TotalAmount=js.getInt("dashboard.purchaseAmount");
	System.out.println(TotalAmount);
	
	// 3. Print Title of the first course
	String title=js.getString("courses[0].title");
	System.out.println(title);
	
	//4. Print All course titles and their respective Prices
		int price=0;
		for(int i=0;i<js.getInt("courses.size()");i++) {
			System.out.println(js.getString("courses["+i+"].title")+" "+js.getString("courses[0].price") );
			price+=js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies");
		}
			
	// 5. Print no of copies sold by RPA Course
	
		for(int i=0;i<js.getInt("courses.size()");i++) {
			System.out.println(js.getString("courses["+i+"].title")+" "+js.getString("courses[0].price") );
			
			if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
				System.out.println(js.getString("courses["+i+"].copies"));
				break;
			}
			
		}
		
		//6. Verify if Sum of all Course prices matches with Purchase Amount
		Assert.assertEquals(price, TotalAmount);
		System.out.println(price+"=="+ TotalAmount);
	}
	
	
	
	
}
