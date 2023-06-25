package hust.soict.sec.objectFb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hust.soict.sec.objectFb.adminGroupData.Group_Feeds;

public class GroupAdmin extends Groups {
	private String privacy;
	private Long member_request_count;
	private ArrayList<Group_Feeds> groupFeeds = new ArrayList<Group_Feeds>();
	
	public GroupAdmin(String Id, String name,String privacy, Date updated_time, Long member_request_count, ArrayList<Group_Feeds> groupFeeds) {
		super();
		this.privacy = privacy;
		this.member_request_count = member_request_count;
		this.groupFeeds = groupFeeds;
	}

		
	public String getPrivacy() {
		return privacy;
	}

	public Long getMember_request_count() {
		return member_request_count;
	}

	public List<Group_Feeds> getGroupFeeds() {
		return groupFeeds;
	}

}
