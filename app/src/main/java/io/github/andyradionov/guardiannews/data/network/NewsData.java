package io.github.andyradionov.guardiannews.data.network;

import android.util.Log;

import io.github.andyradionov.guardiannews.data.dto.GetArticlesResponseDto;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Get Articles from REST api
 *
 * @author Andrey Radionov
 */

public class NewsData {

    private static final String TAG = NewsData.class.getSimpleName();

    private NewsApi mNewsApi;
    private Disposable mSubscription;

    public NewsData(NewsApi newsApi) {
        Log.d(TAG, "NewsData constructor call");
        this.mNewsApi = newsApi;
    }

    public void fetchArticles(String searchQuery, NewsCallback newsCallback) {
        Log.d(TAG, "getArticles");
        unsubscribe();

        mSubscription = mNewsApi.searchNews(searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(GetArticlesResponseDto::getResults)
                .doOnError(throwable -> {
                    newsCallback.onErrorLoading();
                })
                .subscribe(articles -> {
                    if (articles.isEmpty()) {
                        newsCallback.onErrorLoading();
                    } else {
                        newsCallback.onSuccessLoading(articles);
                    }
                }, throwable -> newsCallback.onErrorLoading());
    }

    public void unsubscribe() {
        if (mSubscription != null && !mSubscription.isDisposed()) {
            mSubscription.dispose();
            mSubscription = null;
        }
    }
}
