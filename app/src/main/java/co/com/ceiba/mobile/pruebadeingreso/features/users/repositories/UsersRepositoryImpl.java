package co.com.ceiba.mobile.pruebadeingreso.features.users.repositories;

import android.util.Log;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.database.MyDatabase;
import co.com.ceiba.mobile.pruebadeingreso.features.loader.events.LoaderEvent;
import co.com.ceiba.mobile.pruebadeingreso.features.users.events.UsersEvent;
import co.com.ceiba.mobile.pruebadeingreso.libs.eventbus.GreenRobotEventBus;
import co.com.ceiba.mobile.pruebadeingreso.pojos.User;

public class UsersRepositoryImpl implements UsersRepository {

    private static final String TAG = UsersRepositoryImpl.class.getSimpleName();
    private MyDatabase database;

    public UsersRepositoryImpl() {
        database = MyDatabase.getInstance();
    }

    @Override
    public void getData() {
        Log.d(TAG, "getData().  OBTENER DATOS DE USUARIOS");
        List<User> users = database.getUsers();
        Log.d(TAG, "CANTIDAD DE USUARIOS : " + users.size());
        if (users.isEmpty()) {
            postEvent(UsersEvent.NOT_FOUND, "No hay datos en la base de datos.");
        } else {
            postEvent(UsersEvent.DATA_LOADED, users);
        }
    }

    private void postEvent(int eventType) {
        postEvent(eventType, null, null);
    }

    private void postEvent(int eventType, String message) {
        postEvent(eventType, message, null);
    }

    private void postEvent(int eventType, List<User> users) {
        postEvent(eventType, null, users);
    }

    private void postEvent(int eventType, String message, List<User> users) {
        Log.d(TAG, "POST EVENT : " + eventType);
        UsersEvent event = new UsersEvent(eventType);
        event.setMessage(message);
        event.setUsers(users);

        GreenRobotEventBus.getInstance().post(event);
    }
}
