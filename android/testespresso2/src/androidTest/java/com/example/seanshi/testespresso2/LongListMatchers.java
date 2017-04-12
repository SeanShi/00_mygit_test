package com.example.seanshi.testespresso2;

import android.support.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.equalTo;

public final class LongListMatchers {
    private LongListMatchers() { }
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