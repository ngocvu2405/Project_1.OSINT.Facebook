package hust.soict.sec.objectFb.adminGroupData;

import java.util.Date;

public class uComment {
	private String id, message;
	private Date CreatedTime;
	
	public Date getCreatedTime() {
		return CreatedTime;
	}

	public String getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public uComment(Date CreatedTime, String id, String message) {
		super();
		this.CreatedTime = CreatedTime;
		this.id = id;
		this.message = message;
	}

}
