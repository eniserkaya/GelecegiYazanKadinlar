package com.eniserkaya.gyfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button ekleBtn;
    private EditText ekleEt;
    private TextView okunanTv;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Firebase Database baglantisi
        firebaseDatabase = FirebaseDatabase.getInstance();

        ekleBtn = (Button)findViewById(R.id.button_id);
        ekleEt = (EditText) findViewById(R.id.et_id);
        okunanTv =(TextView)findViewById(R.id.okunan_yazi_id);

        ekleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ekleEt.setText("");
                DatabaseReference dbRef = firebaseDatabase.getReference("Yapilacaklar");
                dbRef.push().setValue(ekleEt.getText().toString().trim());
            }
        });

        DatabaseReference dbOku = firebaseDatabase.getReference("Yapilacaklar");
        dbOku.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> keys =dataSnapshot.getChildren();
                for(DataSnapshot key : keys){
                    okunanTv.append("\n"+key.getValue().toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });



    }
}
