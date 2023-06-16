package hust.soict.sec.scoutFacebook;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.types.Group;
import com.restfb.types.Post;
import com.restfb.types.User;
import hust.soict.sec.scoutFacebook.post.PostFeed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ScoutFacebook {
    private static final String ACCESS_TOKEN = "EAAKOcuxF84gBAGmNshgZCF7nVMmgTiA7gNEDPkouTGwuXRmKDOzVmueLxgQCIdR2pZC3UYGkE2zVa1IGhGj9SvGDGWvLUdNX6NgcwyFZChjOIoZB9OKDuhh9ZA7UPAwzKAXyMAhfKEVwVofNNo9ZAGDKlssZBOvdjAwoExZBf6rH3vUzNDVtoHnxgK5EmXr1ZAOF32giHZCYgUb2lRGf98PCbT";
    private static final String GROUP_ID = "166181893083621";

    private static List<PostFeed> postFeeds = new ArrayList<>();

    public static void main(String[] args) {
        scheduleFetchData();
    }

    private static void scheduleFetchData() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                fetchData();
                processFeeds();
            }
        };

        // Schedule the task to run every 2 months (adjust the interval as needed)
        long interval = 2L * 30L * 24L * 60L * 60L * 1000L; // 2 months in milliseconds
        timer.schedule(task, new Date(), interval);
    }

    private static void fetchData() {
        // Fetch data from the Facebook API (groups, members, feeds)
        FacebookClient facebookClient = new DefaultFacebookClient(ACCESS_TOKEN, Version.VERSION_12_0);
        try {
            // Fetch group information
            Group group = facebookClient.fetchObject(GROUP_ID, Group.class,
                    Parameter.with("fields", "name,description"));

            System.out.println("Group Name: " + group.getName());
            System.out.println("Group Description: " + group.getDescription());
            //System.out.println("Group Member Count: " + group.getMember().getSummary().getTotalCount());

            // Fetch posts from the group
            Connection<Post> posts = facebookClient.fetchConnection(GROUP_ID + "/feed", Post.class,
                    Parameter.with("fields", "message,created_time,attachments"));

            // Clear the previous post feeds
            postFeeds.clear();

            // Convert fetched posts to PostFeed objects
            for (Post post : posts) {
                String message = post.getMessage() != null ? post.getMessage() : "";
                Date date = post.getCreatedTime() != null ? post.getCreatedTime() : new Date();
                List<String> hashtags = extractHashtagsFromMessage(message);

                PostFeed postFeed = new PostFeed(message, date, hashtags);
                postFeeds.add(postFeed);
            }
        } catch (FacebookException e) {
            e.printStackTrace();
        }
    }

    private static List<String> extractHashtagsFromMessage(String message) {
        List<String> hashtags = new ArrayList<>();

        // Extract hashtags from the message
        // ... your logic here ...

        return hashtags;
    }

    private static void processFeeds() {
        // Process the fetched feeds (perform any required data analysis or operations)
        // ... your logic here ...

        // Example: Print the fetched post feeds
        System.out.println("Fetched Post Feeds:");
        for (PostFeed postFeed : postFeeds) {
            System.out.println("Message: " + postFeed.getMessage());
            System.out.println("Date: " + postFeed.getDate());
            System.out.println("Hashtags: " + postFeed.getHashtags());
            System.out.println();
        }
    }

    public static List<PostFeed> getPostFeeds() {
        return postFeeds;
    }
}
