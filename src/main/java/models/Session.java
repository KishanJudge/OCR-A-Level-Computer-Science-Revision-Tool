package models;

public class Session {

    private User user;

    public void setUser(User givenUser) {
        user = givenUser;
    }

    public User getUser() {
        return user;
    }
}
