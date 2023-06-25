package hust.soict.sec.objectFb;

import java.util.Date;

public class Groups {
	private String Id;
    private String name;
    private Date updated_time;
    
    public Groups() {};
    
    public Groups(String Id, String name, Date updated_time) {
        this.name = name;
        this.updated_time = updated_time;
        this.Id = Id;
    }

	public Date getupdated_time() {
		return updated_time;
	}

	public String getName() {
        return name;
    }

	public String getId() {
		return Id;
	}

}
