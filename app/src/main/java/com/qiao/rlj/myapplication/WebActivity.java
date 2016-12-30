package com.qiao.rlj.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 浏览网页
 */
public class WebActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.progress_layout)
    RelativeLayout rl;
    @BindView(R.id.toolbar_title)
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        String url = getIntent().getStringExtra("url");
        String author = getIntent().getStringExtra("author");
        title.setText(author);
        webView.loadUrl(url);
        webView.setWebViewClient(webViewClient);
    }

    @OnClick(R.id.toolbar_back)
    public void OnToolBarBackClick(){
        finish();
    }

    private WebViewClient webViewClient = new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            rl.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            rl.setVisibility(View.GONE);
        }

    };
}
