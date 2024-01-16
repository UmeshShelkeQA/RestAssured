package test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.apache.juneau.json.JsonParser;
import org.apache.juneau.json.JsonSerializer;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.UserInfo;

public class RegisterTest extends BaseTest {

	@Test(groups = {"User API"})
	public void getUserAPI() {
		addAuthor("Lisa");
		
		baseURI = "https://reqres.in";
		basePath = "/api/users/2";

		addLog("Sending GET request To '" + baseURI + basePath + "' with no request data ");
		Response response = given().when().get(baseURI + basePath);
//		System.out.println(response.asString());
		/*
		 * response.then().assertThat() .statusCode(200) .body("per_page",
		 * Matchers.equalTo(6)) .body("data.first_name", Matchers.hasItem("Lindsay"))
		 * .contentType(ContentType.JSON);
		 */
		addLog("Validating Response Code [200]");
		response.then().statusCode(200);

		addLog("Validating Response Content-Type [JSON]");
		response.then().contentType(ContentType.JSON);

		addLog("Validating Response body contains ['id', 'email'] ");
		response.then().body(Matchers.containsString("id"), Matchers.containsString("email"));

		addLog("Checking response time is less than 2 seconds");
		response.then().time(Matchers.lessThan(2000l));
	}

	@Test(groups = {"User API"})
	public void addUserAPI() {
		addAuthor("Umesh");
		baseURI = "https://reqres.in";
		Map<String, String> reqBody = new HashMap<>();
		reqBody.put("name", "morpheus");
		reqBody.put("job", "leader");

		/*
		 * given() .body(reqBody) .when() .post("/api/users") .then()
		 * .assertThat().statusCode(201) .body(Matchers.containsString("createdAt"));
		 */
		basePath = "/api/users";
		addLog("Sending POST request To '" + basePath + "' with request body " + reqBody.toString());

		Response response = given().body(reqBody).when().post(basePath);

		addLog("Validating Response Code [201]");
		response.then().statusCode(201);

		addLog("Validating Response Content-Type [JSON]");
		response.then().contentType(ContentType.JSON);

		addLog("Validating Response body contains ['id', 'createdAt'] ");
		response.then().body(Matchers.containsString("id"), Matchers.containsString("createdAt"));

		addLog("Checking response time is less than 2 seconds");
		response.then().time(Matchers.lessThan(3000l));
	}

	@Test(groups = {"User API"})
	public void registerUserAPI() {
		addAuthor("Tom");
		/*
		 * { "email": "eve.holt@reqres.in", "password": "pistol" }
		 */
		baseURI = "https://reqres.in";
		basePath = "/api/register";
		UserInfo user = UserInfo.builder().email("eve.holt@reqres.in").password("pistol").build();

		//serializing user object to json String
		String reqBody = JsonSerializer.DEFAULT_READABLE.serialize(user);
//		System.out.println(reqBody);

		addLog("Sending POST request To '" + baseURI + basePath + "' with request body " + reqBody);
		Response response = given().body(reqBody).when().post(basePath);

		addLog("Validating Response Code [201]");
		response.then().statusCode(201);

		addLog("Validating Response Content-Type [JSON]");
		response.then().contentType(ContentType.JSON);

		addLog("Validating Response body contains ['id', 'createdAt'] ");
		response.then().body(Matchers.containsString("id"), Matchers.containsString("createdAt"));

		addLog("Checking response time is less than 2 seconds");
		response.then().time(Matchers.lessThan(3000l));
		
		//de-serializing json String to User Object
//		System.out.println("Deserializing Object");
		UserInfo deserializedUserObject = JsonParser.DEFAULT.parse(reqBody, UserInfo.class);
//		System.out.println("Email : " +deserializedUserObject.getEmail());
//		System.out.println("Password : "+ deserializedUserObject.getPassword());
	}
}
