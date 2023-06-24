package hust.soict.sec.main;
import java.io.IOException;
import java.util.*;
import java.io.File;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;

import hust.soict.sec.objectFb.user.*;

public class Main{
	static ObjectMapper mapper = new ObjectMapper();
	static ArrayList<UserInfo> fullUser = new ArrayList<UserInfo>();
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Get the accessToken from keyboard
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Facebook access token: ");
        String accessToken = scanner.nextLine();
        AccessToken.setAccessToken(accessToken);
        
        //Get and add UserInfo
    	FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_12_0);
    	User user = fbClient.fetchObject("me", User.class);
        UserInfo userClient = new UserInfo(user.getId(), user.getName());
        fullUser.add(userClient);
        
        
        //Transfer USER to JSON file
		String jsonUser = mapper.writeValueAsString(fullUser);
		File userFile = new File("E:\\Nam_2\\2022.2\\prj1\\test_prj\\JSONData\\user.json");
		mapper.writeValue(userFile, fullUser);
	}

}
