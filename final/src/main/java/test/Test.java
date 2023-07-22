package test;
import facebook.*;
import facebook.user.Group;
import facebook.user.GroupAdmin;
import facebook.user.GroupFeed;
import facebook.user.User;

import java.io.IOException;
import java.util.*;
public class Test {

	public static void main(String[] args) {
//		User userFb = new User("me?fields", "id%2Cname");
		GroupAdmin groupFb = new GroupAdmin("me?fields=groups%7Bid%2Cname%2Cdescription%2Cadministrator%2Cmember_count%2Cmember_request_count%7D&access_token=");
       
        GroupFeed groupfeed = new GroupFeed("me?fields=groups%7Bfeed%7Bid%2Cmessage%2Ccreated_time%7D%7D&access_token=");
        String order = groupfeed.order;
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Facebook access token: ");
        String accessToken = scanner.nextLine();
        User.setAccessToken(accessToken);
		try {
			GetData.getData(accessToken,order);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
