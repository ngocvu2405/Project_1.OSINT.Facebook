package hust.soict.sec.objectFb.adminGroupData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupFeed {
    private String message;
    private Date createdDate;
    private String id;
    private ArrayList<uComment> comment_list;
    private String id_group;
    
    public GroupFeed() {}
    
    public GroupFeed(String message, Date createdDate, String id, ArrayList<uComment> comment_list, String id_group) {
        this.message = message;
        this.createdDate = createdDate;
        this.id = id;
        this.comment_list = comment_list;
        this.id_group = id_group;
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

	public String getId_group() {
		return id_group;
	}

}
