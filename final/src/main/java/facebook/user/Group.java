<<<<<<< HEAD
package facebook.user;

import java.io.IOException;

import facebook.GetData;

public class Group extends GetData {
    public String order = new String("me?fields=groups%7Bid%2Cname%2Cdescription%2Cadministrator%7D&access_token=");

    public Group(String order) {
        super(order);
        this.order=order;
    }
}
=======
package facebook.user;

import java.io.IOException;

import facebook.GetData;

public class Group extends GetData {
    public String order = new String("me?fields=groups%7Bid%2Cname%2Cdescription%2Cadministrator%7D&access_token=");

    public Group(String order) {
        super(order);
        this.order=order;
    }
}
>>>>>>> 25b63705a174f4bfaa6d2592dbf59a4806f30a25
