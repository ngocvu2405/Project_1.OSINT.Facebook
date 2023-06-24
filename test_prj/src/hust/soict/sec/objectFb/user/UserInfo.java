package hust.soict.sec.objectFb.user;


public class UserInfo {

	String tokenUser = AccessToken.getAccessToken();
	
	
	public String userId;
	public String fullname;
	public UserInfo(String userId, String fullname) {
		super();
		this.userId = userId;
		this.fullname = fullname;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", fullname=" + fullname + "]";
	}
	
	
}