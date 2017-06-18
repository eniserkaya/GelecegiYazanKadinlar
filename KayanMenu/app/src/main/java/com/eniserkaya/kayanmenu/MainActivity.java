package com.eniserkaya.kayanmenu;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar myToolbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitle("Kayan Menü");
        myToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(myToolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId()) {
                    case R.id.ekle_id:
                        Toast.makeText(MainActivity.this, "Ekleye bastın", Toast.LENGTH_SHORT).show();
                        fragment = new EkleFragment();
                        break;
                    case R.id.sil_id:
                        fragment = new SilFragment();
                        Toast.makeText(MainActivity.this, "Sile bastın", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_quit:
                        Toast.makeText(MainActivity.this, "Çıkışa bastın", Toast.LENGTH_SHORT).show();
                        break;

                }
                drawer.closeDrawer(GravityCompat.START);
                if(fragment!=null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.activity_main_container, fragment);
                    ft.commit();
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(Gravity.LEFT);
        else
            super.onBackPressed();
    }
}
