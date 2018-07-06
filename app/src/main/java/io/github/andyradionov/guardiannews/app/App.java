package io.github.andyradionov.guardiannews.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.andyradionov.guardiannews.di.AppComponent;
import io.github.andyradionov.guardiannews.di.AppModule;
import io.github.andyradionov.guardiannews.di.DaggerAppComponent;
import io.github.andyradionov.guardiannews.model.network.NewsApi;
import io.github.andyradionov.guardiannews.model.network.NewsData;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Andrey Radionov
 */

public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    private AppComponent appComponent;


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule())
                .build();
    }

    /**
     * Check if Internet is Available on device
     *
     * @param context of application
     * @return internet status
     */
    public static boolean isInternetAvailable(Context context) {
        Log.d(TAG, "isInternetAvailable");
        ConnectivityManager mConMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return mConMgr.getActiveNetworkInfo() != null
                && mConMgr.getActiveNetworkInfo().isAvailable()
                && mConMgr.getActiveNetworkInfo().isConnected();
    }

    public static App getApp(Activity activity) {
        return (App) activity.getApplication();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
