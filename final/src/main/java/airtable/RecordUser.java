package airtable;

import java.io.IOException;
import java.util.Scanner;
import org.json.*;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonArray;
import facebook.GetData;
import facebook.user.User;

public class RecordUser extends PostRequest {

	@Override
	public String reformatData() {
		User userFb = new User("me?fields", "id%2Cname");
		String objectId = userFb.objectId;
        String listAttribute = userFb.listAttribute;
        
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Facebook access token: ");
        String accessToken = scanner.nextLine();
        User.setAccessToken(accessToken);
		try {
			String res = GetData.getData(objectId,listAttribute,accessToken);
			System.out.println(res);
//			JsonObject jsonObject = new JsonObject();
//			jsonObject.addProperty("userId", res.split("\"id\":")[1].split(",")[0].replaceAll("\"", ""));
//			jsonObject.addProperty("fullname", res.split("\"name\":")[1].replaceAll("[\\{|\\}\"|\\s]", ""));
//
//			JsonObject fields = new JsonObject();
//			fields.add("userId", jsonObject.get("userId"));
//			fields.add("fullname", jsonObject.get("fullname"));
//
//			JsonObject record = new JsonObject();
//			record.add("fields", fields);
//
//			JsonObject result = new JsonObject();
//			JsonArray records = new JsonArray();
//			records.add(record);
//			result.add("records", records);

			JSONObject jsonObject = new JSONObject(res);

	        JSONObject newJsonObject = new JSONObject();
	        JSONArray recordsArray = new JSONArray();
	        JSONObject fieldsObject = new JSONObject();
	        fieldsObject.put("userId", jsonObject.getString("id"));
	        fieldsObject.put("fullname", jsonObject.getString("name"));
	        JSONObject fieldsWrapper = new JSONObject();
	        fieldsWrapper.put("fields", fieldsObject);
	        recordsArray.put(fieldsWrapper);
	        newJsonObject.put("records", recordsArray);

	        String resData = newJsonObject.toString();
			
	        System.out.println(resData);
	        
			return resData;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error: " +e.getMessage();
		}
        
	}

}
