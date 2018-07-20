package io.github.andyradionov.guardiannews.articles;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.andyradionov.guardiannews.articles.di.DaggerTestComponent;
import io.github.andyradionov.guardiannews.articles.di.TestComponent;
import io.github.andyradionov.guardiannews.articles.di.TestModule;
import io.github.andyradionov.guardiannews.data.dto.Article;
import io.github.andyradionov.guardiannews.data.network.NewsData;

/**
 * @author Andrey Radionov
 */
public class ArticlesPresenterTest {

    private static final String CORRECT_QUERY = "correct_query";
    private static final String INCORRECT_QUERY = "incorrect_query";
    private List<Article> correctAnswer = new ArrayList<>();

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock ArticlesContract.View viewMock;
    @Inject NewsData newsData;
    @Inject ArticlesPresenter presenter;

    @Before
    public void setup() {
        TestComponent component = DaggerTestComponent.builder()
                .appModule(new TestModule()).build();
        component.inject(this);
        presenter.attachView(viewMock);
        correctAnswer.add(new Article());

        Mockito.doAnswer(invocation -> {
            presenter.onSuccessLoading(correctAnswer);
            return null;
        }).when(newsData).fetchArticles(CORRECT_QUERY, presenter);

        Mockito.doAnswer(invocation -> {
            presenter.onErrorLoading();
            return null;
        }).when(newsData).fetchArticles(INCORRECT_QUERY, presenter);
    }

    @Test
    public void testFindNewsArticles() throws Exception {
        presenter.findNewsArticles(CORRECT_QUERY);

        Mockito.verify(viewMock, Mockito.times(1)).viewArticles(correctAnswer);
    }

    @Test
    public void testErrorFindNewsArticles() throws Exception {
        presenter.findNewsArticles(INCORRECT_QUERY);

        Mockito.verify(viewMock, Mockito.times(1)).viewError();
    }

}