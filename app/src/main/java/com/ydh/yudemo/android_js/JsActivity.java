package com.ydh.yudemo.android_js;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ydh.yudemo.BaseActivity;
import com.ydh.yudemo.R;

public class JsActivity extends BaseActivity {

    private static final String URL = "file:///android_asset/helloworld.html";
    private WebView mWebView;
    public String tag = "JsActivity";

    @Override
    public int getInflateId() {
        return R.layout.activity_js;
    }

    @Override
    public void initView() {
        mWebView = (WebView) this.findViewById(R.id.wv);
        mWebView.loadUrl(URL);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);// 设置支持javascript脚本

        // String ua = settings.getUserAgentString();
        // mWebView.getSettings().setUserAgentString(ua + " xungenandroid/" + VersionInfo.getAppVersionName(mContext));//便于WEB端统计分析
        //设置js接口  第一个参数事件接口实例，第二个是实例在js中的别名，这个在js中会用到
        mWebView.addJavascriptInterface(new JSHook(), "hello");
    }

    @Override
    public void addListener() {
        mWebView.setWebChromeClient(new WebChromeClient(){
            // 设置网页加载的进度条
            public void onProgressChanged(WebView view, int newProgress) {
            }

            // 获取网页的标题
            public void onReceivedTitle(WebView view, String title) {
            }

            // JavaScript弹出框
            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            // JavaScript输入框
            @Override
            public boolean onJsPrompt(WebView view, String url, String message,
                                      String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            // JavaScript确认框
            @Override
            public boolean onJsConfirm(WebView view, String url, String message,
                                       JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(tag, " url:" + url);
                view.loadUrl(url);// 当打开新链接时，使用当前的 WebView，不会使用系统其他浏览器
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
            }
        });
    }

    @Override
    public void addData() {

    }

    public class JSHook {
        @JavascriptInterface
        public void javaMethod(String p) {
            Log.e(tag, "JSHook.JavaMethod() called! + " + p);
        }

        @JavascriptInterface
        public void showAndroid() {
            final String info = "来自手机内的内容！！！";
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl("javascript:show('" + info + "')");
                }
            });
        }
        @JavascriptInterface
        public String getInfo() {
            return "获取手机内的信息！！";
        }
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); //goBack()表示返回WebView的上一页面
            return super.onKeyDown(keyCode, event);

        }
        //finish();
        return super.onKeyDown(keyCode, event);
    }
}
