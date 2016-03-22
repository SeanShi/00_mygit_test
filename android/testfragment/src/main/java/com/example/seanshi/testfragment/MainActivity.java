package com.example.seanshi.testfragment;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.seanshi.testfragment.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements PlusOneFragment.OnFragmentInteractionListener, ItemFragment.OnListFragmentInteractionListener, BlankFragment.OnFragmentInteractionListener {

    TextView _text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _text = (TextView) findViewById(R.id.text);
    }

    public void onFragmentInteraction(Uri uri)
    {}


    public void onListFragmentInteraction(DummyContent.DummyItem item)
    {
        _text.setText(item.toString());
    }

    public void onBlankFragmentInteraction(String str)
    {
        _text.setText(str);
    }

}
