package hust.soict.sec.objectFb;

public class Groups {
	private String Id;
    private String name;
    private String privacy;
    
    public Groups() {};
    
    public Groups(String Id, String name, String privacy) {
        this.name = name;
        this.privacy = privacy;
        this.Id = Id;
    }

	public String getPrivacy() {
		return privacy;
	}

	public String getName() {
        return name;
    }

	public String getId() {
		return Id;
	}

}
