package com.eniserkaya.gykadnlar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;
    private Button uyeOlBtn;
    private EditText kullaniciAdiEt,sifreEt;
    private String sifre = "asd123";
    private EditText et;
    private String kAdi="eniserkaya";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = (Button)findViewById(R.id.login_btn_id);
        kullaniciAdiEt= (EditText)findViewById(R.id.kadi_et_id);
        sifreEt = (EditText)findViewById(R.id.sifre_et_id);
        uyeOlBtn = (Button)findViewById(R.id.uye_ol_id);

        loginBtn.setOnClickListener(this);
        uyeOlBtn.setOnClickListener(this);

        /*loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!kullaniciAdiEt.getText().toString().trim().isEmpty() && !sifreEt.getText().toString().trim().isEmpty()){
                    String gelenKullaniciAdi = kullaniciAdiEt.getText().toString().trim();
                    String gelenSifre = sifreEt.getText().toString().trim();

                    if(gelenKullaniciAdi.equals(kAdi) && gelenSifre.equals(sifre)) {

                        Bundle bundle = new Bundle();
                        bundle.putString("kullaniciadi",gelenKullaniciAdi);
                        Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);


                    }
                    else
                        Toast.makeText(MainActivity.this, "Hatalı Giriş !", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Lütfen boş bırakmayınız.", Toast.LENGTH_SHORT).show();
                }


            }
        });
        */
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn_id:
                if(!kullaniciAdiEt.getText().toString().trim().isEmpty() && !sifreEt.getText().toString().trim().isEmpty()){
                    String gelenKullaniciAdi = kullaniciAdiEt.getText().toString().trim();
                    String gelenSifre = sifreEt.getText().toString().trim();

                    if(gelenKullaniciAdi.equals(kAdi) && gelenSifre.equals(sifre)) {

                        Bundle bundle = new Bundle();
                        bundle.putString("kullaniciadi",gelenKullaniciAdi);
                        Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);


                    }
                    else
                        Toast.makeText(MainActivity.this, "Hatalı Giriş !", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Lütfen boş bırakmayınız.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.uye_ol_id:
                Toast.makeText(this, "Uye Ola Bastın!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
