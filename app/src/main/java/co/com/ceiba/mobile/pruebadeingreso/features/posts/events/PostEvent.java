package co.com.ceiba.mobile.pruebadeingreso.features.posts.events;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.pojos.Post;

public class PostEvent {
    public static final int NOT_FOUND = 100;
    public static final int DATA_LOADED = 200;

    private int eventType;
    private String message;
    private List<Post> posts;

    public PostEvent() {
    }

    public PostEvent(int eventType) {
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
