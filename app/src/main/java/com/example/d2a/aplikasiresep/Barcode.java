package com.example.d2a.aplikasiresep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Barcode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        setTitle("Scan QRCODE");
    }
}
