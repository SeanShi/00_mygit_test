package com.example.seanshi.testespresso;

import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Sean.Shi on 3/2/2017.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestViewTest.class,
        ExampleInstrumentedTest.class
})

public class RuleSuite {
    @ClassRule
    public static ExternalResource testRule = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            System.out.println("Testing starts.........");
        }

        @Override
        protected void after() {
            System.out.println("Testing ends.........");
        }
    };
}
