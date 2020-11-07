package co.com.ceiba.mobile.pruebadeingreso.app.base;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDexApplication;

import co.com.ceiba.mobile.pruebadeingreso.app.di.MyAppModule;
import co.com.ceiba.mobile.pruebadeingreso.features.users.adapters.UsersAdapterListener;
import co.com.ceiba.mobile.pruebadeingreso.features.users.di.DaggerUsersComponent;
import co.com.ceiba.mobile.pruebadeingreso.features.users.di.UsersComponent;
import co.com.ceiba.mobile.pruebadeingreso.features.users.di.UsersModule;
import co.com.ceiba.mobile.pruebadeingreso.features.users.ui.UsersView;
import co.com.ceiba.mobile.pruebadeingreso.libs.di.LibsModule;
import co.com.ceiba.mobile.pruebadeingreso.rest.JSONPlaceholderEndpoint;
import co.com.ceiba.mobile.pruebadeingreso.rest.di.RestModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Aplicación de publicaciones de usuarios
 *
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class MyApp extends MultiDexApplication {

    private LibsModule libsModule;
    private MyAppModule myAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        initModules();
    }

    private void initRealm() {
        // SOLUCION TEMP PARA TEST CON REALM & JETPACK : (No hay solucion disponible para el bug)
        // Exception:
        // com.getkeepsafe.relinker.MissingLibraryException: Could not find 'librealm-jni.dylib'
        try {
            Realm.init(this);
            RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                    .name("gvoscar")
                    .schemaVersion(1)
                    .build();
            Realm.setDefaultConfiguration(realmConfiguration);
        } catch (Exception e) {
            Log.d("MyApp", e.getLocalizedMessage(), e.getCause());
        }
    }

    private void initModules() {
        libsModule = new LibsModule();
//        restModule = new RestModule(JSONPlaceholderEndpoint.URL_BASE);
        myAppModule = new MyAppModule(this);
    }

    public UsersComponent getUsersComponent(UsersView usersView, UsersAdapterListener usersAdapterListener) {
        return DaggerUsersComponent.builder()
                .myAppModule(myAppModule)
                .libsModule(libsModule)
                .usersModule(new UsersModule(usersView, usersAdapterListener))
                .build();
    }

}
