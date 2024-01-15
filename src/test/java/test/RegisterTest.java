package test;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RegisterTest {

	@Test
	public void listUserAPI() {
		RestAssured.baseURI = "https://reqres.in"; 
//		RestAssured.basePath = "/api/users?page=2";
		
		Response response = RestAssured.get("/api/users?page=2") ;
		System.out.println(response.asString());
		
		response.then()
					.statusCode(200)
					.body("per_page", Matchers.equalTo(6))
					.body("data.first_name", Matchers.hasItem("Lindsay"))
					.contentType(ContentType.JSON);

	}
}
