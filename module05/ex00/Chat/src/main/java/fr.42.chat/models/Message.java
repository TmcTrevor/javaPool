package fr._42.chat.models;

import java.util.Date;




public class Message {
    private int id;
    private String text;
    private User author;
    private Chatroom room;
    private Date datetime;

    public Message(User author, Chatroom room, String text) {
        this.author = author;
        this.room = room;
        this.text = text;
        this.datetime = new Date();
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public Date getDatetime() {
        return datetime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Message) {
            return ((Message) obj).id == this.id;
        }
        return false;
    }
    
    // @Override
    // public int hashCode() {
    //     return Object.hash(id, text, author, room, datetime);
    // }
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * author.hashCode();
        result = 31 * room.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "Message from " + author + " in room " + room + " at " + datetime + " : " + text;
    }

}
