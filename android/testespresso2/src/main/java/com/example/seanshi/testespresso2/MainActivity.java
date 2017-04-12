package com.example.seanshi.testespresso2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView)findViewById(R.id.textView);
        ListView listView = (ListView)findViewById(R.id.myListView);

        ArrayList<Person> items = new ArrayList();
        for(int i=0; i<10; i++){
            items.add(new Person("name"+i, "desc"+i));
        }
        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.list_item, items);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                textView.setText("" + position);
            }
        };
        listView.setOnItemClickListener(mMessageClickedHandler);
    }
}
