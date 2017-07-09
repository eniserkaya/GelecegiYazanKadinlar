package com.eniserkaya.gykchatapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class PwChangeActivity extends AppCompatActivity {

    private Button changeBtn;
    private EditText newPwEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_change);

        changeBtn =(Button)findViewById(R.id.changeBtn);
        newPwEditText = (EditText)findViewById(R.id.newPw);

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sifreDegistir();

            }
        });

    }

    private void sifreDegistir() {
        FirebaseAuth.getInstance().getCurrentUser().getUid()
                DAtabase reference = FirebaseDatabase.getInstance().getReference("KullanıcıBilgileri/"+FirebaseAuth.getInstance().getCurrentUser().getUid());

        String provider = FirebaseAuth.getInstance().getCurrentUser().getProviders().get(0);
        if(!provider.equalsIgnoreCase("google.com"))
        FirebaseAuth.getInstance()
                .getCurrentUser()
                .updatePassword(newPwEditText.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("degisti", "User password updated.");
                }

            }
        });
        else{
            Toast.makeText(this, "Google hesabının şifresini değiştiremeyiz.", Toast.LENGTH_SHORT).show();
        }
    }
}
