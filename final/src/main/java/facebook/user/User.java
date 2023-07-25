package facebook.user;

import facebook.GetData;

public class User extends GetData{

    public String order = new String("me?fields=id%2Cname&access_token=");
		
	public User(String order) {
		super(order);
		this.order = order;
	}

}

