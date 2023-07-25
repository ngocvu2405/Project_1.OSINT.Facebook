package airtable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.json.*;
import facebook.GetData;
import facebook.user.User;

public class RecordUser extends PostRequest {

	@Override
	public String reformatData() throws IOException {
		User userFb = new User("me?fields=id%2Cname&access_token=");

        String order = userFb.order;
		final String API_INFO = new String(Files.readAllBytes(Paths.get("src/main/java/airtable/validatedAPI.json")));
	    JSONObject airtableObject = new JSONObject(API_INFO);
	    final String TOKEN_AIRTABLE = airtableObject.getString("APIToken");
	    final String BASE_AIRTABLE_ID = airtableObject.getString("baseID");
	    final String TABLE_USER = airtableObject.getString("userTable");
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Facebook access token to GET YOUR infomation: ");
        String accessToken = scanner.nextLine();
        User.setAccessToken(accessToken);
//        scanner.close();
		try {
			String res = GetData.getData(accessToken, order);

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
	        POSTRequest(BASE_AIRTABLE_ID, TABLE_USER, TOKEN_AIRTABLE, resData);
	        System.out.println("\u001B[36m Crawling USER DATA is done! \u001B[0m");

			return resData;
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

