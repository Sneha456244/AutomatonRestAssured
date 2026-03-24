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

	
	//1) POST request body using HashMap
   // @Test
    void testPostusingHashMap() {

        // Create request body using HashMap
        HashMap<String, Object> data = new HashMap<>();

        data.put("name", "John");
        data.put("location", "India");
        data.put("phone", "1234567890");

        String[] courses = {"Java", "Selenium"};
        data.put("courses", courses);

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
    
    //2) POST request body Creation using Org.json
    
    @Test
    void testPostusingJsonLibrary() {

        // Create request body using Org.json
        JSONObject data=new JSONObject();
        
        data.put("name", "John");
        data.put("location", "India");
        data.put("phone", "1234567890");

        String[] courses = {"Java", "Selenium"};
        data.put("courses", courses);

        // Perform POST request
        given()
            .contentType("application/json")
            .body(data.toString())

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
