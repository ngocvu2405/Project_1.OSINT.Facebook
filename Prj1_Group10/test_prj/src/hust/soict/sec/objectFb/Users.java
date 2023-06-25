package hust.soict.sec.objectFb;


public class Users {

	String tokenUser = AccessToken.getAccessToken();
	
	private String userId;
	private String fullname;
	 // Constructor
    public Users() {
    }
    
	public Users(String userId, String fullname) {
		super();
		this.userId = userId;
		this.fullname = fullname;
	}
	
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", fullname=" + fullname + "]";
	}
	
	public String getTokenUser() {
		return tokenUser;
	}
	public String getUserId() {
		return userId;
	}
	public String getFullname() {
		return fullname;
	}
}