package com.example.seanshi.testwebview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
        final WebView mWebView = (WebView) findViewById(R.id.webview);
//        final WebView mWebView = new WebView(this);
        mWebView.loadUrl("http://www.google.com");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                info("............ onPageFinished");
                super.onPageFinished(view, url);
                view.clearCache(true);

                getPicture(mWebView);
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
                Picture pic = mWebView.capturePicture();

                info("............ width ["+pic.getWidth()+"]  hieght ["+pic.getHeight()+"]");
                final Bitmap bm = Bitmap.createBitmap(pic.getWidth(), pic.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bm);
                canvas.drawPicture(pic);

                image.setImageBitmap(bm);
*/
            }
        });

    }

    private void getPicture(WebView mWebView)
    {
        Picture pic = mWebView.capturePicture();

        info("............ width ["+pic.getWidth()+"]  hieght ["+pic.getHeight()+"]");
        final Bitmap bm = Bitmap.createBitmap(pic.getWidth(), pic.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        canvas.drawPicture(pic);

        image.setImageBitmap(bm);
    }

    private void info(String info)
    {
        System.out.println(info);
        detail.append(info+"\n");
    }
}
