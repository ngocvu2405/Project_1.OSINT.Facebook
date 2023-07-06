package facebook.user;

import java.io.IOException;

import facebook.GetData;

public class User extends GetData{
    public String objectId = new String("me?fields");
    public String listAttribute = new String("id%2Cname");
//	curl -i -X GET \
//	 "https://graph.facebook.com/v17.0/me?fields=id%2Cname&access_token=EAAT2W7fgLlwBAASGMaUhvDqB3a8BQ3MbiCBsWVZAALwI4o44HHOAvjGhI0H3tQMTSa89GR9BXjCtCPUJUfmDNr6GTKrKoLN73RCUm9QCGaECp40vDckB9zytTy10qTPfsTZAFRxrB7Qz2SRVbGqZCi92QFhq2pfppSKtrqLUL6UPq0BEdXt2ATXTUkpI71x8C2FIdIfFzDUpCHItgRu"
//	setObjectId("me?fields");
//	setListAttribute("id%2Cname");
		

	public User(String objectId, String listAttribute) {
		super(listAttribute, listAttribute);
        this.objectId = objectId;
        this.listAttribute = listAttribute;
	}
	
	public String getObjectId() {
		return objectId;
	}

	public String getListAttribute() {
		return listAttribute;
	}

//	public static String getData(String objectId, String listAttribute, String accessToken) throws IOException{
//		try {
//			String data = getData(objectId, listAttribute, accessToken);
//			return data;
//		} catch (IOException e) {
//			System.out.println("error: " + e.getMessage()); 
//		    e.printStackTrace(); 
//		    return "error: " + e.getMessage();
//	    }
//	}
}
