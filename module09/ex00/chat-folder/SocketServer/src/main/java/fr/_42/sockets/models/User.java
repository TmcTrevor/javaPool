package fr._42.sockets.models;

public class User {
    private final long id;
    private final String username;
    private String password;

    public User(String username, String password) {
        this.id = 0;
        this.username = username;
        this.password = password;
    }


    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "{ ID: " + id + ", Username: " + username + " }";
    }
}