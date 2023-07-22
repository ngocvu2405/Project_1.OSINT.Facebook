package airtable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.Scanner;

import org.json.*;
import java.io.FileWriter;



public class GetRequest {

	public static String getFromAirtable(String baseId, String tableId, String tokenAirtable) throws IOException {
		URL url = new URL("https://api.airtable.com/v0/" +baseId +"/" + tableId +"?maxRecords=1000&view=Grid%20view"
				+ "");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");

		httpConn.setRequestProperty("Authorization", "Bearer " + tokenAirtable);

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
				? httpConn.getInputStream()
				: httpConn.getErrorStream();
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		System.out.println(response);
		
		return response;
	}
	public static void toJsonFile(String response, String fileName) {
      
		 try {
	            JSONObject jsonObject = new JSONObject(response);
//	            JSONArray recordsArray = jsonObject.getJSONArray("records");

	            // Writing the JSON data to a file
	            FileWriter fileWriter = new FileWriter(fileName);
	            fileWriter.write(jsonObject.toString());
	            fileWriter.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
    }
	
}