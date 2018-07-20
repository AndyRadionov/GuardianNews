package io.github.andyradionov.guardiannews.articles.di;

import javax.inject.Singleton;

import dagger.Component;
import io.github.andyradionov.guardiannews.app.di.AppComponent;
import io.github.andyradionov.guardiannews.app.di.AppModule;
import io.github.andyradionov.guardiannews.articles.ArticlesPresenterTest;

/**
 * @author Andrey Radionov
 */

@Singleton
@Component(modules = AppModule.class)
public interface TestComponent extends AppComponent {
    void inject(ArticlesPresenterTest test);
}
