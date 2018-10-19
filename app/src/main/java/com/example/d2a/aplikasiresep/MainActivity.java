package com.example.d2a.aplikasiresep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {



    private LinkedList<String> s1;
    private LinkedList<String> s2 = new LinkedList<String>();
    private LinkedList<String> s3 = new LinkedList<String>();
    private LinkedList<String> s4 = new LinkedList<String>();
    private RecyclerView r1;
    private MyAdapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        s1 = new LinkedList<String>(Arrays.asList(getResources().getStringArray(R.array.resep)));
        s2 = new LinkedList<String>(Arrays.asList(getResources().getStringArray(R.array.desc)));
        s3 = new LinkedList<String>(Arrays.asList(getResources().getStringArray(R.array.bahan)));
        s4 = new LinkedList<String>(Arrays.asList(getResources().getStringArray(R.array.cara)));
        setContentView(R.layout.activity_main);
        r1 = findViewById(R.id.recycler);
        ad = new MyAdapter(this,s1,s2,s3,s4);
        r1.setAdapter(ad);
        r1.setLayoutManager(new LinearLayoutManager(this));
    }




}
