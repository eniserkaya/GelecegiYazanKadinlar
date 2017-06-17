package com.eniserkaya.gykadnlar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    private TextView hosgeldinTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        hosgeldinTv = (TextView)findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null ){
            String kAdi=bundle.getString("kullaniciadi");
            Log.d("gelenler",kAdi);
            hosgeldinTv.setText("Hoşgeldin "+ kAdi + "!");

        }
    }
}
