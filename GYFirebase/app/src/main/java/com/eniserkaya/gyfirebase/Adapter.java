package com.eniserkaya.gyfirebase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by eniserkaya on 18.06.2017.
 */

public class Adapter  extends ArrayAdapter<Mesaj>{
    Context context;
    List<Mesaj> mesajList;
    int layoutResId;

    public Adapter(Context context, int layoutResId, List<Mesaj> mesajList){
        super(context, layoutResId, mesajList);

        this.mesajList=mesajList;
        this.context=context;
        this.layoutResId=layoutResId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        inflater = ((Activity) context).getLayoutInflater();

        view = inflater.inflate(layoutResId, parent, false);


        TextView kullaniciAdi = (TextView) view.findViewById(R.id.nickname_tv);
        TextView mesaj = (TextView) view.findViewById(R.id.mesaj_tv);

        kullaniciAdi.setText(mesajList.get(position).getGonderenAdi());
        mesaj.setText(mesajList.get(position).getMesaj());

        return view;

    }
}