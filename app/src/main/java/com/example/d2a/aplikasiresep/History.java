package com.example.d2a.aplikasiresep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.d2a.aplikasiresep.data.DBHelper;
import com.example.d2a.aplikasiresep.data.ResepModel;

import java.util.List;

public class History extends AppCompatActivity {

    RecyclerView rv_history;
    MyAdapter ad;
    List<ResepModel> resepModelList;

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rv_history = (RecyclerView) findViewById(R.id.history_RV);

        dbHelper = new DBHelper(History.this, null);

        resepModelList = dbHelper.getListResepHistory();

        ad = new MyAdapter(this,resepModelList);
        rv_history.setAdapter(ad);
        rv_history.setLayoutManager(new LinearLayoutManager(this));

        setTitle("HISTORY");
    }
}
