package com.example.seanshi.testfabric;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;


/**
 * Created by Sean.Shi on 2/1/2018.
 */

public class CrashlyticsAppender extends AppenderSkeleton{

    @Override
    protected void append(LoggingEvent event) {
        String t = event.getThrowableInformation()==null?"":event.getThrowableInformation().getThrowable().toString();

/*        Level log4jLevel = event.getLevel();
        int level = Log.INFO;

        if(log4jLevel.equals(Level.TRACE)){
            level = Log.VERBOSE;
        }else if(log4jLevel.equals(Level.DEBUG)){
            level = Log.DEBUG;
        }else if(log4jLevel.equals(Level.INFO)){
            level = Log.INFO;
        }else if(log4jLevel.equals(Level.WARN)){
            level = Log.WARN;
        }else if(log4jLevel.equals(Level.ERROR)){
            level = Log.ERROR;
        }else if(log4jLevel.equals(Level.FATAL)){
            level = Log.ASSERT;
        }

        if(level<Log.WARN){
            return;
        }
        //Crashlytics.log(level, "tag", ""+event.getMessage()+t);*/
        Crashlytics.log(""+event.getMessage()+"|"+t);
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
