package co.com.ceiba.mobile.pruebadeingreso.features.loader.presenters;


import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import co.com.ceiba.mobile.pruebadeingreso.features.loader.events.LoaderEvent;
import co.com.ceiba.mobile.pruebadeingreso.features.loader.interactors.LoaderInteractor;
import co.com.ceiba.mobile.pruebadeingreso.features.loader.interactors.LoaderInteractorImpl;
import co.com.ceiba.mobile.pruebadeingreso.features.loader.ui.LoaderView;
import co.com.ceiba.mobile.pruebadeingreso.libs.base.EventBus;
import co.com.ceiba.mobile.pruebadeingreso.libs.base.GreenRobotEventBus;

public class LoaderPresenterImpl implements LoaderPresenter {

    private static final String TAG = LoaderPresenterImpl.class.getSimpleName();
    private EventBus mBus;
    private LoaderView mView;

    private LoaderInteractor mInteractor;

    public LoaderPresenterImpl(LoaderView view) {
        this.mBus = GreenRobotEventBus.getInstance();
        this.mView = view;
        this.mInteractor = new LoaderInteractorImpl();
    }

    @Override
    public void onCreate() {
        this.mBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.mBus.unregister(this);
    }

    @Override
    public void checkDataRealm() {
        mInteractor.checkDataRealm();
    }

    @Subscribe
    public void onEvent(LoaderEvent event) {
        switch (event.getEventType()) {
            case LoaderEvent.DOWNLOADING:
                onDownloading(event.getMessage());
                break;
            case LoaderEvent.LOADED:
                onLoaded();
                break;
        }
    }

    public void onDownloading(String message) {
        Log.d(TAG, "DESCARGANDO DATOS");
        if (mView != null) {
            if (message != null) {
                mView.onDownloading(message);
            }
        }
    }

    public void onLoaded() {
        Log.d(TAG, "DATOS CARGADOS");
        if (mView != null) {
            mView.onLoaded();
        }
    }
}
