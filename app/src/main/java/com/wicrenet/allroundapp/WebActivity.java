/*
 * Copyright 2015 Wicresoft, Inc. All rights reserved.
 */
package com.wicrenet.allroundapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jaydenxiao.common.base.BaseCoreActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * WebView界面，带自定义进度条显示
 *
 * @author bob.bop
 * @version $Id:WebActivity.java 上午10:49:27 bob.bop$
 * @created on 2015-8-1
 */
@SuppressLint("SetJavaScriptEnabled")
public class WebActivity extends BaseCoreActivity {
    private WebView mWebView;

//    private static final String DOMIN = Constant.DEBUG?Constant.BETA_DOMIN:Constant.NORMAL_DOMIN;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();

        // mWebView.loadUrl("file:///android_asset/index.html");
        // mWebView.loadUrl("http://172.17.1.175:8080/app/mobile/member/groupbuyappsite/getContent.page");

        webSettings.setJavaScriptEnabled(true);
        // 自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        // 启用数据库
        webSettings.setDatabaseEnabled(true);
        // 设置定位的数据库路径
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setGeolocationDatabasePath(dir);
        // 启用地理定位
        webSettings.setGeolocationEnabled(true);
        // 开启DomStorage缓存
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return !(url.startsWith("http:") || url.startsWith("https:"));
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress == 100) {
//                    web_progressbar.setVisibility(View.GONE);
//                } else {
//                    if (web_progressbar.getVisibility() == View.GONE)
//                        web_progressbar.setVisibility(View.VISIBLE);
//                    web_progressbar.setProgress(newProgress);
//                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {

                super.onReceivedIcon(view, icon);

            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {

                callback.invoke(origin, true, false);

                super.onGeolocationPermissionsShowPrompt(origin, callback);

            }
        });

        mWebView.loadDataWithBaseURL(null, getFromAssets("info-detail.html"), "text/html", "utf-8", null);

    }

    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void goBack() {
        if (mWebView.canGoBack()) {
            mWebView.goBack(); // goBack()表示返回WebView的上一页面
        } else if (!mWebView.canGoBack()) {
//            super.goBack();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView = null;
    }

    private class JavaScriptInterface {
        @JavascriptInterface
        public void showMobActivityDetail(String type, String id) {
        }

    }
}