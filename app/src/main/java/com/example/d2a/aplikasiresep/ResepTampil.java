package com.example.d2a.aplikasiresep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.d2a.aplikasiresep.data.DBHelper;

public class ResepTampil extends AppCompatActivity{

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reseptampil);

        Intent intent = getIntent();
        int id = intent.getIntExtra("idResep", 0);
        String title = intent.getStringExtra("title");
        String bahan = intent.getStringExtra("bahan");
        String cara = intent.getStringExtra("cara");
        TextView ttitle = findViewById(R.id.title);
        ttitle.setText(title);
        TextView tbahan = findViewById(R.id.bahan);
        tbahan.setText(bahan);
        TextView tcara = findViewById(R.id.cara);
        tcara.setText(cara);

        dbHelper = new DBHelper(ResepTampil.this, null);

        String qry = "insert or replace into "+dbHelper.TABEL_HISTORY+" ("+
                    dbHelper.kolomidResep+", "+
                    dbHelper.kolomCount+") values ("+id+",0)";


        try{
            dbHelper.insertData(qry);
        }catch (Exception e){
            Log.i("errIns", e.toString());
        }
    }
}
