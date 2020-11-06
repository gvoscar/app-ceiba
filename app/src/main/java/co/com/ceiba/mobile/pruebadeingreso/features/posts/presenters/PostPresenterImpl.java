package co.com.ceiba.mobile.pruebadeingreso.features.posts.presenters;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.features.posts.events.PostEvent;
import co.com.ceiba.mobile.pruebadeingreso.features.posts.interactos.PostInteractor;
import co.com.ceiba.mobile.pruebadeingreso.features.posts.interactos.PostInteractorImpl;
import co.com.ceiba.mobile.pruebadeingreso.features.posts.ui.PostView;
import co.com.ceiba.mobile.pruebadeingreso.libs.eventbus.EventBus;
import co.com.ceiba.mobile.pruebadeingreso.libs.eventbus.GreenRobotEventBus;
import co.com.ceiba.mobile.pruebadeingreso.pojos.Post;

public class PostPresenterImpl implements PostPresenter {

    private EventBus eventBus;
    private PostView view;
    private PostInteractor interactor;

    public PostPresenterImpl(PostView view) {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.view = view;
        this.interactor = new PostInteractorImpl();
    }

    @Override
    public void onCreate() {
        this.eventBus.register(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        this.eventBus.unregister(this);
    }

    @Override
    public void getAllUserPosts(String userId) {
        this.interactor.getAllUserPosts(userId);
    }

    @Subscribe
    public void onEvent(PostEvent event) {
        switch (event.getEventType()) {
            case PostEvent.NOT_FOUND:
                onNotFound(event.getMessage());
                break;
            case PostEvent.DATA_LOADED:
                onDataLoaded(event.getPosts());
                break;
        }
    }

    public void onNotFound(String message) {
        if (view != null) {
            view.onNotFound(message);
        }
    }

    public void onDataLoaded(List<Post> posts) {
        if (view != null) {
            view.onDataLoaded(posts);
        }
    }
}
