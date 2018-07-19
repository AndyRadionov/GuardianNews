package io.github.andyradionov.guardiannews.articles;

import android.util.Log;

import java.util.List;

import io.github.andyradionov.guardiannews.data.dto.Article;
import io.github.andyradionov.guardiannews.data.network.NewsCallback;
import io.github.andyradionov.guardiannews.data.network.NewsData;
import io.reactivex.disposables.Disposable;

/**
 * Articles Presenter
 *
 * @author Andrey Radionov
 */

public class ArticlesPresenter implements ArticlesContract.Presenter, NewsCallback {

    private static final String TAG = ArticlesPresenter.class.getSimpleName();

    private NewsData newsData;
    private ArticlesContract.View mArticlesView;

    public ArticlesPresenter(NewsData newsData) {
        Log.d(TAG, "ArticlesPresenter constructor call");

        this.newsData = newsData;
    }


    @Override
    public void findNewsArticles(String searchQuery) {
        Log.d(TAG, "findNewsArticles");
        newsData.fetchArticles(searchQuery, this);
    }

    @Override
    public void onSuccessLoading(List<Article> articles) {
        mArticlesView.viewArticles(articles);
    }

    @Override
    public void onErrorLoading() {
        mArticlesView.viewError();
    }

    @Override
    public void attachView(ArticlesContract.View view) {
        mArticlesView = view;
    }

    @Override
    public void detachView(ArticlesContract.View view) {
        newsData.unsubscribe();
        mArticlesView = null;
    }
}
