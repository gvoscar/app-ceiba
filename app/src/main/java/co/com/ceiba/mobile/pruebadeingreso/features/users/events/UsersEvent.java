package co.com.ceiba.mobile.pruebadeingreso.features.users.events;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.pojos.User;

public class UsersEvent {
    public static final int NOT_FOUND = 100;
    public static final int DATA_LOADED = 200;

    private int eventType;
    private String message;
    private List<User> users;

    public UsersEvent() {
    }

    public UsersEvent(int eventType) {
        this.eventType = eventType;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
