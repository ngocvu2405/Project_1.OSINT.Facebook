package hust.soict.sec.syncToAirtable;

import hust.soict.sec.scoutFacebook.post.PostFeed;

import java.sql.Date;
import java.util.List;
import java.util.*;
public class SyncToAirtable {
    public static void sync(List<PostFeed> postFeeds) {
        // Prepare data for Airtable sync
        List<String> messages = new ArrayList<>();
        List<Date> dates = new ArrayList<>();
        List<String> hashtags = new ArrayList<>();

        for (PostFeed postFeed : postFeeds) {
            messages.add(postFeed.getMessage());
            dates.add(postFeed.getDate());
            hashtags.addAll(postFeed.getHashtags());
        }

        // Sync to Airtable
        // ... your logic here ...
    }
}
