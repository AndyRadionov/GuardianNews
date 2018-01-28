package io.github.andyradionov.guardiannews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.andyradionov.guardiannews.R;
import io.github.andyradionov.guardiannews.app.App;
import io.github.andyradionov.guardiannews.model.dto.Article;
import io.github.andyradionov.guardiannews.presenter.ArticlesPresenter;

public class ArticlesActivity extends AppCompatActivity implements ArticlesView,
        ArticlesAdapter.OnItemClickListener {

    private static final String TAG = ArticlesActivity.class.getSimpleName();

    private Unbinder unbinder;
    private ArticlesPresenter mArticlesPresenter;
    private ArticlesAdapter mArticlesAdapter;

    @BindView(R.id.recycler_news)
    RecyclerView mRecyclerNews;

    @BindView(R.id.et_search_box)
    EditText mSearchBox;

    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    @BindView(android.R.id.empty)
    TextView mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        unbinder = ButterKnife.bind(this);

        mArticlesPresenter = new ArticlesPresenter(this);

        setUpRecycler();
        setKeyListener();
        loadArticles(App.ALL_NEWS_QUERY);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void viewArticles(List<Article> articles) {
        Log.d(TAG, "viewArticles");

        setDataVisibility(View.VISIBLE, View.GONE, View.GONE);

        mArticlesAdapter.setData(articles);
        mRecyclerNews.scrollToPosition(0);
    }

    @Override
    public void viewError() {
        Log.d(TAG, "viewError");
        setDataVisibility(View.GONE, View.GONE, View.VISIBLE);
    }

    @Override
    public void onItemClick(String articleUrl) {
        Log.d(TAG, "onItemClick");

        Intent openWebPageIntent = new Intent(this, WebViewActivity.class);
        openWebPageIntent.putExtra(WebViewActivity.ARTICLE_URL_EXTRA, articleUrl);
        startActivity(openWebPageIntent);
    }

    public void onSearchClick(View view) {
        Log.d(TAG, "onSearchClick");

        hideKeyboard();

        String searchQuery = mSearchBox.getText().toString();
        loadArticles(searchQuery);
    }

    private void setUpRecycler() {
        mArticlesAdapter = new ArticlesAdapter(this);
        mRecyclerNews.setAdapter(mArticlesAdapter);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerNews.setLayoutManager(layoutManager);
    }

    private void setKeyListener() {
        mSearchBox.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                hideKeyboard();
                String searchQuery = mSearchBox.getText().toString();
                loadArticles(searchQuery);
                return true;
            }
            return false;
        });
    }

    private void loadArticles(String searchQuery) {
        Log.d(TAG, "findNewsArticles");

        if (App.isInternetAvailable(this)) {
            setDataVisibility(View.GONE, View.VISIBLE, View.GONE);
            mArticlesPresenter.findNewsArticles(searchQuery);
        } else {
            Toast.makeText(this, R.string.no_internet_connection_msg, Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void setDataVisibility(int recyclerVis, int progressBarVis, int emptyViewVis) {
        mRecyclerNews.setVisibility(recyclerVis);
        mLoadingIndicator.setVisibility(progressBarVis);
        mEmptyView.setVisibility(emptyViewVis);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}