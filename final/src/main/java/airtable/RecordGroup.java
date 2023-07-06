package airtable;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import facebook.GetData;
import facebook.group.*;

public class RecordGroup extends PostRequest {

	@Override
	public String reformatData() {
		Group userGr = new Group("me?fields=groups%7Bid%2Cname%2Cdescription%2Cadministrator%7D");
		String objectId = userGr.objectId;
        String listAttribute = userGr.listAttribute;
        
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Facebook access token: ");
        String accessToken = scanner.nextLine();
        Group.setAccessToken(accessToken);
		try {
			String res = GetData.getData(objectId,listAttribute,accessToken);
			System.out.println(res);

			JSONObject jsonObject = new JSONObject(res);
	        JSONArray jsonArray = jsonObject.getJSONObject("groups").getJSONArray("data");
	        JSONArray recordsArray = new JSONArray();

	        for (int i = 0; i < jsonArray.length(); i++) {
	            JSONObject fieldsObject = new JSONObject();
	            JSONObject dataObject = jsonArray.getJSONObject(i);
	            fieldsObject.put("id", dataObject.getString("id"));
	            fieldsObject.put("name", dataObject.getString("name"));
	            fieldsObject.put("administrator", dataObject.getBoolean("administrator"));
	            if (dataObject.has("description")) {
	                fieldsObject.put("description", dataObject.getString("description"));
	            }
	            JSONObject recordObject = new JSONObject();
	            recordObject.put("fields", fieldsObject);
	            recordsArray.put(recordObject);
	        }

	        JSONObject outputObject = new JSONObject();
	        outputObject.put("records", recordsArray);
	        String resData = outputObject.toString();
	        System.out.println(resData);
	        
			return resData;
		} catch (IOException e) {
			e.printStackTrace();
			return "error: " +e.getMessage();
		}
	}

}
