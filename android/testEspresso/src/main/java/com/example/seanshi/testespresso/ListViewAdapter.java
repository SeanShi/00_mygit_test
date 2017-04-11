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

    private final List<ProductDetails> _products;
    private final int _layout;

    public ListViewAdapter(@NonNull Context context, @LayoutRes int resource, List<ProductDetails> products) {
        super(context, resource);
        _layout = resource;
        _products = products;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent)
    {
        View rowView;
        System.out.println("................" + position + "    "+ convertview);
        if(convertview==null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(_layout, parent, false);
        }else{
            rowView = convertview;
        }
        ((TextView)rowView.findViewById(R.id.productName)).setText(_products.get(position)._name);
        ((TextView)rowView.findViewById(R.id.description)).setText(_products.get(position)._description);

        return rowView;
    }

    @Override
    public int getCount(){
        return _products.size();
    }
}
