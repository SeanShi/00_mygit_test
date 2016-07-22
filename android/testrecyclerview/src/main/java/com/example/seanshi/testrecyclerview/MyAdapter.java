package com.example.seanshi.testrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Sean.Shi on 5/25/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private String[] _dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView _textView;
        public ViewHolder(View v)
        {
            super(v);

            _textView = (TextView) v.findViewById(R.id.text);
        }
    }

    public MyAdapter(String[] dataSet)
    {
        _dataSet = dataSet;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder._textView.setText(_dataSet[position]);
    }

    @Override
    public int getItemCount()
    {
        return _dataSet.length;
    }
}
