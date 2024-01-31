package test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.apache.juneau.json.JsonSerializer;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import end.points.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.UserInfo;

public class RegisterTest extends BaseTest {

	@Test(groups = {"User API"})
	public void getUserAPI() {
		logger.info("Execution started : getUserAPI()");
		addAuthor("Lisa");
		logger.info("Author : Lisa");
		
		addLog("Sending GET request To '" + EndPoints.GET_USER_REQRES_API + "' with no request data ");
		logger.info("Sending GET request To '" + EndPoints.GET_USER_REQRES_API + "' with no request data ");
		Response response = given().when().get(EndPoints.GET_USER_REQRES_API);
//		System.out.println(response.asString());
		/*
		 * response.then().assertThat() .statusCode(200) .body("per_page",
		 * Matchers.equalTo(6)) .body("data.first_name", Matchers.hasItem("Lindsay"))
		 * .contentType(ContentType.JSON);
		 */
		addLog("Validating Response Code [200]");
		logger.info("Validating Response Code [200]");
		response.then().statusCode(200);

		addLog("Validating Response Content-Type [JSON]");
		logger.info("Validating Response Content-Type [JSON]");
		response.then().contentType(ContentType.JSON);

		addLog("Validating Response body contains ['id', 'email'] ");
		logger.info("Validating Response body contains ['id', 'email'] ");
		response.then().body(Matchers.containsString("id"), Matchers.containsString("email"));

		addLog("Checking response time is less than 2 seconds");
		logger.info("Checking response time is less than 2 seconds");
		response.then().time(Matchers.lessThan(2000l));
	}

	@Test(groups = {"User API"})
	public void addUserAPI() {
		logger.info("Execution started : addUserAPI()");
		addAuthor("Umesh");
		logger.info("Author : Umesh");
		
		Map<String, String> reqBody = new HashMap<>();
		reqBody.put("name", "morpheus");
		reqBody.put("job", "leader");

		/*
		 * given() .body(reqBody) .when() .post("/api/users") .then()
		 * .assertThat().statusCode(201) .body(Matchers.containsString("createdAt"));
		 */
		addLog("Sending POST request To '" + EndPoints.POST_ADD_USER_REQRES_API + "' with request body " + reqBody.toString());
		logger.info("Sending POST request To '" + EndPoints.POST_ADD_USER_REQRES_API + "' with request body " + reqBody.toString());
		
		Response response = given().body(reqBody).when().post(EndPoints.POST_ADD_USER_REQRES_API);

		addLog("Validating Response Code [201]");
		logger.info("Validating Response Code [201]");
		
		response.then().statusCode(201);

		addLog("Validating Response Content-Type [JSON]");
		logger.info("Validating Response Content-Type [JSON]");
		response.then().contentType(ContentType.JSON);

		addLog("Validating Response body contains ['id', 'createdAt'] ");
		logger.info("Validating Response body contains ['id', 'createdAt'] ");
		response.then().body(Matchers.containsString("id"), Matchers.containsString("createdAt"));

		addLog("Checking response time is less than 2 seconds");
		logger.info("Checking response time is less than 2 seconds");
		response.then().time(Matchers.lessThan(3000l));
	}

	@Test(groups = {"User API"})
	public void registerUserAPI() {
		logger.info("Execution started : registerUserAPI()");
		addAuthor("Tom");
		logger.info("Author : Tom");
		/*
		 * { "email": "eve.holt@reqres.in", "password": "pistol" }
		 */
		UserInfo user = UserInfo.builder().email("eve.holt@reqres.in").password("pistol").build();

		//serializing user object to json String
		String reqBody = JsonSerializer.DEFAULT_READABLE.serialize(user);
//		System.out.println(reqBody);

		addLog("Sending POST request To '" + EndPoints.POST_REGISTER_USER_REQRES_API + "' with request body " + reqBody);
		logger.info("Sending POST request To '" + EndPoints.POST_REGISTER_USER_REQRES_API + "' with request body " + reqBody);
		Response response = given().body(reqBody).when().post(EndPoints.POST_REGISTER_USER_REQRES_API);

		addLog("Validating Response Code [201]");
		logger.info("Validating Response Code [201]");
		response.then().statusCode(201);

		addLog("Validating Response Content-Type [JSON]");
		logger.info("Validating Response Content-Type [JSON]");
		response.then().contentType(ContentType.JSON);

		addLog("Validating Response body contains ['id', 'createdAt'] ");
		logger.info("Validating Response body contains ['id', 'createdAt'] ");
		response.then().body(Matchers.containsString("id"), Matchers.containsString("createdAt"));

		addLog("Checking response time is less than 2 seconds");
		logger.info("Checking response time is less than 2 seconds");
		response.then().time(Matchers.lessThan(3000l));
		
	}
}
