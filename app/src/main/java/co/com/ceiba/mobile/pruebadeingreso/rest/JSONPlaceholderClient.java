package co.com.ceiba.mobile.pruebadeingreso.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class JSONPlaceholderClient {

    private Retrofit retrofit;

    public JSONPlaceholderClient() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(JSONPlaceholderEndpoint.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public JSONPlaceholderClient(Retrofit retrofit) {
        this.retrofit = retrofit;
//        this.retrofit = new Retrofit.Builder()
//                .baseUrl(JSONPlaceholderEndpoint.URL_BASE)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
    }

    public JSONPlaceholderService getJsonPlaceholderService() {
        return this.retrofit.create(JSONPlaceholderService.class);
    }
}
