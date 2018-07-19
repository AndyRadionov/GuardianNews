package io.github.andyradionov.guardiannews.details;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.github.andyradionov.guardiannews.R;

public class DetailsWebViewActivity extends AppCompatActivity {

    private static final String TAG = DetailsWebViewActivity.class.getSimpleName();

    public static final String ARTICLE_URL_EXTRA = "article_url_extra";

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        Intent startIntent = getIntent();
        String siteUrl = startIntent.getStringExtra(ARTICLE_URL_EXTRA);

        setTitle(getString(R.string.web_view_title));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mWebView = new WebView(this);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(siteUrl);

        setContentView(mWebView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Log.d(TAG, "onOptionsItemSelected");

        if (android.R.id.home == menuItem.getItemId()) {
            finish();
            return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");

        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }
}
