package test;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.response.Response.*;

public class RegisterTest {

//	@Test
	public void listUserAPI() {
		baseURI = "https://reqres.in"; 
//		RestAssured.basePath = "/api/users?page=2";
		
		Response response = get("/api/users?page=2") ;
		System.out.println(response.asString());
		
		response.then()
					.statusCode(200)
					.body("per_page", Matchers.equalTo(6))
					.body("data.first_name", Matchers.hasItem("Lindsay"))
					.contentType(ContentType.JSON);

	}
	
	//@Test
	public void postCreateAPI() {
		baseURI = "https://reqres.in"; 	
		Response Postresponse = given()
	    .contentType(ContentType.JSON)
	    .body("{\r\n"
	    		+ "    \"name\": \"morpheus\",\r\n"
	    		+ "    \"job\": \"leader\"\r\n"
	    		+ "}")
	.when()
	.post("/api/users");
		System.out.println(Postresponse.asString() );
		Postresponse.then()
	    .statusCode(201);
		
	}
	
	//@Test
	public void putCreateAPI() 
	{
		baseURI = "https://reqres.in"; 
		Response putResponse = given()
	    .contentType(ContentType.JSON)
	   .body("{\r\n"
	   		+ "    \"name\": \"morpheus\",\r\n"
	   		+ "    \"job\": \"zion resident\"\r\n"
	   		+ "}")
	 .when()
	    .put("/api/users/2");
		System.out.println(putResponse.getBody().asString());
		putResponse.then()
	    .statusCode(200);
	}
	
	@Test
	public void deleteCreateAPI() 
	{
		baseURI = "https://reqres.in"; 
		when()
	    .delete("/api/users/2")
	.then()
	    .statusCode(204);
	    
	}	
}


