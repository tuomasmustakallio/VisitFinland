package com.example.visitfinland;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] cities;
    String[] dists;
    Integer[] mImageIds;

    public ItemAdapter(Context context, String[] c, String[] d, Integer[] i){
        cities = c;
        dists = d;
        mImageIds = i;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() { return cities.length; }

    @Override
    public Object getItem(int position) { return cities[position]; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.list_city, null);
        TextView cityTextView = (TextView) v.findViewById(R.id.cityTextView);
        TextView distanceTextView = (TextView) v.findViewById(R.id.distanceTextView);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);

        String city = cities[position];
        String dist = dists[position];

        cityTextView.setText(city);
        distanceTextView.setText(dist);
        imageView.setImageResource(mImageIds[position]);

        return v;
    }
}
