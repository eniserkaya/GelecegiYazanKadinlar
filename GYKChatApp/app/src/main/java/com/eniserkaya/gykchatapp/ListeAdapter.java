package com.eniserkaya.gykchatapp;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.List;

import static android.R.id.message;
import static com.eniserkaya.gykchatapp.R.id.messageTextView;
import static com.eniserkaya.gykchatapp.R.id.photoImageView;


/**
 * Created by eniserkaya on 9.07.2017.
 */

public class ListeAdapter extends ArrayAdapter<ListClass> {

    public ListeAdapter(Context context, int resource, List<ListClass> objects){
        super(context,resource,objects);
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.list_item,parent,false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.icon_img);
        TextView secenekTv = (TextView) convertView.findViewById(R.id.secenek_tv);

        ListClass listeObj = getItem(position);

        iconImageView.setImageResource(listeObj.getIconID());
        secenekTv.setText(listeObj.getSecenek());

        return convertView;
    }
}
