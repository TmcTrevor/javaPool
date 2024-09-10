package fr._42.chat.exceptions;
public class ChatroomNotFoundException extends RuntimeException {
    public ChatroomNotFoundException(String message)  {
        super(message);
    }
}