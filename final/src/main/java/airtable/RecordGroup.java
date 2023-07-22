package airtable;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import facebook.GetData;
import facebook.user.*;

public class RecordGroup extends PostRequest {

	@Override
	public String reformatData() {
		Group userGr = new Group("me?fields=groups%7Bid%2Cname%2Cdescription%2Cadministrator%7D&access_token=");
		String order = userGr.order;

        
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Facebook access token: ");
        String accessToken = scanner.nextLine();
        Group.setAccessToken(accessToken);
		try {
			String res = GetData.getData(accessToken, order);
			System.out.println(res);
        	JSONObject jsonObject = new JSONObject(res);
	        JSONArray jsonArray = jsonObject.getJSONObject("groups").getJSONArray("data");

		        for (int i = 0; i < jsonArray.length(); i++) {

			        JSONArray recordsArray = new JSONArray();
		            JSONObject fieldsObject = new JSONObject();
		            JSONObject dataObject = jsonArray.getJSONObject(i);
		            
		            fieldsObject.put("description", dataObject.isNull("description") ? null : dataObject.getString("description"));
		            fieldsObject.put("id", dataObject.getString("id"));
		            fieldsObject.put("name", dataObject.getString("name"));
		            fieldsObject.put("administrator", dataObject.getBoolean("administrator"));

		            JSONObject recordObject = new JSONObject();
		            recordObject.put("fields", fieldsObject);
		            recordsArray.put(recordObject);
		            JSONObject outputObject = new JSONObject();
			        outputObject.put("records", recordsArray);
			        String resData = outputObject.toString();
			        System.out.println(resData);
			        POSTRequest("appfpkYiYDZtMWJhA", "tblhmlceroOgnh6Ed", "patJOGkmzGUONSJVC.1e139f03d8fc3789fa64c266896a4a32fd875c90d0c83e895e28e49a44ed89b7", resData);
		        }
	        
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return "error: " +e.getMessage();
		}
	}

}