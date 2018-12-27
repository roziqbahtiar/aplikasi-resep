package com.example.d2a.aplikasiresep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.d2a.aplikasiresep.data.DBHelper;
import com.example.d2a.aplikasiresep.data.ResepModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



//    private LinkedList<String> s1;
//    private LinkedList<String> s2 = new LinkedList<String>();
//    private LinkedList<String> s3 = new LinkedList<String>();
//    private LinkedList<String> s4 = new LinkedList<String>();
    private RecyclerView r1;
    private MyAdapter ad;

    public DBHelper dbHelper;
    public List<ResepModel> resepModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new DBHelper(MainActivity.this, resepModelList);
        resepModelList = new ArrayList<>();
        resepModelList  = dbHelper.getListResep();


        r1 = findViewById(R.id.recycler);
        ad = new MyAdapter(this,resepModelList);
        r1.setAdapter(ad);
        r1.setLayoutManager(new LinearLayoutManager(this));

        setTitle("Resep Masakan");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
        if (id == R.id.infoKoki){
           Intent intent = new Intent(MainActivity.this, Barcode.class);
           startActivity(intent);
        }
        if (id == R.id.hisory){
            Intent intent = new Intent(MainActivity.this, History.class);
            startActivity(intent);
        }
        return true;
    }
}
