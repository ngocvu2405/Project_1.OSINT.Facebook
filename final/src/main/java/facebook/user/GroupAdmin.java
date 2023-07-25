package facebook.user;

import facebook.GetData;

public class GroupAdmin extends GetData {
    public String order = new String("me?fields=groups%7Bid%2Cname%2Cdescription%2Cadministrator%2"
    		+ "Cmember_count%2Cmember_request_count%7D&access_token=");

    public GroupAdmin(String order) {
        super(order);
        this.order=order;
    }

}
