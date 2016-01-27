package com.example.seanshi.android;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class TestWebViewActivity extends AppCompatActivity
{

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web_view);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //   _text.setText("button is clicked");
                refresh();
            }
        });

        refresh();
    }

    void refresh()
    {
        WebView mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        mWebView.setInitialScale(150);
        mWebView.getSettings().setLoadWithOverviewMode(true);

        System.out.println("............... isCached 1 [" +mWebView.getSettings().getCacheMode() + "]");
        mWebView.getSettings().setAppCacheEnabled(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        System.out.println("............... isCached 2 [" +mWebView.getSettings().getCacheMode() + "]");

        mWebView.clearCache(true);


        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.clearCache(true);
                System.out.println("cache is cleard...");
            }
        });

        String image = Environment.getExternalStorageDirectory() + "/receipt.png";

        File file = new File(image);
        System.out.println(".......... ["+image+"] length ["+ file.length()+"] readable["+file.canRead()+"]");
        image = "file://"+image;

        System.out.println(".......... ["+image+"]");

        String html = "<html><body>hello, world 11       <table style=\"Width:400\">\n" +
                "            <tr>\n" +
                "                <td align=\"center\">\n" +
                "                    <img src=\""+image+"\" alt=\""+image+"\" style=\"width: 400; max-width: 400; display:block\"/>\n" +
                "                </td>\n" +
                "            </tr></table>\n</body></html>";

        mWebView.loadDataWithBaseURL("", html, "text/html", "utf-8", "");

    }
}
