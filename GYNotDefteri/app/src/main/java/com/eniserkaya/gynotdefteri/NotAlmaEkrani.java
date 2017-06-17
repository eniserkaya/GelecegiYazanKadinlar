package com.eniserkaya.gynotdefteri;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import static android.R.attr.data;

public class NotAlmaEkrani extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,View.OnClickListener {

    public static final int PICK_IMG = 1000;
    public static final int TAKE_PHOTO = 2000;

    private Toolbar toolbar;
    private EditText notEt,baslikEt;
    private Button btnKaydet;
    private ImageView imgFoto;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Database db;
    private String konum;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_alma_ekrani);

        db = new Database(this);
        Toast.makeText(this, ""+db.getRowCount(), Toast.LENGTH_SHORT).show();
        toolbar = (Toolbar)findViewById(R.id.toolbar_notalma);
        toolbar.setTitle("deneme");
        setSupportActionBar(toolbar);

        notEt = (EditText)findViewById(R.id.not_et);
        baslikEt = (EditText)findViewById(R.id.baslik_et);
        btnKaydet = (Button) findViewById(R.id.kaydet);
        imgFoto = (ImageView)findViewById(R.id.image_id);
        btnKaydet.setOnClickListener(this);
        buildGoogleApiClient();

    }
    /**
     * Creating google api client object
     * */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
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
                Uri uri = Uri.parse("smsto:05544157979");
                Intent intentSms = new Intent(Intent.ACTION_SENDTO,uri);
                String smsBody = "Not Başlığı: " +baslikEt.getText().toString().trim() +
                        "\nNot: "+notEt.getText().toString().trim();
                intentSms.putExtra("sms_body",smsBody);
                startActivity(intentSms);
                return true;
            case R.id.mail_id:
                Intent intentMail = new Intent(Intent.ACTION_SEND);
                intentMail.setType("text/html");
                intentMail.putExtra(Intent.EXTRA_EMAIL,new String[]{"aeniserkaya@gmail.com"});
                intentMail.putExtra(Intent.EXTRA_SUBJECT,baslikEt.getText().toString().trim());
                intentMail.putExtra(Intent.EXTRA_TEXT,notEt.getText().toString().trim());
                startActivity(Intent.createChooser(intentMail,"Send Email"));
                return true;

            case R.id.galeri_id:
                Intent galeriIntent = new Intent();
                galeriIntent.setType("image/*");
                galeriIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(galeriIntent,PICK_IMG);
                return true;

            case R.id.kamera_id:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,TAKE_PHOTO);
                break;

            case R.id.konum_id:
                gpsAcikMi();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gpsAcikMi() {
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                IzinKontrolEt();
            else{
                displayLocation();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PICK_IMG){
            if(resultCode == RESULT_OK){
                Uri uri = data.getData();
                Log.d("gelenUri",uri+"");
                try {
                    InputStream imageStream = getContentResolver().openInputStream(uri);
                    Bitmap fotoBitmap = BitmapFactory.decodeStream(imageStream);
                    bitmap=fotoBitmap;
                    imgFoto.setImageBitmap(fotoBitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if(requestCode == TAKE_PHOTO){
            if(resultCode == RESULT_OK){
                Bitmap bitmapKamera = (Bitmap)data.getExtras().get("data");
                bitmap=bitmapKamera;
                imgFoto.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void IzinKontrolEt() {
        String[] izinler = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        int izinKodu = 6;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                displayLocation();
            } else {
                //-- Almak istediğimiz izinler daha öncesinde kullanıcı tarafından onaylanmamış ise bu kod bloğu harekete geçecektir.
                //-- Burada requestPermissions() metodu ile kullanıcıdan ilgili Manifest izinlerini onaylamasını istiyoruz.
                requestPermissions(izinler, izinKodu);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 6: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //-- Eğer kullanıcı istemiş olduğunuz izni onaylarsa bu kod bloğu çalışacaktır.
                    displayLocation();
                } else {
                    Toast.makeText(NotAlmaEkrani.this, "Lokasyon için izin vermelisiniz.", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            //-- Farklı 'case' blokları ekleyerek diğer izin işlemlerinizin sonuçlarını da kontrol edebilirsiniz.. Biz burada sadece değerini 67 olarak tanımladığımız izin işlemini kontrol ettik.
        }
    }

    private void displayLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLastLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {
                double latitude = mLastLocation.getLatitude();
                double longitude = mLastLocation.getLongitude();
                adresGetir(latitude,longitude);

                Log.d("enlem", "" + latitude);
                Log.d("boylam", "" + longitude);

                //lblLocation.setText(latitude + ", " + longitude);

            } else {

                //lblLocation
                // .setText("(Couldn't get the location. Make sure location is enabled on the device)");
            }
        } else {
            Toast.makeText(this, "İzinler eksik", Toast.LENGTH_SHORT).show();
        }
    }
    public void adresGetir(double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            Toast.makeText(this, ""+state, Toast.LENGTH_SHORT).show();
            konum=state;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.kaydet){
            db.notEkle(new Not(baslikEt.getText().toString().trim(),
                    notEt.getText().toString().trim(),
                    konum,getBitmapAsByteArray(bitmap)));
            Toast.makeText(this, "Kaydedildi!", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    private byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,outputStream);
        return outputStream.toByteArray();
    }

}
