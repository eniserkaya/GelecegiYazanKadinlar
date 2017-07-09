package com.eniserkaya.gykchatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {


    private CircleImageView profileImg;
    private TextView userNameTv;
    private ListView customLv;
    private ListeAdapter mAdapter;
    private List<ListClass> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        profileImg = (CircleImageView) findViewById(R.id.profile_img);
        userNameTv = (TextView)findViewById(R.id.username_tv);
        customLv = (ListView)findViewById(R.id.listview_id);

        userNameTv.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        Glide.with(this).load(FirebaseAuth.getInstance()
                .getCurrentUser()
                .getPhotoUrl())
                .into(profileImg);

        mList = new ArrayList<ListClass>();
        mList.add(new ListClass(R.drawable.ic_pw_change,"Şifre Değiştir"));
        mList.add(new ListClass(R.drawable.ic_action_name,"Hesabı Sil"));
        mList.add(new ListClass(R.drawable.ic_action_exit,"Çıkış Yap"));
        mAdapter = new ListeAdapter(this,R.layout.list_item,mList);
        customLv.setAdapter(mAdapter);


    }

}
