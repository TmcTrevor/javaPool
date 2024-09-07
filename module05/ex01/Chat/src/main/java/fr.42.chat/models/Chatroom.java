package fr._42.chat.models;
import java.util.List;

public class Chatroom {
    private int id;
    private String roomName;
    private User owner;
    private List<Message> messages;


    public Chatroom(User user, String roomName) {
        this.owner = user;
        this.roomName = roomName;
    }

    public int getId() {
        return id;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
            this.roomName = roomName;
    }

    public User getOwner() {
        return this.owner;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return ((Chatroom) obj).getId() == this.id;
        }
        return false;
    }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(this.id + roomName, this.owner);
    // }
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roomName != null ? roomName.hashCode() : 0);
        result = 31 * result + (roomName != null ? this.owner.getLogin().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Room " + roomName + " with Owner " + this.owner;
    }


}
