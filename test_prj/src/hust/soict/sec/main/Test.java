package hust.soict.sec.main;
import java.io.IOException;
import java.util.*;
import java.io.File;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.types.Comment;
import com.restfb.types.Group;
import com.restfb.types.Post;
import com.restfb.types.User;

import hust.soict.sec.objectFb.*;
import hust.soict.sec.objectFb.adminGroupData.GroupFeed;
import hust.soict.sec.objectFb.adminGroupData.uComment;

public class Test{
	static ObjectMapper mapper = new ObjectMapper();
	static ArrayList<Users> fullUser = new ArrayList<Users>();
    static ArrayList<GroupFeed> groupFeedlist = new ArrayList<GroupFeed>();
    static ArrayList<Groups> groupsList = new ArrayList<Groups>();
    static ArrayList<Groups> groupsAdminList = new ArrayList<Groups>();
    
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
        Users userClient = new Users(user.getId(), user.getName());
        fullUser.add(userClient);
        fetchData(accessToken);
        
        // Transfer USER to JSON file
        try {
            String jsonUser = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fullUser);
            String jsonGroupList = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(groupsList);
            String jsonFeed = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(groupFeedlist);
            String jsonGroupAdminList = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(groupsAdminList);
            File userFile = new File("D:\\HUST\\JavaProjects\\Prj1_Group10\\test_prj\\JSONData\\user.json");
            File groupFile = new File("D:\\HUST\\JavaProjects\\Prj1_Group10\\test_prj\\JSONData\\groupList.json");
            File feedFile = new File("D:\\HUST\\JavaProjects\\Prj1_Group10\\test_prj\\JSONData\\groupFeed.json");
            File groupAdminFile = new File("D:\\HUST\\JavaProjects\\Prj1_Group10\\test_prj\\JSONData\\groupAdminList.json");
            mapper.writeValue(userFile, fullUser);
            mapper.writeValue(groupFile, groupsList);
            mapper.writeValue(feedFile, groupFeedlist);
            mapper.writeValue(groupAdminFile, groupsAdminList);
            System.out.println("JSON files have been created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	static void fetchData(String accessToken) {
    	FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_12_0);
    	User user = fbClient.fetchObject("me", User.class);
        Users userClient = new Users(user.getId(), user.getName());
        fullUser.add(userClient);
 
        try {
        	// Fetch user's groups
            Connection<Group> myGroups = fbClient.fetchConnection("me/groups", Group.class);

            // Process user's groups
            for (List<Group> groupPage : myGroups) 
            {
                for (Group group : groupPage) 
                {                		
                	if (group.getPrivacy() != null) {
                		// Fetch posts from the group
		                Connection<Post> posts = fbClient.fetchConnection(group.getId() + "/feed", Post.class);
		                // Process post feeds
		                
			            for (List<Post> postPage : posts) 
			            {
			            	for (Post post : postPage) 
			            	{
			                   String message = post.getMessage() != null ? post.getMessage() : "";
			                   Date date = post.getCreatedTime() != null ? post.getCreatedTime() : new Date();
			                   String id = post.getId();
		                       // Get comments
			                   Connection<Comment> comments = fbClient.fetchConnection(id+ "/comments", Comment.class); 
			                   ArrayList<uComment> commentList = new ArrayList<uComment>();
			                   for (List<Comment> listcomment : comments) 
			                   {
			                	   for (Comment aComment : listcomment) 
			                	   {
			                		   uComment newComment = new uComment(aComment.getCreatedTime(), aComment.getId(), aComment.getMessage());
			                		   commentList.add(newComment);
			                	   }
			                   }
			                   
			                   // add group feeds then add group to group list
			                   GroupFeed postFeed = new GroupFeed(message, date, id, commentList, group.getId());
			                   groupFeedlist.add(postFeed);
			                }
			            }
		                GroupAdmin userGroup = new GroupAdmin(group.getId(), group.getName(), group.getPrivacy(), group.getMemberRequestCount(), group.getUpdatedTime());
		                groupsAdminList.add(userGroup);
                	} else 
                	{
                		Groups userGroup = new Groups(group.getId(), group.getName(), group.getPrivacy());
                		groupsList.add(userGroup);
                	}
                } // groups loop
            }
            
        } catch (FacebookException e) {
            e.printStackTrace();
        }

	}
	    
}