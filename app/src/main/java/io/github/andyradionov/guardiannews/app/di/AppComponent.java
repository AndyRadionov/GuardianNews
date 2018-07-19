package io.github.andyradionov.guardiannews.app.di;

import javax.inject.Singleton;

import dagger.Component;
import io.github.andyradionov.guardiannews.data.network.NewsData;
import io.github.andyradionov.guardiannews.articles.ArticlesActivity;

/**
 * @author Andrey Radionov
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(ArticlesActivity activity);
}
