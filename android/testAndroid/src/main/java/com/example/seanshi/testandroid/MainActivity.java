package com.example.seanshi.testandroid;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.textView);

        String content = "";
        content += format("3062168793") +"\n";
        content += format("(306)2168793") +"\n";
        content += format("(306) 216-8793") +"\n";
        textView.setText(content);
    }

    private String format(String phone){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return PhoneNumberUtils.formatNumber(phone, Locale.getDefault().getCountry());
        } else {
//Deprecated method
            return PhoneNumberUtils.formatNumber(phone);
        }
    }
}
