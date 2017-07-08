package com.eniserkaya.gykchatapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by eniserkaya on 8.07.2017.
 */

public class MessageAdapter extends ArrayAdapter<MessageClass> {

    public MessageAdapter(Context context, int resource, List<MessageClass> objects){
        super(context,resource,objects);
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.message_item,parent,false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView userNameTextView =(TextView)convertView.findViewById(R.id.nameTextView);

        MessageClass message = getItem(position);

        boolean isPhoto = message.getPhotoUrl() != null;
        if(isPhoto){
            // Ekrana foto gosterilecek
            messageTextView.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);

            // EKRANA BASMAK ICIN BIR KUTUPHANE GEREKIYOR
        }
        else{
            //Ekrana mesaj gosterilecek.
            messageTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            messageTextView.setText(message.getMessage());
        }
        userNameTextView.setText(message.getUserName());
        return convertView;
    }
}
