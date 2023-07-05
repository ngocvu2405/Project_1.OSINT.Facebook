package hust.soict.sec.objectFb;

import java.util.Date;


public class GroupAdmin extends Groups {
	private Long member_request_count;
	private Date updatedTime;
	
	public GroupAdmin(String Id, String name,String privacy, Long member_request_count, Date updatedTime) {
		super(Id, name, privacy);
		this.member_request_count = member_request_count;
		this.updatedTime = updatedTime;
	}
	public GroupAdmin(String Id, String name, String description) {
		super(Id, name, description);
	}

	public Long getMember_request_count() {
		return member_request_count;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}

}
