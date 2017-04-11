package com.example.seanshi.testespresso;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkArgument;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import android.support.test.espresso.matcher.BoundedMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import java.util.Map;

public final class LongListMatchers {
    private LongListMatchers() { }
    /**
     * Creates a matcher against the text stored in R.id.item_content. This text is roughly
     * "item: $row_number".
     */
    public static Matcher<Object> withItemContent(final String expectedText) {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        checkNotNull(expectedText);

        return new BoundedMatcher<Object, ProductDetails>(ProductDetails.class) {
            @Override
            public boolean matchesSafely(ProductDetails product) {
                System.out.println("name ["+product._name);
                return product._name.equals(expectedText);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with item content: ");
//                itemTextMatcher.describeTo(description);
            }
        };

//        return withItemContent(equalTo(expectedText));
    }
    /**
     * Creates a matcher against the text stored in R.id.item_content. This text is roughly
     * "item: $row_number".
     */
    @SuppressWarnings("rawtypes")
    public static Matcher<Object> withItemContent(final Matcher<String> itemTextMatcher) {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        checkNotNull(itemTextMatcher);
        return new BoundedMatcher<Object, ProductDetails>(ProductDetails.class) {
            @Override
            public boolean matchesSafely(ProductDetails product) {
                return hasEntry(equalTo("STR"), itemTextMatcher).matches(product);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with item content: ");
                itemTextMatcher.describeTo(description);
            }
        };
    }
    /**
     * Creates a matcher against the text stored in R.id.item_size. This text is the size of the text
     * printed in R.id.item_content.
     */
    public static Matcher<Object> withItemSize(int itemSize) {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        checkArgument(itemSize > -1);
        return withItemSize(equalTo(itemSize));
    }
    /**
     * Creates a matcher against the text stored in R.id.item_size. This text is the size of the text
     * printed in R.id.item_content.
     */
    @SuppressWarnings("rawtypes")
    public static Matcher<Object> withItemSize(final Matcher<Integer> itemSizeMatcher) {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        checkNotNull(itemSizeMatcher);
        return new BoundedMatcher<Object, Map>(Map.class) {
            @Override
            public boolean matchesSafely(Map map) {
                return hasEntry(equalTo("LEN"), itemSizeMatcher).matches(map);
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("with item size: ");
                itemSizeMatcher.describeTo(description);
            }
        };
    }


    /**
     * Creates a matcher against the footer of this list view.
     */
    @SuppressWarnings("unchecked")
    public static Matcher<? extends Object> isFooter() {
        // This depends on LongListActivity.FOOTER being passed as data in the addFooterView method.
        //return is(MainActivity.FOOTER);
        return null;
    }
}