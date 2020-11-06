package co.com.ceiba.mobile.pruebadeingreso.libs.di;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.app.di.MyAppModule;
import dagger.Component;

@Singleton
@Component(modules = {LibsModule.class, MyAppModule.class})
public interface LibsComponent {
}
