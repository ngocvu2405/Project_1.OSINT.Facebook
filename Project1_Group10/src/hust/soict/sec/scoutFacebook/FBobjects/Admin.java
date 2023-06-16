package hust.soict.sec.scoutFacebook.FBobjects;

import hust.soict.sec.scoutFacebook.post.PostFeed;

public class Admin extends User {
    public Admin(String name) {
        super(name);
    }

    @Override
    public void post(String message) {
        // Implement logic to post a message as an admin
        // ... your logic here ...
    }

    public void deletePost(PostFeed postFeed) {
        // Implement logic to delete a post
        // ... your logic here ...
    }
}
