package fr._42.chat.models;

import java.util.List;
public class User {
    private int id;
    private String login;
    private String password;
    private List<Chatroom> CreatedRooms;
    private List<Chatroom> socializingRooms;


    public User() {}

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
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
    public String getPassword() {
        return password;
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
        String  info =  "{id=" + id+ ",login=\"" + login + "\",password=\""+ password + "\"";
        StringBuilder createdROoms = new StringBuilder(",\nCreatedRooms = ");
        StringBuilder rooms = new StringBuilder(",\nsocializingRooms = ");
        if (CreatedRooms == null)
            createdROoms.append("null");
        else
            for (Chatroom room : CreatedRooms)
            {
                createdROoms.append(room);
                createdROoms.append("\n");
            }
        if (socializingRooms == null)
            rooms.append("null");
        else
            for (Chatroom room : socializingRooms)
            {
                rooms.append(room);
                rooms.append("\n");
            }
        return info + " " + createdROoms + " " + rooms+ " }";
    }
}
