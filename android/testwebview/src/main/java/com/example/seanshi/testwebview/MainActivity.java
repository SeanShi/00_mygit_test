package com.example.seanshi.testwebview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView image;
    TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.imageView1);
        image.setImageResource(R.drawable.capture);

        button = (Button) findViewById(R.id.btnChangeImage);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                generateImage();
            }
        });

        Button btnReset = (Button) findViewById(R.id.btnResetImage);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                image.setImageResource(R.drawable.capture);
            }
        });

        detail = (TextView) findViewById(R.id.detail);

        generateImage();
    }

    public void generateImage()
    {
        final AtomicBoolean ready = new AtomicBoolean(false);

//        final WebView mWebView = (WebView) findViewById(R.id.webview);
//        mWebView.loadUrl("http://www.google.com");



        final WebView mWebView = new WebView(this);
        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        mWebView.setInitialScale(150);
        mWebView.getSettings().setLoadWithOverviewMode(true);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setDrawingCacheEnabled(true);
//        mWebView.setWebViewClient(new WebViewClient(){});
        mWebView.addJavascriptInterface(new JavaScriptInterface(mWebView, image), "Android");

        String html = "<html><body>hello world<p>bbb</p></body></html>";
//        String html = "<html><body onload=\"Android.captureImage()\">hello world<p>bbb</p></body></html>";
        mWebView.loadDataWithBaseURL("", html, "text/html", "utf-8", "");

//        mWebView.loadData("", "text/html", null);
//        mWebView.loadUrl("javascript:alert(Android.toString())");


        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                System.out.println("............ onPageFinished");
                super.onPageFinished(view, url);
                view.clearCache(true);
//                ready.set(true);
//                getPicture(mWebView);
            }
        });
        mWebView.setPictureListener(new WebView.PictureListener() {
            @Override
            public void onNewPicture(WebView view, Picture picture)
            {


                info("............ onNewPicture()  pic ["+picture+"]");
                if (picture == null) {
                    return;
                }




/*
                if (!ready.get()) {
                    return;
                }
                ready.set(false);
*/



                Picture pic = mWebView.capturePicture();

                if(pic==null){
                    System.out.println("............ pic is null");
                    return;
                }
                System.out.println("onNewPicture............ width ["+pic.getWidth()+"]  hieght ["+pic.getHeight()+"]");
                final Bitmap bm = Bitmap.createBitmap(pic.getWidth(), pic.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bm);
                canvas.drawPicture(pic);

                image.setImageBitmap(bm);

            }
        });


    }

    private void getPicture(WebView mWebView)
    {
        Picture pic = mWebView.capturePicture();

        System.out.println("............ width ["+pic.getWidth()+"]  height ["+pic.getHeight()+"]");
        if(pic.getHeight()==0 || pic.getWidth()==0){
            return;
        }

        final Bitmap bm = Bitmap.createBitmap(pic.getWidth(), pic.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        canvas.drawPicture(pic);

        image.setImageBitmap(bm);
    }

    private void info(String info)
    {
        System.out.println(info);
//        detail.append(info+"\n");
    }

    public class JavaScriptInterface {
        private WebView mWebView;
        private ImageView mImageView;

        public JavaScriptInterface(WebView webView, ImageView imageView) {
            mWebView = webView;
            mImageView = imageView;
        }

        @JavascriptInterface
        public void captureImage()
        {
            System.out.println("... captureImage");

            Bitmap b = mWebView.getDrawingCache(true);
            if(b==null){
                System.out.println("... b is null");
                return;
            }
            System.out.println("... height "+b.getHeight()+"    width "+b.getWidth()+"    bytes "+b.getByteCount());
         //   mImageView.setImageBitmap(b);
        }

        @JavascriptInterface
        public String toString()
        {
            info("... toString");
            return "injectedObject";
        }
    }
}
