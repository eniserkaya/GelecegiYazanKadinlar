package com.eniserkaya.gyfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText nickEt;
    private Button btnBasla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nickEt = (EditText)findViewById(R.id.nick_id);
        btnBasla = (Button)findViewById(R.id.katil_id);

        btnBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nickEt.getText().toString().trim().isEmpty()){
                    Bundle bundle = new Bundle();
                    bundle.putString("nickname",nickEt.getText().toString().trim());
                    Intent intent = new Intent(LoginActivity.this,SohbetActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
