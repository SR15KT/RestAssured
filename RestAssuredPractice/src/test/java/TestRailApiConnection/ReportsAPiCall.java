package TestRailApiConnection;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class ReportsAPiCall {
    
    @Test    
    public void testRail_addAttachments() {    	
        RequestSpecification request = RestAssured.given()
        		.pathParam("resultId", "11");
        		request.auth().preemptive().basic("jahnavidevi.esamsetti@wickes.co.uk", ".5ynRv2La29rn0nj23i9-R0eR3iFql4a4IGum4pfn")
        		.header("Content-Type", "multipart/form-data")
        		.multiPart("attachment", new File("\\Users\\2304119\\OneDrive - Cognizant\\Pictures\\Screenshots\\Screenshot (22).png"));

        String response=request.post("https://jahnavi.testrail.io/index.php?/api/v2/add_attachment_to_result/{resultId}")
        	   .then().log().all().assertThat().statusCode(200).extract().response().toString();
        
        System.out.println(response);
       
    }
    

    
    @DataProvider(name="files")
    public Object[][] dataProvider() {
    	String[] testCaseIds = {"1", "2"};
        File[] screenshots = {
            new File("path/to/screenshot1.png"),
            new File("path/to/screenshot2.png"),
            new File("path/to/screenshot3.png")
        };


        int length = Math.min(testCaseIds.length, screenshots.length);
        Object[][] data = new Object[length][2];

        for (int i = 0; i < length; i++) {
            data[i][0] = testCaseIds[i];
            data[i][1] = screenshots[i];
        }

        return data;
    }
}