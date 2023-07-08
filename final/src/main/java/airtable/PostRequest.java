<<<<<<< HEAD
package airtable;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.net.HttpURLConnection;
import java.io.*;

public abstract class PostRequest {
	public abstract String reformatData();
	
	public void POSTRequest(String baseId, String tableId, String airtableToken, String res) throws IOException {
		URL url = new URL("https://api.airtable.com/v0/" + baseId + "/" + tableId);
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("POST");
		http.setDoOutput(true);
		http.setRequestProperty("Authorization", "Bearer " + airtableToken);
		http.setRequestProperty("Content-Type", "application/json");
		byte[] out = res.getBytes(StandardCharsets.UTF_8);

		OutputStream stream = http.getOutputStream();
		stream.write(out);

		System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
		http.disconnect();
	}
}
=======
package airtable;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.net.HttpURLConnection;
import java.io.*;

public abstract class PostRequest {
	public abstract String reformatData();
	
	public void POSTRequest(String baseId, String tableId, String airtableToken, String res) throws IOException {
		URL url = new URL("https://api.airtable.com/v0/" + baseId + "/" + tableId);
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("POST");
		http.setDoOutput(true);
		http.setRequestProperty("Authorization", "Bearer " + airtableToken);
		http.setRequestProperty("Content-Type", "application/json");
		byte[] out = res.getBytes(StandardCharsets.UTF_8);

		OutputStream stream = http.getOutputStream();
		stream.write(out);

		System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
		http.disconnect();
	}
}
>>>>>>> 25b63705a174f4bfaa6d2592dbf59a4806f30a25
