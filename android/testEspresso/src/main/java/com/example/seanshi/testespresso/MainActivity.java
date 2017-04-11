package com.example.seanshi.testespresso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        final EditText editText = (EditText)findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("hello");
            }
        });

        ListView listView = (ListView)findViewById(R.id.listView);

        GridView gridView = (GridView)findViewById(R.id.gridView);

        int length = 10;
        ArrayList<ProductDetails> items = new ArrayList();
        for(int i=0; i<length; i++){
            items.add(new ProductDetails("name"+i, "desc"+i));
        }
        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.list_item, items);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                editText.setText("" + position);
            }
        };
        listView.setOnItemClickListener(mMessageClickedHandler);
    }
}
