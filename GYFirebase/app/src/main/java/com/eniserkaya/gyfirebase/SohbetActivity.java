package com.eniserkaya.gyfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SohbetActivity extends AppCompatActivity implements View.OnClickListener {

    private String nickName;
    private ListView lvMesajlar;
    private EditText etMesaj;
    private Button sendBtn;
    private List<Mesaj> mesajList;
    private Adapter adapter;

    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sohbet);

        database = FirebaseDatabase.getInstance();
        mesajList = new ArrayList<Mesaj>();
        adapter = new Adapter(this,R.layout.tek_satir,mesajList);
        lvMesajlar = (ListView)findViewById(R.id.messageListView);
        etMesaj = (EditText)findViewById(R.id.messageEditText);
        sendBtn = (Button) findViewById(R.id.sendButton);
        sendBtn.setOnClickListener(this);

        if(getIntent().getExtras()!=null){
            nickName= getIntent().getExtras().getString("nickname");
        }

        mesajlariGetir();
    }

    private void mesajlariGetir() {
        DatabaseReference dbRef = database.getReference("SohbetOdasi");
        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.sendButton){
            DatabaseReference dbRef = database.getReference("SohbetOdasi");
            dbRef.push().setValue(new Mesaj(etMesaj.getText().toString().trim(),
                    nickName));
            etMesaj.setText("");
        }
    }
}
