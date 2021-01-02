import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplesJsonPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		System.out.println(count);
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		String courseTitle = js.get("courses[0].title");
		System.out.println(courseTitle);

	}

}
