package com.example.seanshi.testrecyclerview;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment1, BlankFragment.newInstance("aaa", "bbbb"));
        ft.commit();
    }
}
