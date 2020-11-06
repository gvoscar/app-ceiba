package co.com.ceiba.mobile.pruebadeingreso.rest.di;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.app.di.MyAppModule;
import co.com.ceiba.mobile.pruebadeingreso.rest.JSONPlaceholderEndpoint;
import dagger.Component;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Component(modules = {RestModule.class, MyAppModule.class})
public interface RestComponent {
}
