import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// given -all input details
		// when -submit the Api
		// then-validate the response
		
		//Add Google Maps Place
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String Response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(payload.AddPlace())
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)")
				.extract().response().asString();
		System.out.println(Response);
		JsonPath js = new JsonPath(Response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		//Update Place
		String newAddress="70 winter walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n"+ "")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place Details
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
		String updatedAddress = js1.getString("address");
		System.out.println(updatedAddress);
		Assert.assertEquals(newAddress, updatedAddress);
	}

}
