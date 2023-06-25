package hust.soict.sec.objectFb.adminGroupData;

public class uComment {
	private String id, message;
	private Long like_count;
	
	public Long getLike_count() {
		return like_count;
	}

	public String getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public uComment(Long like_count, String id, String message) {
		super();
		this.like_count = like_count;
		this.id = id;
		this.message = message;
	}
	
}
