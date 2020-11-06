package co.com.ceiba.mobile.pruebadeingreso.libs.di;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.libs.base.GlideImageLoader;
import co.com.ceiba.mobile.pruebadeingreso.libs.base.GreenRobotEventBus;
import co.com.ceiba.mobile.pruebadeingreso.libs.base.EventBus;
import co.com.ceiba.mobile.pruebadeingreso.libs.base.ImageLoader;
import dagger.Module;
import dagger.Provides;

@Module
public class LibsModule {

    //private AppCompatActivity activity;

    public LibsModule() {
    }

//    public void setAppCompatActivity(AppCompatActivity activity) {
//        this.activity = activity;
//    }

    @Provides
    @Singleton
    EventBus providesEventBus() {
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(Context context) {
        return new GlideImageLoader(context);
    }

//    @Provides
//    @Singleton
//    AppCompatActivity providesAppCompatActivity() {
//        return this.activity;
//    }
}
