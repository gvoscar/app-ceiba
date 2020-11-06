package co.com.ceiba.mobile.pruebadeingreso.rest.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestModule {
    String jsonPlaceholderURL;

    public RestModule(String jsonPlaceholderURL) {
        this.jsonPlaceholderURL = jsonPlaceholderURL;
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(jsonPlaceholderURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    String providesJSONPlaceholderURL() {
        return this.jsonPlaceholderURL;
    }
}
