package airtable;

import java.io.IOException;
import java.util.Scanner;
import org.json.*;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonArray;
import facebook.GetData;
import facebook.user.User;

public class RecordUser extends PostRequest  {

	@Override
	public String reformatData() throws IOException {
		User userFb = new User("me?fields=id%2Cname&access_token=");

        String order = userFb.order;
        
		try(Scanner scanner = new Scanner(System.in)) {
	        System.out.print("Enter the Facebook access token: ");
	        String accessToken = scanner.nextLine();
	        User.setAccessToken(accessToken);
			String res = GetData.getData(accessToken, order);
			System.out.println(res);

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
	        POSTRequest("appfpkYiYDZtMWJhA", "tblexw8RrU1S7drHh", "patJOGkmzGUONSJVC.1e139f03d8fc3789fa64c266896a4a32fd875c90d0c83e895e28e49a44ed89b7", resData);
	        
			return resData;
		} catch (JSONException e) {
		    // Handle JSONException
		    return("Error: Required fields not found in the response.");
		} catch (IOException e) {
		    // Handle IOException
		    return("Error: An I/O error occurred.");
		} catch (Exception e) {
		    // Handle other exceptions
		    return("Error: An unexpected error occurred.");
		}
        
	}

}