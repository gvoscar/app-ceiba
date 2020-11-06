package co.com.ceiba.mobile.pruebadeingreso.features.posts.repositories;

import android.util.Log;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.database.MyDatabase;
import co.com.ceiba.mobile.pruebadeingreso.features.posts.events.PostEvent;
import co.com.ceiba.mobile.pruebadeingreso.libs.base.GreenRobotEventBus;
import co.com.ceiba.mobile.pruebadeingreso.pojos.Post;
import co.com.ceiba.mobile.pruebadeingreso.rest.JSONPlaceholderClient;
import co.com.ceiba.mobile.pruebadeingreso.rest.JSONPlaceholderService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PostRepositoryImpl implements PostRepository {

    private static final String TAG = PostRepositoryImpl.class.getSimpleName();
    private MyDatabase database;
    private CompositeDisposable composite;

    public PostRepositoryImpl() {
        database = MyDatabase.getInstance();
        composite = new CompositeDisposable();
    }

    @Override
    public void getAllUserPosts(String userId) {
        JSONPlaceholderService serviceApi = new JSONPlaceholderClient().getJsonPlaceholderService();
        //composite.add(serviceApi.allUserPosts(userId)
        composite.add(serviceApi.postsUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        Log.d(TAG, "CANTIDAD DE POSTS DEL USUARIO : " + posts.size());
                        check(posts);
                    }
                })
        );
    }

    private void check(List<Post> posts) {
        composite.clear();
        if (posts.isEmpty()) {
            postEvent(PostEvent.NOT_FOUND, "No hay posts");
        } else {
            postEvent(PostEvent.DATA_LOADED, posts);
        }
    }

    private void postEvent(int eventType) {
        postEvent(eventType, null, null);
    }

    private void postEvent(int eventType, String message) {
        postEvent(eventType, message, null);
    }

    private void postEvent(int eventType, List<Post> posts) {
        postEvent(eventType, null, posts);
    }

    private void postEvent(int eventType, String message, List<Post> posts) {
        Log.d(TAG, "POST EVENT : " + eventType);
        PostEvent event = new PostEvent(eventType);
        event.setMessage(message);
        event.setPosts(posts);
        GreenRobotEventBus.getInstance().post(event);
    }
}
