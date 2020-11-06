package co.com.ceiba.mobile.pruebadeingreso.app;

import androidx.multidex.MultiDexApplication;

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

    @Override
    public void onCreate() {
        super.onCreate();

        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("gvoscar")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
