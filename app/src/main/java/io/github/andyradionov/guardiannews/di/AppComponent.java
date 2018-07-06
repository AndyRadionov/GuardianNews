package io.github.andyradionov.guardiannews.di;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Component;
import io.github.andyradionov.guardiannews.ui.ArticlesActivity;

/**
 * @author Andrey Radionov
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(ArticlesActivity activity);
}
