package com.example.seanshi.testespresso;

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

public class ListViewAdapter extends ArrayAdapter<ProductDetails>{
    private final int _layout;

    public ListViewAdapter(@NonNull Context context, @LayoutRes int resource, List<ProductDetails> products) {
        super(context, resource, products);
        _layout = resource;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent)
    {
        View rowView;
        if(convertview==null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(_layout, parent, false);
        }else{
            rowView = convertview;
        }
        ((TextView)rowView.findViewById(R.id.productName)).setText(getItem(position)._name);
        ((TextView)rowView.findViewById(R.id.description)).setText(getItem(position)._description);

        return rowView;
    }
}
