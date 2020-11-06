package co.com.ceiba.mobile.pruebadeingreso.features.posts.ui;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.pojos.Post;

public interface PostView {
    void onNotFound(String message);

    void onDataLoaded(List<Post> posts);
}
