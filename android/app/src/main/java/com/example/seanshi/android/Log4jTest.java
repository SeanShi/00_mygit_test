package com.example.seanshi.android;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class Log4jTest {
    public static void test()
    {
        Logger logger = Logger.getLogger(Log4jTest.class);
        logger.info("aaaa");
    }
}
