package com.eniserkaya.gynotdefteri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class NotAlmaEkrani extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_alma_ekrani);

        toolbar = (Toolbar)findViewById(R.id.toolbar_notalma);
        toolbar.setTitle("deneme");
    }
}
