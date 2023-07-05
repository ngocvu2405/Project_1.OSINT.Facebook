package hust.soict.sec.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import hust.soict.sec.objectFb.*;
import hust.soict.sec.objectFb.adminGroupData.GroupFeed;
import hust.soict.sec.objectFb.adminGroupData.uComment;

public class Main {
    static ObjectMapper mapper = new ObjectMapper();
    static ArrayList<Users> fullUser = new ArrayList<Users>();
//    static ArrayList<GroupFeed> groupFeeds = new ArrayList<GroupFeed>();
    static ArrayList<Groups> groupsList = new ArrayList<Groups>();
    static ArrayList<Groups> groupsAdminList = new ArrayList<Groups>();

    public static void main(String[] args) throws IOException {
        // Get the accessToken from keyboard
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Facebook access token: ");
        String accessToken = scanner.nextLine();
        AccessToken.setAccessToken(accessToken);

        // Get and add UserInfo
        fetchData(accessToken);

        // Transfer USER to JSON file
        try {
            String jsonUser = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fullUser);
            String jsonGroupList = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(groupsList);
//            String jsonFeed = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(groupFeeds);
            String jsonGroupAdminList = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(groupsAdminList);
            // Update the file paths as per your requirements
            File userFile = new File("D:\\HUST\\JavaProjects\\Prj1_Group10\\test_prj\\JSONData\\user.json");
            File groupFile = new File("D:\\HUST\\JavaProjects\\Prj1_Group10\\test_prj\\JSONData\\groupList.json");
//            File feedFile = new File("D:\\HUST\\JavaProjects\\Prj1_Group10\\test_prj\\JSONData\\groupFeed.json");
            File groupAdminFile = new File("D:\\HUST\\JavaProjects\\Prj1_Group10\\test_prj\\JSONData\\groupAdminList.json");
            mapper.writeValue(userFile, fullUser);
            mapper.writeValue(groupFile, groupsList);
//            mapper.writeValue(feedFile, groupFeeds);
            mapper.writeValue(groupAdminFile, groupsAdminList);
            System.out.println("JSON files have been created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void fetchData(String accessToken) {
        try {
            // Fetch user's groups
            String groupsUrl = "https://graph.facebook.com/v17.0/me/groups?fields=id%2Cname%2Cdescription%2Cadministrator&access_token=" + accessToken;
            URL url = new URL(groupsUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                String responseData = readResponseStream(inputStream);

//                connection.disconnect();
                // Process the response data
                JsonNode rootNode = mapper.readTree(responseData);
                JsonNode groupsNode = rootNode.get("groups");
                
                // Check if the response contains an array of groups or a single group
                if (groupsNode.isArray()) {
                	ArrayNode groupsArray = (ArrayNode) groupsNode;
                    // Iterate over each group
                    for (int i = 0; i < groupsArray.size(); i++) {
                        ObjectNode groupNode = (ObjectNode) groupsArray.get(i);
                        String groupId = groupNode.get("id").asText();
                        boolean isAdmin = groupNode.get("administrator").asBoolean();
                        String description = groupNode.get("description").asText();
                        String name = groupNode.get("name").asText();  
                        // Only process administrator groups
                        if (isAdmin) {
                            // Fetch the group's feeds
                            String feedUrl = "https://graph.facebook.com/v17.0/277497698183334?fields=privacy%2Cmember_request_count%2Cfeed%7Bmessage%2Cid%2Ccreated_time%2Ccomments%7Blike_count%2Cmessage%2Cid%7D%7D&access_token=" + accessToken;
                            url = new URL(feedUrl);
                            connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            responseCode = connection.getResponseCode();
                            ArrayList<GroupFeed> groupFeedList = new ArrayList<GroupFeed>();
                            String privacy = null;
                            int member_request_count = 0;
                            ArrayList<uComment> comment_list = new ArrayList<>();
                            
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                inputStream = connection.getInputStream();
                                responseData = readResponseStream(inputStream);

                                // Parse the JSON response
                                groupNode = mapper.readValue(responseData, ObjectNode.class);
                                ObjectNode groupInfoNode = (ObjectNode) groupNode;
                                privacy = groupInfoNode.get("privacy").asText();
                                member_request_count = groupInfoNode.get("member_request_count").asInt();
    							ArrayNode feedNode = (ArrayNode) rootNode.get("feed");
    							
                                // Iterate over each feed in the group
                                for (int j = 0; j < feedNode.size(); j++) {
                                    ObjectNode feedObject = (ObjectNode) feedNode.get(j);
                                    String feedId = feedObject.get("id").asText();
                                    Date createdDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(feedObject.get("created_time").asText());
                                    String message = feedObject.get("message").asText();
                                    
                                    // Create an array for comments
                                    comment_list.clear();

                                    // Fetch the comments for the feed
                                    ArrayNode commentsNode = (ArrayNode) feedObject.get("comments");
                                    for (int k = 0; k < commentsNode.size(); k++) {
                                        ObjectNode commentObject = (ObjectNode) commentsNode.get(k);
                                        int likeCount = commentObject.get("like_count").asInt();
                                        String commentId = commentObject.get("id").asText();
                                        String commentMessage = commentObject.get("message").asText();

                                        // Create a uComment object and add it to the comment_list
                                        uComment comment = new uComment(likeCount, commentId, commentMessage);
                                        comment_list.add(comment);
                                    }

                                    // Create a GroupFeed object with the comment_list
                                    GroupFeed groupFeed = new GroupFeed(message, createdDate, feedId, comment_list, groupId);
                                    groupFeedList.add(groupFeed);
                                }
                            }
                            // Create a Group object
                            GroupAdmin group = new GroupAdmin(groupId, name, privacy, member_request_count, groupFeedList);
                            groupsAdminList.add(group);
                        }
                    } 
                } else {
                	System.out.println("No groups found.");
                }
            } else {
                System.out.println("Failed to fetch user's groups. Response code: " + responseCode);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    static String readResponseStream(InputStream inputStream) throws IOException {
        Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}

