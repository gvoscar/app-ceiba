package co.com.ceiba.mobile.pruebadeingreso.features.users.di;


import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.app.di.MyAppModule;
import co.com.ceiba.mobile.pruebadeingreso.features.users.ui.UsersActivity;
import co.com.ceiba.mobile.pruebadeingreso.libs.di.LibsModule;
import dagger.Component;

@Singleton
@Component(modules = {UsersModule.class, LibsModule.class, MyAppModule.class})
public interface UsersComponent {
    void inject(UsersActivity activity);
}
