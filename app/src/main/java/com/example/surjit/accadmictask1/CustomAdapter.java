package com.example.surjit.accadmictask1;

import android.content.Context;
import android.media.DrmInitData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataClass> {

    ArrayList<DataClass> list = new ArrayList<>();

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<DataClass> objects) {
        super(context, textViewResourceId, objects);
        list = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_view, parent, false);
        }

        ImageView imageView = listItemView.findViewById(R.id.imgView);
        imageView.setImageBitmap(list.get(position).getBitmap());

        TextView textView = listItemView.findViewById(R.id.textimg);
        textView.setText(list.get(position).getText());

        return listItemView;
    }
}
