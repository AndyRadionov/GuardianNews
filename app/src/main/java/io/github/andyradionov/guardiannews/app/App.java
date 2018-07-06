package io.github.andyradionov.guardiannews.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import io.github.andyradionov.guardiannews.app.di.AppComponent;
import io.github.andyradionov.guardiannews.app.di.AppModule;
import io.github.andyradionov.guardiannews.app.di.DaggerAppComponent;

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
