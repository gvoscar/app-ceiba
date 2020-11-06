package co.com.ceiba.mobile.pruebadeingreso.features.posts.presenters;

public interface PostPresenter {
    void onCreate();

    void onStart();

    void onStop();

    void onDestroy();

    void getAllUserPosts(String userId);
}
