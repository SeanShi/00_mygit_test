package com.example.seanshi.testespresso2;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ListViewTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testListView()
    {
        // withItemContent() is a customized matcher
        onData(withItemContent("name1")).inAdapterView(withId(R.id.myListView)).perform(click());
        onView(withId(R.id.textView)).check(matches(withText("1")));
    }

    public static Matcher<Object> withItemContent(final String expectedText) {
        checkNotNull(expectedText);
        return withItemContent(equalTo(expectedText));
    }

    public static Matcher<Object> withItemContent(final Matcher<String> itemTextMatcher) {
        checkNotNull(itemTextMatcher);
        return new BoundedMatcher<Object, Person>(Person.class) {
            @Override
            public boolean matchesSafely(Person person) {
                return itemTextMatcher.matches(person._name);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with item content: "+itemTextMatcher.toString());
                itemTextMatcher.describeTo(description);
            }
        };
    }
}
