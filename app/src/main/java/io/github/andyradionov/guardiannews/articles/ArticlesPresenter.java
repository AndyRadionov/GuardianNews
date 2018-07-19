package io.github.andyradionov.guardiannews.articles;

import android.util.Log;

import io.github.andyradionov.guardiannews.data.network.NewsData;
import io.reactivex.disposables.Disposable;

/**
 * Articles Presenter
 *
 * @author Andrey Radionov
 */

public class ArticlesPresenter implements ArticlesContract.Presenter {

    private static final String TAG = ArticlesPresenter.class.getSimpleName();

    private NewsData newsData;
    private ArticlesContract.View mArticlesView;
    private Disposable mSubscription;

    public ArticlesPresenter(NewsData newsData) {
        Log.d(TAG, "ArticlesPresenter constructor call");

        this.newsData = newsData;
    }


    @Override
    public void findNewsArticles(String searchQuery) {
        Log.d(TAG, "findNewsArticles");
        checkSubscription();
        mSubscription = newsData.getArticles(searchQuery)
                .doOnError(throwable -> {
                    mArticlesView.viewError();
                })
                .subscribe(articles -> {
                    if (articles.isEmpty()) {
                        mArticlesView.viewError();
                    } else {
                        mArticlesView.viewArticles(articles);
                    }
                }, throwable -> mArticlesView.viewError());
    }

    @Override
    public void attachView(ArticlesContract.View view) {
        mArticlesView = view;
    }

    @Override
    public void detachView(ArticlesContract.View view) {
        checkSubscription();
        mArticlesView = null;
    }

    private void checkSubscription() {
        if(mSubscription != null && !mSubscription.isDisposed()){
            mSubscription.dispose();
            mSubscription = null;
        }
    }
}
