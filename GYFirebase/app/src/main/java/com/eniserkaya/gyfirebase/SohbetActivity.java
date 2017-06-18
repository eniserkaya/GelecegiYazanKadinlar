package com.eniserkaya.gyfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.List;

public class SohbetActivity extends AppCompatActivity implements View.OnClickListener {

    private String nickName;
    private ListView lvMesajlar;
    private EditText etMesaj;
    private Button sendBtn;
    private List<Mesaj> mesajList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sohbet);

        lvMesajlar = (ListView)findViewById(R.id.messageListView);
        etMesaj = (EditText)findViewById(R.id.messageEditText);
        sendBtn = (Button) findViewById(R.id.sendButton);
        sendBtn.setOnClickListener(this);

        if(getIntent().getExtras()!=null){
            nickName= getIntent().getExtras().getString("nickname");
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.sendButton){

        }

    }
}
