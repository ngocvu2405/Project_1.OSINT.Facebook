<<<<<<< HEAD
package facebook.user;

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
>>>>>>> 03b99985bb22b10552bf9ce159b1fdfbce4e1a3c
