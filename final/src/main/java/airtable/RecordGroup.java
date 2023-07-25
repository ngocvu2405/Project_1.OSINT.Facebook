package airtable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import facebook.GetData;
import facebook.user.*;

public class RecordGroup extends PostRequest {

	@Override
	public String reformatData() throws IOException {
		Group userGr = new Group("me?fields=groups%7Bid%2Cname%2Cdescription%2Cadministrator%7D&access_token=");
		String order = userGr.order;
		final String API_INFO = new String(Files.readAllBytes(Paths.get("src/main/java/airtable/validatedAPI.json")));
	    JSONObject airtableObject = new JSONObject(API_INFO);
	    final String TOKEN_AIRTABLE = airtableObject.getString("APIToken");
	    final String BASE_AIRTABLE_ID = airtableObject.getString("baseID");
	    final String TABLE_GROUP = airtableObject.getString("groupTable");
        
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Facebook GROUPS access token: ");
        String accessToken = scanner.nextLine();
        Group.setAccessToken(accessToken);
		try {
			String res = GetData.getData(accessToken, order);
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
			        POSTRequest(BASE_AIRTABLE_ID, TABLE_GROUP, TOKEN_AIRTABLE, resData);
		        }
	        System.out.println("\u001B[36m Crawling GROUP DATA is done! \u001B[0m");
			return null;
		} catch (JSONException e) {
		    // Handle JSONException
			System.out.println("Error: Required fields not found in the response.");
			return null;

		} catch (IOException e) {
		    // Handle IOException
			System.out.println("Error: An I/O error occurred.");
			return null;

		} catch (NoSuchElementException e) {
			System.out.println("Error: You do not enter any token");
			return null;

		}
		catch (Exception e) {
			System.out.println("Error: An unexpected error occurred.");
			return null;

		}
	}

}


