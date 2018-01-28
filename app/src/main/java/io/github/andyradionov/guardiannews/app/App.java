package io.github.andyradionov.guardiannews.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.github.andyradionov.guardiannews.model.network.NewsApi;
import io.github.andyradionov.guardiannews.model.network.NewsData;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Andrey Radionov
 */

public class App extends Application {

    public static final String NEWS_BASE_URL = "http://content.guardianapis.com";
    public static final String SEARCH_NEWS_QUERY = "search?api-key=test&page-size=30&order-by=newest&show-fields=thumbnail,trailText";
    public static final String ALL_NEWS_QUERY = "";

    private static final String TAG = App.class.getSimpleName();

    private static NewsApi newsApi;
    private static NewsData newsData;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();

        newsApi = createApi();
        newsData = new NewsData();
    }

    public static NewsApi getNewsApi() {
        return newsApi;
    }

    public static NewsData getNewsData() {
        return newsData;
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

    private static NewsApi createApi() {
        Log.d(TAG, "createApi");

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create();

        return new Retrofit.Builder()
                .baseUrl(NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NewsApi.class);
    }
}
