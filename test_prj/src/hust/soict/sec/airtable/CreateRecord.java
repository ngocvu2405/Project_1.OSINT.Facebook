package hust.soict.sec.airtable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CreateRecord {
	public static void createRecord(JsonObject fields, String tableId, String baseId, String Token) throws IOException {
		try {
		URL url = new URL("https://api.airtable.com/v0/" + baseId + "/" + tableId);
        JsonObject body = new JsonObject();
        body.add("fields", fields);

        JsonArray records = new JsonArray();
        records.add(body);

        JsonObject fullBody = new JsonObject();
        fullBody.add("records", records);
        
        ObjectMapper mapper = new ObjectMapper();
        String json1 = mapper.writeValueAsString(fullBody);
        
        
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        
        
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer " + Token);

        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        con.setDoInput(true);

        OutputStream os = con.getOutputStream();
            byte[] input = json1.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            os.flush();
        
        
        if (con.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            throw new RuntimeException("Failed : HTTP error code : "
                + con.getResponseCode());
        }

//        assertThat(con.getResponseCode()).isEqualTo(201);

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        con.disconnect();
		} catch (MalformedURLException e) {
			 
	        e.printStackTrace();
	 
	      } catch (IOException e) {
	 
	        e.printStackTrace();
	 
	     }
		
	}
}