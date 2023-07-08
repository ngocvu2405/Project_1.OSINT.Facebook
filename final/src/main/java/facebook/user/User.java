<<<<<<< HEAD
package facebook.user;

import java.io.IOException;

import facebook.GetData;

public class User extends GetData{

    public String order = new String("me?fields=id%2Cname&access_token=");
		
	public User(String order) {
		super(order);
		this.order = order;
	}

}
=======
package facebook.user;

import java.io.IOException;

import facebook.GetData;

public class User extends GetData{

    public String order = new String("me?fields=id%2Cname&access_token=");
		
	public User(String order) {
		super(order);
		this.order = order;
	}

}
>>>>>>> 25b63705a174f4bfaa6d2592dbf59a4806f30a25
