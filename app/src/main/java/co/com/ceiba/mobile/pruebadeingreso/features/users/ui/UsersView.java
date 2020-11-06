package co.com.ceiba.mobile.pruebadeingreso.features.users.ui;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.pojos.User;

public interface UsersView {
    void onNotFound(String message);

    void onDataLoaded(List<User> users);
}
