package io.github.andyradionov.guardiannews.data.network;

import java.util.List;

import io.github.andyradionov.guardiannews.data.dto.Article;

/**
 * @author Andrey Radionov
 */

public interface NewsCallback {

    void onSuccessLoading(List<Article> articles);

    void onErrorLoading();
}
