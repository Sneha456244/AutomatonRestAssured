package day01;

/* given () -> content type, set cookies, add auth, add params, set headers info etc...
 *  when () -> get , post , put , delete 
 *  then () -> validate status code , extract response , extract headers cookies & response body ...
*/

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


public class HTTPRequests {

	int id;
   
	@Test(priority=1)
    void getUsers() {

        given()
            .header("x-api-key", "reqres_48aa101a9c74404bbd95e9a342472aaf")  // IMPORTANT
        .when()
            .get("https://reqres.in/api/users?page=2")
        .then()
            .log().all()
            .statusCode(200)
            .body("page", equalTo(2));  
    }
    
    @Test(priority=2)
    void CreateUser() { 
        
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Sneha");
        data.put("job", "J_QA");
        
        id=given()
            .header("x-api-key", "reqres_48aa101a9c74404bbd95e9a342472aaf")  // IMPORTANT
            .contentType("application/json")
            .body(data)
        
        .when()
            .post("https://reqres.in/api/users")
            .jsonPath().getInt("id");
        
       // .then()
          //  .statusCode(201)
           // .log().all();
    }
    
    @Test(priority=3, dependsOnMethods= {"CreateUser"})
    void UpdateUser() { 
        
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "Asha");
        data.put("job", "Intern");
        
        given()
            .header("x-api-key", "reqres_48aa101a9c74404bbd95e9a342472aaf")  // IMPORTANT
            .contentType("application/json")
            .body(data)
        
        .when()
            .put("https://reqres.in/api/users/" +id)
        
        .then()
            .statusCode(200)
            .log().all();
    }
    
    @Test(priority=4)
    void DeleteUser() { 
    	 given()
    	 	.header("x-api-key", "reqres_48aa101a9c74404bbd95e9a342472aaf")  // IMPORTANT
    	
    	.when()
    		.delete("https://reqres.in/api/users/" +id)
    	
    	.then()
    		.statusCode(204)
    		.log().all();
    	
    }
}
