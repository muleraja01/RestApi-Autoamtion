package files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

	public static JsonPath rawToJson(String reponse) {
		JsonPath jsonPath = new JsonPath(reponse);
		return jsonPath;
	}
}
