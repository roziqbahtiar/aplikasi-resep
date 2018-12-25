package com.example.d2a.aplikasiresep;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.d2a.aplikasiresep.data.DBHelper;
import com.example.d2a.aplikasiresep.data.ResepModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SplashScreen extends Activity {

    private LinkedList<String> s1;
    private LinkedList<String> s2 = new LinkedList<String>();
    private LinkedList<String> s3 = new LinkedList<String>();
    private LinkedList<String> s4 = new LinkedList<String>();

    public DBHelper dbHelper;
    public List<ResepModel> resepModelList;
    public static final int requestCode = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        askPermissions();

    }

    public void initializingdata(){
        //initialize data
        s1 = new LinkedList<String>(Arrays.asList(getResources().getStringArray(R.array.resep)));
        s2 = new LinkedList<String>(Arrays.asList(getResources().getStringArray(R.array.desc)));
        s3 = new LinkedList<String>(Arrays.asList(getResources().getStringArray(R.array.bahan)));
        s4 = new LinkedList<String>(Arrays.asList(getResources().getStringArray(R.array.cara)));

        resepModelList = new ArrayList<>();

        for (int i = 0; i < s1.size(); i++){

            ResepModel resepModel = new ResepModel();

            resepModel.setTitle(s1.get(i));
            resepModel.setDesc(s2.get(i));
            resepModel.setBahan(s3.get(i));
            resepModel.setCara(s4.get(i));

            resepModelList.add(resepModel);

        }
        //end

        dbHelper = new DBHelper(SplashScreen.this, resepModelList);
    }


    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };

        int permissionCheckInternalStorage = ContextCompat.checkSelfPermission(SplashScreen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionReadStorage = ContextCompat.checkSelfPermission(SplashScreen.this, Manifest.permission.READ_EXTERNAL_STORAGE);

        // Here, thisActivity is the current activity
        if (permissionCheckInternalStorage != PackageManager.PERMISSION_GRANTED ||
                permissionReadStorage != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(SplashScreen.this,
                    permissions,
                    requestCode);
        }
        else{

            initializingdata();
            goToMain();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(this.requestCode == requestCode) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // permission was granted, yay! Do the
                // contacts-related task you need to do.

                initializingdata();
                goToMain();


            } else {
                askPermissions();
            }
            return;
        }
    }

    public void goToMain(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainActivity = new Intent(SplashScreen.this,MainActivity.class);
                SplashScreen.this.startActivity(mainActivity);

            }
        },2000);
    }
}
