package facebook.user;

import java.io.IOException;

import facebook.GetData;

public class User extends GetData{
    public String objectId = new String("me?fields");
    public String listAttribute = new String("id%2Cname");
		

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

}
