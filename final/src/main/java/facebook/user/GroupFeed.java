package facebook.user;

import facebook.GetData;

public class GroupFeed extends GetData {
    public String order = new String("me?fields=groups%7Bfeed%7Bid%2Cmessage%2Ccreated_time%7D%7D&access_token=");

    public GroupFeed(String order) {
        super(order);
        this.order=order;
    }

}
