package com.example.seanshi.testfabric;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.crashlytics.android.answers.LoginEvent;
import com.crashlytics.android.answers.PurchaseEvent;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.Crash;

public class MainActivity extends AppCompatActivity {

    String _time;
    Calendar _c;
    TextView _text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        _text = (TextView) findViewById(R.id.text);

        _c = Calendar.getInstance();
        _time = _c.getTime().toString();
        _text.setText(_time);

        // TODO: Move this to where you establish a user session
        logUser();

        Crashlytics.log(Log.ERROR, "tag", "..................this is Error (int, tag, str)");
        Crashlytics.log(Log.WARN, "tag1", "..................this is Warn (int, tag, str)");
        Crashlytics.log(".................this is (str)");

        Crashlytics.setInt("current_level", 3);
        Crashlytics.setString("last_UI_action", "logged_in");

        testException();

        _text.setText("MODEL: "+android.os.Build.MODEL
                +"\nDEVICE: "+android.os.Build.DEVICE
                +"\nBRAND: "+android.os.Build.BRAND
                +"\nDISPLAY: "+android.os.Build.DISPLAY
                +"\nBOARD: "+android.os.Build.BOARD
                +"\nHOST: "+android.os.Build.HOST
                +"\nMANUFACTURER: "+android.os.Build.MANUFACTURER
                +"\nPRODUCT: "+android.os.Build.PRODUCT
                +"\nID: "+android.os.Build.ID
                +"\nUser: "+ android.os.Build.USER);

        _text.append("\n\n phone name ["+getPhoneName()+"]");

        append("......USER ["+ Build.USER+"]");
        append("......BOARD ["+ Build.BOARD+"]");
        append("......BOOTLOADER ["+ Build.BOOTLOADER+"]");
        append("......BRAND ["+ Build.BRAND+"]");
        append("......DEVICE ["+ Build.DEVICE+"]");
        append("......DISPLAY ["+ Build.DISPLAY+"]");
        append("......FINGERPRINT ["+ Build.FINGERPRINT+"]");
        append("......HARDWARE ["+ Build.HARDWARE+"]");
        append("......HOST ["+ Build.HOST+"]");
        append("......ID ["+ Build.ID+"]");
        append("......MANUFACTURER ["+ Build.MANUFACTURER+"]");
        append("......MODEL ["+ Build.MODEL+"]");
        append("......PRODUCT ["+ Build.PRODUCT+"]");
        append("......SERIAL ["+ Build.SERIAL+"]");
        append("......TAGS ["+ Build.TAGS+"]");
        append("......TYPE ["+ Build.TYPE+"]");
        append("......UNKNOWN ["+ Build.UNKNOWN+"]");
        append("......USER ["+ Build.USER+"]");
        append("......TIME ["+ Build.TIME+"]");
    }

    private void append(String s)
    {
        _text.append("\n"+s);
    }

    public String getPhoneName()
    {
        BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();
        String deviceName = myDevice.getName();
        return deviceName;
    }

    public void forceCrash(View view) {
        System.out.println(_time);
        throw new RuntimeException("This is a crash "+ _time);
    }

    public void testAnswer(View view) {
        System.out.println(_time);

        Answers.getInstance().logPurchase(new PurchaseEvent()
                .putItemPrice(BigDecimal.valueOf(13.50))
                .putCurrency(Currency.getInstance("USD"))
                .putItemName("Answers Shirt")
                .putItemType("Apparel")
                .putItemId("sku-350")
                .putSuccess(true));

        Answers.getInstance().logLogin(new LoginEvent().putMethod("login").putSuccess(true).putCustomAttribute("name", "sean.shi"));

        System.out.println("............ customer event");

        Answers.getInstance().logCustom(new CustomEvent("Custom Event").putCustomAttribute("custom key", _time));
    }

    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("12345");
        Crashlytics.setUserEmail("seans@iqmetrix.com");
        Crashlytics.setUserName("Test User");
    }

    private void testException()
    {
        try{
            throw new NullPointerException();
        }catch(Exception e){
            Crashlytics.logException(e);
        }
    }
}
