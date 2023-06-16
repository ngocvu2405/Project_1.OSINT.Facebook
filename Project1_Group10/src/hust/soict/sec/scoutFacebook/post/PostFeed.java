package hust.soict.sec.scoutFacebook.post;

import java.util.Date;
import java.util.List;

public class PostFeed {
    private String message;
    private Date date;
    private List<String> hashtags;

    public PostFeed(String message, Date date, List<String> hashtags) {
        this.message = message;
        this.date = date;
        this.hashtags = hashtags;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public List<String> getHashtags() {
        return hashtags;
    }
}
