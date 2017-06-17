package com.eniserkaya.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ListeAdapter adapter;
    private List<Kullanici> kullanicilar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.list_customm);
        kullanicilar=new ArrayList<Kullanici>();
        kullanicilar.add(new Kullanici("Gülay Erkaya",0,"13:55"));
        kullanicilar.add(new Kullanici("Enis Erkaya",1,"13:55"));
        kullanicilar.add(new Kullanici("Mehmet Erkaya",1,"13:55"));
        kullanicilar.add(new Kullanici("Ali Kaya",1,"13:55"));
        adapter=new ListeAdapter(MainActivity.this,R.layout.tek_satir,kullanicilar);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                kullanicilar.add(new Kullanici("Enis Kaya",1,"13:55"));
                adapter.notifyDataSetChanged();
            }
        });

    }
}
