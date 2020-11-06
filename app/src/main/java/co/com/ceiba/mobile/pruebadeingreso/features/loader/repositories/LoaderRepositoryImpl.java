package co.com.ceiba.mobile.pruebadeingreso.features.loader.repositories;

import android.util.Log;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.database.MyDatabase;
import co.com.ceiba.mobile.pruebadeingreso.features.loader.events.LoaderEvent;
import co.com.ceiba.mobile.pruebadeingreso.libs.eventbus.EventBus;
import co.com.ceiba.mobile.pruebadeingreso.libs.eventbus.GreenRobotEventBus;
import co.com.ceiba.mobile.pruebadeingreso.pojos.Post;
import co.com.ceiba.mobile.pruebadeingreso.pojos.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.JSONPlaceholderClient;
import co.com.ceiba.mobile.pruebadeingreso.rest.JSONPlaceholderService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class LoaderRepositoryImpl implements LoaderRepository {

    private static final String TAG = LoaderRepositoryImpl.class.getSimpleName();

    private MyDatabase mDatabase;

    private boolean requestApi = false;
    private CompositeDisposable usersComposite;
    private CompositeDisposable postsComposite;


    public LoaderRepositoryImpl() {
        mDatabase = MyDatabase.getInstance();
        usersComposite = new CompositeDisposable();
        // postsComposite = new CompositeDisposable();
    }

    @Override
    public void checkDataRealm() {
        boolean isChildren = mDatabase.hasChildren();
        Log.d(TAG, "HAS CHILDREN: " + isChildren);

        if (!requestApi){
            requestApi = true;
            if (isChildren) {
                postEvent(LoaderEvent.LOADED);
                return;
            }

            postEvent(LoaderEvent.DOWNLOADING, "Descargando datos...");
            getUsersWithRX();
        }

    }

    private void getUsersWithRX() {
        Log.d(TAG, "getUsersWithRX().   OBTENIENDO USUARIOS DEL API");
        JSONPlaceholderService serviceApi = new JSONPlaceholderClient().getJsonPlaceholderService();
        usersComposite.add(serviceApi.users()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        Log.d(TAG, "CANTIDAD DE USUARIOS OBTENIDOS : " + users.size());
                        saveUsers(users);
                    }
                })
        );
    }

    private void saveUsers(List<User> users) {
        Log.d(TAG, "saveUsers().    GUARDAR USUARIOS");
        mDatabase.saveUsers(users);
        usersComposite.clear();

        requestApi = false;
        postEvent(LoaderEvent.LOADED);
        // getPostsWithRX();
    }

    private void getPostsWithRX() {
        Log.d(TAG, "getPostsWithRX().   OBTENIENDO PUBLICACIONES DEL API");
        JSONPlaceholderService serviceApi = new JSONPlaceholderClient().getJsonPlaceholderService();
        postsComposite.add(serviceApi.posts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        Log.d(TAG, "CANTIDAD DE PUBLICACIONES OBTENIDAS : " + posts.size());
                        savePosts(posts);
                    }
                })
        );
    }

    private void savePosts(List<Post> posts) {
        Log.d(TAG, "savePosts().    GUARDAR PUBLICACIONES");
        // mDatabase.savePosts(posts);
        // postsComposite.clear();

        // postEvent(LoaderEvent.LOADED);
    }

    private void postEvent(int eventType) {
        postEvent(eventType, null);
    }

    private void postEvent(int eventType, String message) {
        Log.d(TAG, "postEvent().   Publicar evento.   " + eventType);
        LoaderEvent event = new LoaderEvent();
        event.setEventType(eventType);
        event.setMessage(message);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);
    }
}
