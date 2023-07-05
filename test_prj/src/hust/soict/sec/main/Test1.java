package hust.soict.sec.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.Comment;
import com.restfb.types.Group;
import com.restfb.types.Post;
import com.restfb.types.User;

import hust.soict.sec.objectFb.AccessToken;
import hust.soict.sec.objectFb.GroupAdmin;
import hust.soict.sec.objectFb.Users;
import hust.soict.sec.objectFb.adminGroupData.GroupFeed;
import hust.soict.sec.objectFb.adminGroupData.uComment;

public class Test1 {
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
        // Fetch user's groups
        Connection<Group> myGroups = fbClient.fetchConnection("me/groups", Group.class);
        ArrayList<uComment> commentList = new ArrayList<uComment>();
        int comment_count=0;
        for (List<Group> groupPage : myGroups) 
        {
            for (Group group : groupPage) 
            { 
            	System.out.println(group.getName());
            	if(group.getPrivacy() != null) {
            		 // Fetch posts from the group
	                Connection<Post> posts = fbClient.fetchConnection(group.getId() + "/feed", Post.class);
	                // Process post feeds
		            for (List<Post> postPage : posts) 
		            {
		            	for (Post post : postPage) 
		            	{
		                   String message = post.getMessage() != null ? post.getMessage() : "";
		                   Date date = post.getUpdatedTime() != null ? post.getUpdatedTime() : new Date();
		                   String id = post.getId();
		                   System.out.println("Post: " + id + message + date);
	                       // Get comments
		                   Connection<Comment> comments = fbClient.fetchConnection(id+ "/comments", Comment.class); 
		                   
		                   
		                   for (List<Comment> listcomment : comments) 
		                   {
		                	   for (Comment aComment : listcomment) 
		                	   {
		                		   System.out.println(aComment.getId() + " " + aComment.getMessage() + " " + aComment.getCreatedTime());
		                		   uComment newComment = new uComment(aComment.getCreatedTime(), aComment.getId(), aComment.getMessage());
		                		   commentList.add(comment_count, newComment);
		                		   comment_count++;
		                	   }
		                   }
	
		                }
		            }
            	}
            	
            }
        }
        for (uComment comment : commentList) {
            System.out.println(comment.getId() + comment.getMessage() + comment.getCreatedTime());
        }
	}
}
