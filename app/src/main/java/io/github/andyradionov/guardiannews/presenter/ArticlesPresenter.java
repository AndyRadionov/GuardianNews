package io.github.andyradionov.guardiannews.presenter;

import android.util.Log;

import io.github.andyradionov.guardiannews.app.App;
import io.github.andyradionov.guardiannews.model.network.NewsApi;
import io.github.andyradionov.guardiannews.model.network.NewsData;
import io.github.andyradionov.guardiannews.ui.ArticlesView;

/**
 * Articles Presenter
 *
 * @author Andrey Radionov
 */

public class ArticlesPresenter {

    private static final String TAG = ArticlesPresenter.class.getSimpleName();

    private NewsData newsData;

    public ArticlesPresenter(NewsData newsData) {
        Log.d(TAG, "ArticlesPresenter constructor call");

        this.newsData = newsData;
    }

    public void findNewsArticles(String searchQuery, ArticlesView articlesView) {
        Log.d(TAG, "findNewsArticles");

        newsData.getArticles(searchQuery)
                .doOnError(throwable -> {
                    articlesView.viewError();
                })
                .subscribe(articles -> {
                    if (articles.isEmpty()) {
                        articlesView.viewError();
                    } else {
                        articlesView.viewArticles(articles);
                    }
                }, throwable -> articlesView.viewError());
    }
}
