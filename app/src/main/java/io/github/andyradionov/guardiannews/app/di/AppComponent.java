package io.github.andyradionov.guardiannews.app.di;

import javax.inject.Singleton;

import dagger.Component;
import io.github.andyradionov.guardiannews.articles.ArticlesActivity;
import io.github.andyradionov.guardiannews.articles.ArticlesPresenter;

/**
 * @author Andrey Radionov
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    ArticlesPresenter articlesPresenter();

    void inject(ArticlesActivity activity);
}
