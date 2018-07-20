package io.github.andyradionov.guardiannews.articles.di;

import android.support.annotation.NonNull;

import org.mockito.Mockito;

import javax.inject.Singleton;

import io.github.andyradionov.guardiannews.app.di.AppModule;
import io.github.andyradionov.guardiannews.data.network.NewsData;

/**
 * @author Andrey Radionov
 */
public class TestModule extends AppModule {
    @NonNull
    @Singleton
    @Override
    public NewsData provideNewsData() {
        return Mockito.mock(NewsData.class);
    }
}
