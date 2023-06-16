package hust.soict.sec.exportToExcel;

import hust.soict.sec.scoutFacebook.post.Feed;

import java.util.List;

public class ExportToExcel {
    public static void export(List<PostFeed> postFeeds) {
        // Prepare data for Excel export
        List<String> messages = new ArrayList<>();
        List<Date> dates = new ArrayList<>();
        List<String> hashtags = new ArrayList<>();

        for (PostFeed postFeed : postFeeds) {
            messages.add(postFeed.getMessage());
            dates.add(postFeed.getDate());
            hashtags.addAll(postFeed.getHashtags());
        }

        // Export to Excel
        // ... your logic here ...
    }
}
