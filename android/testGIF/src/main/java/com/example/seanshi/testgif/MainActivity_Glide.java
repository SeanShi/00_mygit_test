package com.example.seanshi.testgif;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity_Glide extends AppCompatActivity {

    // There is memory leak with movie class.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_glide);

        ImageView imageView = (ImageView) findViewById(R.id.my_image_view);
        imageView.setImageResource(R.drawable.ic_launcher);
        Glide.with(this).load(R.drawable.spinner).asGif().into(imageView);
    }
}
