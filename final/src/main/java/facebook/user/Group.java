<<<<<<< HEAD
package facebook.user;

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
>>>>>>> 03b99985bb22b10552bf9ce159b1fdfbce4e1a3c
