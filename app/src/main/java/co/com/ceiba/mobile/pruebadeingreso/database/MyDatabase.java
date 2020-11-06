package co.com.ceiba.mobile.pruebadeingreso.database;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.pojos.Post;
import co.com.ceiba.mobile.pruebadeingreso.pojos.User;
import io.realm.Realm;
import io.realm.RealmResults;

public class MyDatabase {

    private static final String TAG = MyDatabase.class.getSimpleName();
    private Realm realm;

    public MyDatabase() {
        realm = Realm.getDefaultInstance();
    }

    public static class SingletonHolder {
        private static final MyDatabase INSTANCE = new MyDatabase();
    }

    public static MyDatabase getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean hasChildren() {
        RealmResults<User> results = realm.where(User.class).findAll();
        return !results.isEmpty();
    }

    public List<User> getUsers() {
        RealmResults<User> results = realm.where(User.class).findAll();
        return results;
    }

    public void saveUsers(List<User> users) {
        realm.beginTransaction();

        for (User user : users) {
            User snapshot = realm.createObject(User.class, user.getId());
            snapshot.setName(user.getName());
            snapshot.setPhone(user.getPhone());
            snapshot.setEmail(user.getEmail());
            snapshot.setUsername(user.getUsername());
            snapshot.setWebsite(user.getWebsite());
        }

        realm.commitTransaction();
    }

    public void savePosts(List<Post> posts) {
        realm.beginTransaction();

        for (Post post : posts) {
            realm.createObject(Post.class, post.getId());
        }

        realm.commitTransaction();
    }
}
