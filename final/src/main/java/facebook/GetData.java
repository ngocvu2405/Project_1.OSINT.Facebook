package facebook;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetData {
	protected String objectId;
	protected String listAttribute;

	//Set attribute
	protected void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	protected void setListAttribute(String listAttribute) {
		this.listAttribute = listAttribute;
	}
	
	public String getObjectId() {
		return objectId;
	}
	public String getListAttribute() {
		return listAttribute;
	}
	public String getAccessToken() {
		return accessToken;
	}
	protected static String accessToken;

	public static void setAccessToken(String accessToken) {
		GetData.accessToken = accessToken;
	}
	//Constructor
	protected GetData(String objectId, String listAttribute) {
		this.objectId = objectId;
		this.listAttribute = listAttribute;
	}
	
	//get Data from Graph Fb API - STATIC method to use in all Object
	public static String getData(String objectId, String listAttribute, String accessToken) throws IOException {
		URL url = new URL("https://graph.facebook.com/v17.0/" + objectId + "=" + listAttribute + "&access_token=" +accessToken);
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
