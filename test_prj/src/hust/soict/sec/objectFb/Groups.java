package hust.soict.sec.objectFb;

public class Groups {
	private String Id;
    private String name;
    private String description;
    
    public Groups() {};
    
    public Groups(String Id, String name, String description) {
        this.name = name;
        this.description = description;
        this.Id = Id;
    }

	public String description() {
		return description;
	}

	public String getName() {
        return name;
    }

	public String getId() {
		return Id;
	}

}
