package com.eniserkaya.gykchatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.editable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // view tanımlamaları
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        mMessageListView = (ListView)findViewById(R.id.messageListView);
        mPhotoPickerButton = (ImageButton)findViewById(R.id.photoPickerButton);
        mMessageEditText = (EditText)findViewById(R.id.messageEditText);
        mSendButton = (Button)findViewById(R.id.sendButton);
        // Adapter tanımlamaları
        List<MessageClass> messageList = new ArrayList<MessageClass>();
        mMessageAdapter = new MessageAdapter(this,R.layout.message_item,messageList);
        mMessageListView.setAdapter(mMessageAdapter);
        // progresbar ayarlamasi
        mProgressBar.setVisibility(View.INVISIBLE);

        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length()>0)
                    mSendButton.setEnabled(true);
                else
                    mSendButton.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Galeri Açılacak
                Toast.makeText(MainActivity.this, "Galeri butonuna basıldı.", Toast.LENGTH_SHORT).show();
            }
        });

        mSendButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sendButton:
                // Firebase'e data gonderecegiz.
                mMessageEditText.setText("");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.sign_out_menu){
            // Cikis yapcak
            Toast.makeText(this, "Çıkış Yap", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
