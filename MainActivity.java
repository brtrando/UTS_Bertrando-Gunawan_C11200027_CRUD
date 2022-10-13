package com.example.c11200027_bertrandogunawan_crud_uts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText produk, jumlah, harga;
    Button create, update, remove, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        produk = findViewById(R.id.produk);
        jumlah = findViewById(R.id.jumlah);
        harga = findViewById(R.id.harga);
        create = findViewById(R.id.btCreate);
        update = findViewById(R.id.btUpdate);
        remove = findViewById(R.id.btRemove);
        view = findViewById(R.id.btView);
        DB = new DBHelper(this);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String produkTXT = produk.getText().toString();
                String jumlahTXT = jumlah.getText().toString();
                String hargaTXT = harga.getText().toString();

                Boolean checkcreatdatagudang = DB.createdatagudang(produkTXT, jumlahTXT, hargaTXT);
                if(checkcreatdatagudang==true)
                    Toast.makeText(MainActivity.this, "Produk Baru Berhasil Dimasukkan", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Produk Baru Gagal Dimasukkan", Toast.LENGTH_SHORT).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String produkTXT = produk.getText().toString();
                String jumlahTXT = jumlah.getText().toString();
                String hargaTXT = harga.getText().toString();
                Boolean checkupdatadatagudang = DB.updatedatagudang(produkTXT, jumlahTXT, hargaTXT);
                if(checkupdatadatagudang==true)
                    Toast.makeText(MainActivity.this, "Produk Telah Diperbaharui", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Produk Tidak Diperbaharui", Toast.LENGTH_SHORT).show();
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String produkTXT = produk.getText().toString();
                String jumlahTXT = jumlah.getText().toString();
                String hargaTXT = harga.getText().toString();
                Boolean checkremovedatagudang = DB.removedatagudang(produkTXT);
                if(checkremovedatagudang==true)
                    Toast.makeText(MainActivity.this, "Produk Telah Dihapus", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Produk Tidak Terhapus", Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "Tidak ada Data", Toast.LENGTH_SHORT).show();
                return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Produk :"+res.getString(0)+"\n");
                    buffer.append("Jumlah :"+res.getString(1)+"\n");
                    buffer.append("Harga :"+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Barang Gudang");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}