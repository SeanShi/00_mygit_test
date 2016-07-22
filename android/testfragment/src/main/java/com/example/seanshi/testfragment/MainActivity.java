package com.example.seanshi.testfragment;

import android.app.Fragment;
import android.content.ClipData;
import android.net.Uri;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.seanshi.testfragment.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements PlusOneFragment.OnFragmentInteractionListener, ItemFragment.OnListFragmentInteractionListener, BlankFragment.OnFragmentInteractionListener {

    TextView _text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _text = (TextView) findViewById(R.id.text);

        BlankFragment fragment = new BlankFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.container, fragment);
        ft.commit();
        _isBlank = true;

    }

    boolean _isBlank;
    public void ShowHide(View view) {
        Fragment fragment = _isBlank? new ItemFragment() : new BlankFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
        _isBlank = !_isBlank;
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
