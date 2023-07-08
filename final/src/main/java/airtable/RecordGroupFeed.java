package airtable;

import java.io.IOException;
import java.util.Scanner;
import org.json.*;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonArray;
import facebook.GetData;
import facebook.user.GroupFeed;
import facebook.user.User;

public class RecordGroupFeed extends PostRequest {

	@Override
	public String reformatData() {
		GroupFeed groupfeed = new GroupFeed("me?fields=groups%7Bfeed%7Bid%2Cmessage%2Ccreated_time%7D%7D&access_token=");
        String order = groupfeed.order;
        
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Facebook access token: ");
        String accessToken = scanner.nextLine();
        User.setAccessToken(accessToken);
		try {
			String response = GetData.getData(accessToken, order);
            System.out.println(response);

            JSONObject responseObj = new JSONObject(response);
            JSONObject groupsObj = responseObj.getJSONObject("groups");
            JSONArray feedArray = groupsObj.getJSONArray("data");

            JSONObject newJsonObject = new JSONObject();
            JSONArray recordsArray = new JSONArray();

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = feedArray.getJSONObject(i);

                JSONObject fieldsObject = new JSONObject();
                fieldsObject.put("id", feedObj.getString("id"));
                fieldsObject.put("message", feedObj.getString("message"));
                fieldsObject.put("created_time", feedObj.getString("created_time"));

                JSONObject fieldsWrapper = new JSONObject();
                fieldsWrapper.put("fields", fieldsObject);

                recordsArray.put(fieldsWrapper);
            }

            newJsonObject.put("records", recordsArray);
            String reformattedData = newJsonObject.toString();

            System.out.println(reformattedData);
            return reformattedData;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error: " +e.getMessage();
		}
        
	}

}
