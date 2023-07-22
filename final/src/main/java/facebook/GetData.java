package facebook;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetData {

	protected String order;
	//Set attribute
	public String getAccessToken() {
		return accessToken;
	}
	public String getOrder() {
		return order;
	}
	protected static String accessToken;

	public static void setAccessToken(String accessToken) {
		GetData.accessToken = accessToken;
	}
	//Constructor
	
	protected GetData(String order) {
		this.order = order;
	}
	//get Data from Graph Fb API - STATIC method to use in all Object
	public static String getData(String accessToken, String order) throws IOException {
		URL url = new URL("https://graph.facebook.com/v17.0/" + order +accessToken);//objectId + "=" + listAttribute + "&access_token="
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
				? httpConn.getInputStream()
				: httpConn.getErrorStream();
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		System.out.println(response);
		return response;
	}

}
