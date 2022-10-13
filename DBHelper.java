package com.example.c11200027_bertrandogunawan_crud_uts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Datagudang.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(Produk TEXT primary key,Jumlah TEXT, Harga TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean createdatagudang(String produk, String jumlah, String harga){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Produk", produk);
        contentValues.put("Jumlah", jumlah);
        contentValues.put("Harga", harga);
        long result=DB.insert("Userdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updatedatagudang(String produk, String jumlah, String harga){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Jumlah", jumlah);
        contentValues.put("Harga", harga);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where Produk = ?", new String[]{produk});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "Produk=?", new String[]{produk});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean removedatagudang(String produk){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where Produk = ?", new String[]{produk});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "Produk=?", new String[]{produk});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}
