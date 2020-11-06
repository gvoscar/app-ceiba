package co.com.ceiba.mobile.pruebadeingreso.app.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.app.base.MyApp;
import co.com.ceiba.mobile.pruebadeingreso.database.MyDatabase;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class MyAppModule {

    Application application;

    public MyAppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return application.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }
}
