package com.eniserkaya.customlistview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by eniserkaya on 10.06.2017.
 */

public class ListeAdapter extends ArrayAdapter<Kullanici> {

    Context context;
    List<Kullanici> kullanicilar;
    int layoutResId;

    public ListeAdapter(Context context, int layoutResId, List<Kullanici> kullanicilar){
        super(context, layoutResId, kullanicilar);

        this.kullanicilar=kullanicilar;
        this.context=context;
        this.layoutResId=layoutResId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        inflater = ((Activity) context).getLayoutInflater();

        view = inflater.inflate(layoutResId, parent, false);

        ImageView kullaniciFoto = (ImageView) view.findViewById(R.id.foto_id);
        TextView kullaniciAdi = (TextView) view.findViewById(R.id.ad_id);
        TextView saat = (TextView) view.findViewById(R.id.saat_id);

        kullaniciAdi.setText(kullanicilar.get(position).getAd());
        saat.setText(saatiGetir());

        if(kullanicilar.get(position).getCinsiyet()==1)
            kullaniciFoto.setImageResource(R.drawable.male);
        else
            kullaniciFoto.setImageResource(R.drawable.female);

        return view;

     }

     private String saatiGetir(){

         String currentDateTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
         return currentDateTimeString;

     }

}
