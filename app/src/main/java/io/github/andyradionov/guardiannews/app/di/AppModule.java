package io.github.andyradionov.guardiannews.app.di;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.andyradionov.guardiannews.app.AppPreferences;
import io.github.andyradionov.guardiannews.model.network.NewsApi;
import io.github.andyradionov.guardiannews.model.network.NewsData;
import io.github.andyradionov.guardiannews.presenter.ArticlesPresenter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Andrey Radionov
 */


@Module
public class AppModule {
    private static final String TAG = AppModule.class.getSimpleName();

    @Provides
    @NonNull
    @Singleton
    public NewsData provideNewsData() {
        return new NewsData(createApi());
    }

    @Provides
    @NonNull
    @Singleton
    public ArticlesPresenter provideArticlesPresenter(NewsData newsData) {;
        return new ArticlesPresenter(newsData);
    }

    private static NewsApi createApi() {
        Log.d(TAG, "createApi");

        Gson gson = new GsonBuilder()
                .setDateFormat(AppPreferences.QUERY_DATE_FORMAT)
                .create();

        return new Retrofit.Builder()
                .baseUrl(AppPreferences.NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NewsApi.class);
    }
}

