package com.example.seanshi.testespresso;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.seanshi.testespresso.LongListMatchers.withItemContent;

/**
 * Created by Sean.Shi on 3/2/2017.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestViewTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Ignore
    @Test
    public void compareText()
    {
        onView(withId(R.id.testView)).check(matches(withText("Hello World!")));
    }

    @Ignore
    @Test
    public void compareTextWrong()
    {
        onView(withId(R.id.testView)).check(matches(withText("welcome")));
    }

    @Test
    public void testButton()
    {
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("hello")));

        Customer expectedCustomer = new Customer();
    }

    @Test
    public void testListView()
    {
        onView(withId(R.id.myListView)).perform(click());
        try{
            Thread.sleep(3000);
        }catch(Exception ex){

        }
//        onData(withItemContent("name1")).perform(click());
        onData(withItemContent("name3")).inAdapterView(withId(R.id.myListView)).perform(click());
        try{
            Thread.sleep(3000);
        }catch(Exception ex){

        }
        onView(withId(R.id.editText)).check(matches(withText("3")));
    }
}
