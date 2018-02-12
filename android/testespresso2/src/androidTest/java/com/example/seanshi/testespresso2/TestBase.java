package com.example.seanshi.testespresso2;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;

/**
 * Created by Sean.Shi on 4/28/2017.
 */

public class TestBase {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    /*
    private Activity splashActivity;
    @Before
    public void setActivity() {
        splashActivity = mActivityRule.getActivity();
    }

    public Activity getActivity(){
        return splashActivity;
    }
    */

    @BeforeClass
    public static void before(){}

    @AfterClass
    public static void after(){}

}
