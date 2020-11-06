package co.com.ceiba.mobile.pruebadeingreso.features.users.presenters;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.features.users.events.UsersEvent;
import co.com.ceiba.mobile.pruebadeingreso.features.users.interactors.UsersInteractor;
import co.com.ceiba.mobile.pruebadeingreso.features.users.interactors.UsersInteractorImpl;
import co.com.ceiba.mobile.pruebadeingreso.features.users.ui.UsersView;
import co.com.ceiba.mobile.pruebadeingreso.libs.base.EventBus;
import co.com.ceiba.mobile.pruebadeingreso.libs.base.GreenRobotEventBus;
import co.com.ceiba.mobile.pruebadeingreso.pojos.User;

public class UsersPresenterImpl implements UsersPresenter {

    private EventBus eventBus;
    private UsersView view;
    private UsersInteractor interactor;

    public UsersPresenterImpl(EventBus eventBus, UsersView view, UsersInteractor interactor) {
        // this.eventBus = GreenRobotEventBus.getInstance();
        this.eventBus = eventBus;
        this.view = view;
        // this.interactor = new UsersInteractorImpl();
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        this.eventBus.register(this);
        this.interactor.getData();
    }

    @Override
    public void onStart() {
        // this.interactor.subscribeListener();
    }

    @Override
    public void onStop() {
        // this.interactor.unSubscribeListener();
    }

    @Override
    public void onDestroy() {
        this.eventBus.unregister(this);
    }

    @Subscribe
    public void onEvent(UsersEvent event) {
        switch (event.getEventType()) {
            case UsersEvent.NOT_FOUND:
                onNotFound(event.getMessage());
                break;
            case UsersEvent.DATA_LOADED:
                onDataLoaded(event.getUsers());
                break;
        }
    }

    public void onNotFound(String message) {
        if (view != null) {
            view.onNotFound(message);
        }
    }

    public void onDataLoaded(List<User> users) {
        if (view != null) {
            view.onDataLoaded(users);
        }
    }
}
