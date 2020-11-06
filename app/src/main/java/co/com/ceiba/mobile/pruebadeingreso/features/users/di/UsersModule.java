package co.com.ceiba.mobile.pruebadeingreso.features.users.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.database.MyDatabase;
import co.com.ceiba.mobile.pruebadeingreso.features.users.adapters.UsersAdapter;
import co.com.ceiba.mobile.pruebadeingreso.features.users.adapters.UsersAdapterListener;
import co.com.ceiba.mobile.pruebadeingreso.features.users.interactors.UsersInteractor;
import co.com.ceiba.mobile.pruebadeingreso.features.users.interactors.UsersInteractorImpl;
import co.com.ceiba.mobile.pruebadeingreso.features.users.presenters.UsersPresenter;
import co.com.ceiba.mobile.pruebadeingreso.features.users.presenters.UsersPresenterImpl;
import co.com.ceiba.mobile.pruebadeingreso.features.users.repositories.UsersRepository;
import co.com.ceiba.mobile.pruebadeingreso.features.users.repositories.UsersRepositoryImpl;
import co.com.ceiba.mobile.pruebadeingreso.features.users.ui.UsersView;
import co.com.ceiba.mobile.pruebadeingreso.libs.base.EventBus;
import co.com.ceiba.mobile.pruebadeingreso.pojos.User;
import dagger.Module;
import dagger.Provides;

@Module
public class UsersModule {
    UsersView view;
    UsersAdapterListener listener;

    public UsersModule(UsersView view, UsersAdapterListener listener) {
        this.view = view;
        this.listener = listener;
    }

    @Provides
    @Singleton
    UsersView providesUsersView() {
        return view;
    }

    @Provides
    @Singleton
    UsersPresenter providesUsersPresenter(EventBus eventBus, UsersView view, UsersInteractor interactor) {
        return new UsersPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    UsersInteractor providesUsersInteractor(UsersRepository repository) {
        return new UsersInteractorImpl(repository);
    }

    @Provides
    @Singleton
    UsersRepository providesUsersRepository(MyDatabase database, EventBus eventBus) {
        return new UsersRepositoryImpl(database, eventBus);
    }

    @Provides
    @Singleton
    MyDatabase providesMyDatabase() {
        return MyDatabase.getInstance();
    }

    @Provides
    @Singleton
    UsersAdapter providesUsersAdapter(List<User> userList, UsersAdapterListener listener) {
        return new UsersAdapter(userList, listener);
    }

    @Provides
    @Singleton
    UsersAdapterListener providesUsersAdapterListener() {
        return this.listener;
    }

    @Provides
    @Singleton
    List<User> providesUserList() {
        return new ArrayList<User>();
    }
}
