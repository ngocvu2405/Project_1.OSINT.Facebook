package hust.soict.sec.objectFb.adminGroupData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Group_Feeds {
    private String message;
    private Date createdDate;
    private String id;
    private ArrayList<uComment> comment_list;
    
    public Group_Feeds() {}
    
    public Group_Feeds(String message, Date createdDate, String id, ArrayList<uComment> comment_list) {
        this.message = message;
        this.createdDate = createdDate;
        this.id = id;
        this.comment_list = comment_list;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getId() {
        return id;
    }

	public ArrayList<uComment> getComment_list() {
		return comment_list;
	}

}
