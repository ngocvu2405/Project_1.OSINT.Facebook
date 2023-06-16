package hust.soict.sec.scoutFacebook.FBobjects;

public abstract class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void post(String message);
}
