package airtable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
		try(Scanner s = new Scanner(responseStream).useDelimiter("\\A")) {
			String response = s.hasNext() ? s.next() : "";
			System.out.println(response);
			return response;
		}	
	}
	public static void toJsonFile(String response, String fileName) {
      
		try (FileWriter fileWriter = new FileWriter(fileName)) {
		    JSONObject jsonObject = new JSONObject(response);
		    fileWriter.write(jsonObject.toString());
		} catch (IOException ex) {
		System.out.println("Cannot write json to string");
		}

    }
	
}