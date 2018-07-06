package io.github.andyradionov.guardiannews.model.network;

import android.util.Log;

import java.util.List;

import io.github.andyradionov.guardiannews.app.App;
import io.github.andyradionov.guardiannews.model.dto.Article;
import io.github.andyradionov.guardiannews.model.dto.GetArticlesResponseDto;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Get Articles from REST api
 *
 * @author Andrey Radionov
 */

public class NewsData {

    private static final String TAG = NewsData.class.getSimpleName();

    private NewsApi newsApi;

    public NewsData(NewsApi newsApi) {
        Log.d(TAG, "NewsData constructor call");
        this.newsApi = newsApi;
    }

    public Observable<List<Article>> getArticles(String searchQuery) {
        Log.d(TAG, "getArticles");
        return newsApi.searchNews(searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(GetArticlesResponseDto::getResults);
    }
}
