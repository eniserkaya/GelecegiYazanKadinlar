package com.eniserkaya.gynotdefteri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class NotAlmaEkrani extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_alma_ekrani);

        toolbar = (Toolbar)findViewById(R.id.toolbar_notalma);
        toolbar.setTitle("deneme");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_iki,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.sms_id:
                Toast.makeText(this, "Sms atılacak", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mail_id:
                Toast.makeText(this, "Mail atılacak", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
