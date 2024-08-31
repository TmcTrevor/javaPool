package fr._42.chat.models;

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
            return ((User) obj).id == this.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Object.hash(id, roomName, owner);
    }

    @Override
    public String toString() {
        return "Room " + roomName + " with Owner " + Owner;
    }


}
