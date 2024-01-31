package test;

import static io.restassured.RestAssured.given;

import org.apache.juneau.json.JsonSerializer;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import end.points.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.Location;
import pojo.Place;

public class GoogleMapTest extends BaseTest {

	private String place_id;
	private Place place;

	@Test( groups = {"GoogleMap"})
	public void addLocationPOSTCallAPITest() {
		logger.info("Execution Started : addLocationPOSTCallAPITest()");
		addAuthor("Umesh");
		logger.info("Author : Umesh");
		/*
		 * API Call Info 
		 * Complete URL: https://rahulshettyacademy.com/maps/api/place/add/json?key= qaclick123
		 * Base URL/End Point: https://rahulshettyacademy.com Resource:
		 * /maps/api/place/add/json Query Parameters: key =qaclick123 Http Method: POST
		 * Sample Request Body { "location": { "lat": -38.383494, "lng": 33.427362 },
		 * "accuracy": 50, "name": "Frontline house", "phone_number":
		 * "(+91) 983 893 3937", "address": "29, side layout, cohen 09", "types": [
		 * "shoe park", "shop" ], "website": "http://google.com", "language":
		 * "French-IN" } 
		 * Sample Response 
		 * {  "status": "OK", 
		 * "place_id": "928b51f64aed18713b0d164d9be8d67f", 
		 * "scope": "APP", 
		 * "reference": "736f3c9bec384af62a184a1936d42bb0736f3c9bec384af62a184a1936d42bb0", 
		 * "id":36f3c9bec384af62a18  }
		 * 
		 */
		String[] types = { "shoe park", "shop" };
		place = Place.builder()
							.location(Location.builder().lat(-38.383494).lng(33.427362).build())
							.accuracy(50)
							.name("Frontline house")
							.phone_number("(+91) 983 893 3937")
							.address("29, side layout, cohen 09")
							.types(types)
							.website("http://google.com")
							.language("French-IN").build();

		String jsonPlaceDataForRequest = JsonSerializer.DEFAULT_READABLE.serialize(place);

		addLog("Sending request with data : "+jsonPlaceDataForRequest);
		logger.info("Sending request with data : "+jsonPlaceDataForRequest);
		Response response = given()
								.body(jsonPlaceDataForRequest)
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.pathParam("key", "qaclick123")
							.when()
								.post(EndPoints.POST_ADD_LOCATION_GOOGLE_MAP_API);

		
		place_id = response.getBody().jsonPath().getString("place_id");
		addLog("Got place_id = "+place_id);
		logger.info("Got place_id = "+place_id);
		
		addLog("Validating Response Code [200]");
		logger.info("Validating Response Code [200]");
		response.then().assertThat().statusCode(200);

		addLog("Validating Response Content-Type [JSON]");
		logger.info("Validating Response Content-Type [JSON]");
		response.then().assertThat().contentType(ContentType.JSON);

		addLog("Validating Response body contains [ 'id', 'place_id', 'reference', 'scope', 'status'] ");
		logger.info("Validating Response body contains [ 'id', 'place_id', 'reference', 'scope', 'status'] ");
		response.then().assertThat().body(Matchers.containsString("id"), 
							 Matchers.containsString("place_id"), 
							 Matchers.containsString("reference"),
							 Matchers.containsString("scope"),
							 Matchers.containsString("status"));

		addLog("Checking response time is less than 2 seconds");
		logger.info("Checking response time is less than 2 seconds");
		response.then().assertThat().time(Matchers.lessThan(2000l));
		
	}
	
	@Test(dependsOnMethods = {"addLocationPOSTCallAPITest"}, groups = {"GoogleMap"})
	public void getLocationGETCallAPITest() {
		logger.info("Execution Started : getLocationGETCallAPITest()");
		addAuthor("Lisa");
		logger.info("Author : Lisa");
		/*
		 * Google Maps get Place API (GET): This API Will get existing place details
		 * from Server Complete URL :
		 * http://rahulshettyacademy.com/maps/api/place/get/json?place_id=xxxx&key=qaclick123 
		 * Base URL: https://rahulshettyacademy.com 
		 * Resource: /maps/api/place/get/json 
		 * Query Parameters: key, place_id //( place_id value comes from Add place(POST) response) 
		 * Http request: GET 
		 * Note: Key value is hardcoded and it is always qaclick123 
		 * Sample Response for the Provided Place_Id 
		 * {
			  "location":{
			  "lat" : -38.383494,
			  "lng" : 33.427362
			  },
			  "accuracy":50,
			  "name":"Frontline house",
			  "phone_number":"(+91) 983 893 3937",
			  "address" : "29, side layout, cohen 09",
			  "types": ["shoe park","shop"],
			  "website" : "http://google.com",
			  "language" : "French-IN"
		 * }
		 * 
		 */
		addLog("Sending request to "+EndPoints.GET_LOCATION_GOOGLE_MAP_API);
		logger.info("Sending request to "+EndPoints.GET_LOCATION_GOOGLE_MAP_API);
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.pathParam("place_id", place_id)
								.pathParam("key", "qaclick123").
							when()
								.get(EndPoints.GET_LOCATION_GOOGLE_MAP_API);
		
		addLog("Validating Response Code [200]");
		logger.info("Validating Response Code [200]");
		response.then().assertThat().statusCode(200);

		addLog("Validating Response Content-Type [JSON]");
		logger.info("Validating Response Content-Type [JSON]");
		response.then().assertThat().contentType(ContentType.JSON);

		addLog("Validating Response body contains [ 'location', 'accuracy', 'name', 'phone_number', 'address', 'types', 'website', 'language'] ");
		logger.info("Validating Response body contains [ 'location', 'accuracy', 'name', 'phone_number', 'address', 'types', 'website', 'language'] ");
		response.then().assertThat().body(Matchers.containsString("location"), 
										  Matchers.containsString("accuracy"), 
										  Matchers.containsString("name"),
										  Matchers.containsString("phone_number"),
										  Matchers.containsString("address"),
										  Matchers.containsString("types"),
										  Matchers.containsString("website"),
										  Matchers.containsString("language"));

		addLog("Checking response time is less than 2 seconds");
		logger.info("Checking response time is less than 2 seconds");
		response.then().assertThat().time(Matchers.lessThan(2000l));
		
		
		addLog("Validating response data Expected :"+ JsonSerializer.DEFAULT_READABLE.serialize(place));
		logger.info("Validating response data Expected :"+ JsonSerializer.DEFAULT_READABLE.serialize(place));
		JsonPath responseJsonPath = response.getBody().jsonPath();
		String[] typesStringArr = responseJsonPath.getString("types").split(",");
		Place actualPlace = Place.builder()
					.location(Location.builder().lat(responseJsonPath.getDouble("location.latitude")).lng(responseJsonPath.getDouble("location.longitude")).build())
					.accuracy(Integer.parseInt(responseJsonPath.getString("accuracy")))
					.name(responseJsonPath.getString("name"))
					.phone_number(responseJsonPath.getString("phone_number"))
					.address(responseJsonPath.getString("address"))
					.types(responseJsonPath.getString("types").split(","))
					.website(responseJsonPath.getString("website"))
					.language(responseJsonPath.getString("language"))
					.build();
		
		
		Assert.assertEquals(actualPlace, place);
	}
}
