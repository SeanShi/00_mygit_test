package com.example.seanshi.testespresso2;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sean.Shi on 4/11/2017.
 */

public class ListViewAdapter extends ArrayAdapter<Person>{

    private final List<Person> _products;
    private final int _layout;

    public ListViewAdapter(@NonNull Context context, @LayoutRes int resource, List<Person> products) {
        super(context, resource);
        _layout = resource;
        _products = products;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent)
    {
        View rowView = convertview;
        if(convertview==null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(_layout, parent, false);
        }

        ((TextView)rowView.findViewById(R.id.productName)).setText(_products.get(position)._name);
        ((TextView)rowView.findViewById(R.id.description)).setText(_products.get(position)._description);

        return rowView;
    }

    @Override
    public int getCount(){
        return _products.size();
    }

    @Override
    public Person getItem(int position){
        return _products.get(position);
    }
}
