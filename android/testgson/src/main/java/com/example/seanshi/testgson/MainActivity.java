package com.example.seanshi.testgson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView output = (TextView) findViewById(R.id.output);

        Gson gson = new Gson();

        String str = "";

        MyClass target = gson.fromJson("{'name1':'v1'}", MyClass.class);
        str+="v1     "+ target.b +"\n";
        target = gson.fromJson("{'name2':'v2'}", MyClass.class);
        str+="v2     "+ target.b +"\n";
        target = gson.fromJson("{'name3':'v3'}", MyClass.class);
        str+="v3     "+target.b +"\n";


        MyItem item = gson.fromJson("{\"Items\":\"Items,,,,\"}", MyItem.class);
        str += "Item   "+item.Items +"\n";
        output.setText(str);

        item = gson.fromJson("{\"items\":\"items,,,,\"}", MyItem.class);
        str += "item   "+item.Items +"\n";

        output.setText( str);

    }

    public class MyClass {
        @SerializedName("name") String a;
        @SerializedName(value="name1", alternate={"name2", "name3"}) String b;
        String c;

        public MyClass(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public class MyItem {
        @SerializedName("name") String a;
//        @SerializedName(value="Items", alternate={"items"}) String Items;
        @SerializedName(value="Items", alternate={"items"}) String Items;
        String c;

        public MyItem(String a, String b, String c) {
            this.a = a;
            this.c = c;
        }
    }

}
