package io.github.andyradionov.guardiannews.ui;

import java.util.List;

import io.github.andyradionov.guardiannews.model.dto.Article;

/**
 * Articles View
 *
 * @author Andrey Radionov
 */

public interface ArticlesView {

    void viewArticles(List<Article> articles);

    void viewError();

}
