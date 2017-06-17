package com.eniserkaya.gynotdefteri;

/**
 * Created by eniserkaya on 17.06.2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import static android.R.attr.id;

/**
 * Created by eniserkaya on 16.06.2017.
 */

public class Database extends SQLiteOpenHelper {

    // DatabaseLocal Name
    private static final String DATABASE_NAME = "notlarim_database";
    // DatabaseLocal Version
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "notlar_tablosu";

    private static String not_id = "id";
    private static String baslik = "baslik";
    private static String kaydetNot = "not_icerik";
    private static String konum = "konum";
    private static String fotoImg = "fotograf";




    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE= "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + not_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + baslik + " TEXT,"
                + kaydetNot + " TEXT,"
                + fotoImg +" BLOB,"
                + konum + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }
    public void notEkle(Not not) {

        String sql = "INSERT INTO " + TABLE_NAME + " ( baslik, not_icerik, fotograf, konum ) VALUES ( ?, ?, ?, ? )";
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement(sql);
        db.beginTransaction();

        stmt.bindString(1,not.getBaslik());
        stmt.bindString(2,not.getNotIcerik());
        stmt.bindBlob(3,not.getFoto());
        stmt.bindString(4,not.getKonum());

        stmt.execute();
        stmt.clearBindings();
        Log.d("eklendi",""+not.getBaslik());

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close(); //DatabaseLocal Bağlantısını kapattık
    }

    public void notSil(String  id){ //kelime verilen row u silmek için

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, not_id + " = ?",
                new String[] { id });
        db.close();
    }
    public ArrayList<Not> notlariGetir(){
        String notBaslik,notIcerik,notKonum;
        String id;
        byte[] fotograf;
        ArrayList<Not> notlarListesi = new ArrayList<Not>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                id=cursor.getString(cursor.getColumnIndex(not_id));
                notBaslik=cursor.getString(cursor.getColumnIndex(baslik));
                Log.d("notbilgisi",notBaslik);
                notIcerik=cursor.getString(cursor.getColumnIndex(kaydetNot));
                Log.d("notbilgisi",notIcerik);
                fotograf = cursor.getBlob(cursor.getColumnIndex(fotoImg));
                Log.d("notbilgisi",fotograf+"");

                notKonum=cursor.getString(cursor.getColumnIndex(konum));
                Log.d("notbilgisi",notKonum+"");

                notlarListesi.add(new Not(id,notBaslik,notIcerik,notKonum,fotograf));

            } while (cursor.moveToNext());
        }
        if(cursor!=null)
            cursor.close();
        db.close();

        return notlarListesi;
    }

    public boolean notVarMi(Not gelenNot)
    {
        String selectQuery = "SELECT * FROM "+TABLE_NAME+" WHERE ("+id+") = '"+gelenNot.getId()+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int rowCountum = cursor.getCount();
        if(rowCountum>0)
            return true;
        else
            return false;
    }

    public void notGuncelle(Not gelenNot) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Bu methodda ise var olan veriyi güncelliyoruz(update)
        ContentValues values = new ContentValues();
        values.put(baslik, gelenNot.getBaslik());
        values.put(kaydetNot, gelenNot.getNotIcerik());
        values.put(konum, gelenNot.getKonum());
        values.put(fotoImg,gelenNot.getFoto());

        // updating row
        db.update(TABLE_NAME, values, not_id + " = ?",
                new String[] { gelenNot.getId() });
    }
    public void resetTables(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
    public int getRowCount() {
        // Bu method bu uygulamada kullanylmyyor ama her zaman lazym olabilir.Tablodaki row sayysyny geri döner.
        //Login uygulamasynda kullanaca?yz
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        // return row count
        return rowCount;
    }
    public Not notDetay(String id){
        String notBaslik,notIcerik,notKonum;
        Not not=null;
        byte[] fotograf;
        String selectQuery = "SELECT * FROM " + TABLE_NAME+ " WHERE "+not_id+"="+id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                notBaslik=cursor.getString(cursor.getColumnIndex(baslik));
                Log.d("notbilgisi",notBaslik);
                notIcerik=cursor.getString(cursor.getColumnIndex(kaydetNot));
                Log.d("notbilgisi",notIcerik);
                fotograf = cursor.getBlob(cursor.getColumnIndex(fotoImg));
                Log.d("notbilgisi",fotograf+"");

                notKonum=cursor.getString(cursor.getColumnIndex(konum));
                Log.d("notbilgisi",notKonum+"");
                not = new Not(notBaslik,notIcerik,notKonum,fotograf);

            } while (cursor.moveToNext());
        }
        if(cursor!=null)
            cursor.close();
        db.close();
        return not;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}