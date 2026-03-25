package day02;

public class Pojo_PostRequest {
	
	String name;
	String location;
	String phone;
	String courses[];
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String[] getCourses() {
		return courses;
	}
	public void setCourses(String[] courses) {
		this.courses = courses;
	}

}

//-> open the folder json in notepad -> enter cmd -> npx json-server --watch students.json (run this file in localhost)
package day02;

import org.json.JSONObject;

/* Different ways to Create Post Request body
 * a) POST request body using HashMap
 * b) POST request body Creation using Org.json
 * c) POST request body Creation using POJO class (Plain Old Java Object)
 * d) POST request body using external json file data
 */

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

class DiffWaysToCreatePostRequest {
//3) POST request body Creation using POJO class (Plain Old Java Object)
    
    @Test(priority=1)
    void testPostusingPOJO() {

        // Create request body using Org.json
    	Pojo_PostRequest data=new Pojo_PostRequest();
        
        data.setName("John");
        data.setLocation("India");
        data.setPhone("1234567890");

        String coursesArr[] = {"Java", "Selenium"};
        data.setCourses(coursesArr);

        // Perform POST request
        given()
            .contentType("application/json")
            .body(data)

        .when()
            .post("http://localhost:3000/Students")

        .then()
            .statusCode(201)

            // Validate response body
            .body("name", equalTo("John"))
            .body("location", equalTo("India"))
            .body("phone", equalTo("1234567890"))
            .body("courses[0]", equalTo("Java"))
            .body("courses[1]", equalTo("Selenium"))

            // Flexible header validation (best practice)
            .header("Content-Type", containsString("application/json"))

            // Print response
            .log().all();
    }

    
    //deleting student record
    @Test (priority=2)
    void testDelete() {
    	
    	given()
    	
    	.when()
    		.post("http://localhost:3000/Students/4")
    	
    	.then()
    	.statusCode(404);
    }
}
