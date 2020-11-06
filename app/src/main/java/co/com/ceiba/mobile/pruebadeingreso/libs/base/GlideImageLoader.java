package co.com.ceiba.mobile.pruebadeingreso.libs.base;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

public class GlideImageLoader implements ImageLoader {
    private RequestManager requestManager;

    public GlideImageLoader(Context context) {
        if (context != null) {
            this.requestManager = Glide.with(context);
        }
    }

    public GlideImageLoader(AppCompatActivity activity) {
        if (activity != null) {
            this.requestManager = Glide.with(activity);
        }
    }

    public GlideImageLoader(Fragment fragment) {
        if (fragment != null) {
            this.requestManager = Glide.with(fragment);
        }
    }

    @Override
    public void load(ImageView imageView, String URL) {
        this.requestManager
                .load(URL)
                .into(imageView);
    }
}
