package io.github.andyradionov.guardiannews.articles;

import java.util.List;

import io.github.andyradionov.guardiannews.data.dto.Article;

/**
 * Articles View
 *
 * @author Andrey Radionov
 */

public interface ArticlesContract {

    interface Presenter {
        void findNewsArticles(String searchQuery);

        void attachView(View view);

        void detachView(View view);
    }

    interface View {
        void viewArticles(List<Article> articles);

        void viewError();
    }
}
