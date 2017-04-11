package com.example.seanshi.testfragment.dummy;

/**
 * Created by Sean.Shi on 3/2/2017.
 */

public class DummyItem {
    public final String id;
    public final String content;
    public final String details;

    public DummyItem(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
        return content;
    }
}
