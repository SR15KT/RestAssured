package TestRailApiConnection;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import org.testng.annotations.Test;
public class ReportsAPiCall {
    
    @Test    
    public void testRail_addAttachments() {    	
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