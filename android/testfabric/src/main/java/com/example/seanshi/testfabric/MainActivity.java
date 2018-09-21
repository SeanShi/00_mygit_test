package com.example.seanshi.testfabric;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.crashlytics.android.answers.LoginEvent;
import com.crashlytics.android.answers.PurchaseEvent;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

import de.mindpipe.android.logging.log4j.LogConfigurator;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    String _time;
    Calendar _c;
    TextView _text;

    private Logger logger = Logger.getLogger(this.getClass());

    static {
        final LogConfigurator logConfigurator = new LogConfigurator();

        File root = Environment.getExternalStorageDirectory();
        System.out.println(".........["+root+"]");

        // We need to enable "storage" permission on tablet with Android 23+
        // settings-->apps-->select app-->permission-->enable "storage"
        String FileName = root.getAbsolutePath() + "/testFabric.log";

        logConfigurator.setFileName(FileName);
        logConfigurator.setRootLevel(Level.DEBUG);

        // Set log level of a specific logger
        logConfigurator.setLevel("com.iqmetrix.pos", Level.DEBUG);
        logConfigurator.setFilePattern("%d{dd MMM yyyy HH:mm:ss,SSS} -- [%t] %c %x %M - %m (ln:%L)%n");

        // Be careful to change these two parameters (5 and 2M). We send all 6 log files in a screenshot email. The attachment limit is around 3.3M.
        // A zipped file contains 6 logs is around 2.3M.
        logConfigurator.setMaxBackupSize(5);
        logConfigurator.setMaxFileSize(2 * 1024 * 1024);

        logConfigurator.configure();

        Logger loggerRoot = Logger.getRootLogger();
        loggerRoot.addAppender(new CrashlyticsAppender());
    }

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

        logger.info("this is log4j info");
        logger.warn("this is log4j warning");
        logger.error("this is log4j error in crashlytic but not on logcat");

/*
        Crashlytics.log(Log.ERROR, "tag", "..................this is Error (int, tag, str)");
        Crashlytics.log(Log.WARN, "tag1", "..................this is Warn (int, tag, str)");
        Crashlytics.log(".................this is (str)");
        Crashlytics.log(Log.WARN, "tag2", "..................this is Warn2 (int, tag, str)");
*/

        Crashlytics.setInt("current_level", 6);
        Crashlytics.setString("last_UI_action", "logged_in");

        try{
            throw new Exception("test");
        }catch(Exception ex){
            logger.error("get an exception", ex);
        }

//        testException();

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

        for(int i=0;i<1000;i++){
            logger.error("..................................................................................................................this is log4j error in crashlytic but not on logcat "+i);
        }

    }

    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("12345");
        Crashlytics.setUserEmail("seans1@iqmetrix.com");
        Crashlytics.setUserName("Test User1");
    }

    private void testException()
    {
        /*
        try{
            throw new NullPointerException();
        }catch(Exception e){
            Crashlytics.logException(e);
        }
        */
        testException2();
    }

    private void testException2()
    {
        throw new RuntimeException("this is a runtime error " + new Date().toString());
    }
}
