package fr._42.chat.models;

import java.util.List;
public class User {
    private int id;
    private String login;
    private String password;
    private List<Chatroom> CreatedRooms;
    private List<Chatroom> socializingRooms;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public List<Chatroom> getCreatedRooms() {
        return CreatedRooms;
    }
    public void setCreatedRooms(List<Chatroom> createdRooms) {
        CreatedRooms = createdRooms;
    }
    public List<Chatroom> getSocializingRooms() {
        return socializingRooms;
    }
    public void setSocializingRooms(List<Chatroom> socializingRooms) {
        this.socializingRooms = socializingRooms;
    }
    public int getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return ((User) obj).id == this.id;
        }
        return false;
    }
    // @Override
    // public int hashCode() {
    //     return Object.hash(this.id, login);
    // }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return login;
    }
}
