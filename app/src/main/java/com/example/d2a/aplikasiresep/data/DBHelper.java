package com.example.d2a.aplikasiresep.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.d2a.aplikasiresep.BuildConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class DBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "resep.db";

    public final static String DATABASE_PATH = "/data/data/"+ BuildConfig.APPLICATION_ID+"/databases/";

    private SQLiteDatabase db;
    private Context dbContext;
    private List<ResepModel> resepModelList;

    public final static String TABEL_RESEP = "dataresep";
    public final static String kolomidResep= "id_resep";
    public final static String kolomTitle= "title";
    public final static String kolomDesc= "descr";
    public final static String kolomBahan= "bahan";
    public final static String kolomCara = "cara";

    public final static String CREATE_TABLE_RESEP = "CREATE TABLE "+TABEL_RESEP+" ("+
                                                    kolomidResep+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                                    kolomTitle+" TEXT,"+
                                                    kolomDesc+" TEXT,"+
                                                    kolomBahan+" TEXT, "+
                                                    kolomCara+" TEXT )";
    public final static String TABEL_HISTORY = "history";
    public final static String kolomIdHistory = "id_history";
    public final static String kolomCount = "count";

    public final static String CREATE_TABLE_HISTORY = "CREATE TABLE "+TABEL_HISTORY+" ("+
                                                        kolomIdHistory+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                                        kolomidResep+" INTEGER UNIQUE, "+
                                                        kolomCount+" INTEGER )";

    public DBHelper(Context context, List<ResepModel> resepModelList) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.dbContext = context;
        this.resepModelList = resepModelList;

        //Log.i("tblQry", CREATE_TABLE_RESEP);
        //DATABASE_NAME = DBActivity.DatabaseName;
        // checking database and open it if exists

        if (checkDataBase()) {
            openDataBase();
        } else
        {
            if (resepModelList != null) {
                try {
                    open();

                    db.execSQL(CREATE_TABLE_RESEP);
                    db.execSQL(CREATE_TABLE_HISTORY);

                    copyDataBase(resepModelList);

                    this.close();
                    openDataBase();

                } catch (IOException e) {
                    throw new Error("Error copying database");
                }
                Toast.makeText(dbContext, "Database sedang diimport", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void copyDataBase(List<ResepModel> resepModelList) throws IOException{

        String qry ="";
        for (ResepModel data : resepModelList ) {

            qry = "INSERT INTO "+TABEL_RESEP+" ("+kolomTitle+","+kolomDesc+","+ kolomBahan+","+kolomCara+") VALUES ('" +
                    data.getTitle()+"','"+data.getDesc()+"','"+data.getBahan()+"','"+data.getCara()+"')";

            insertData(qry);

        }
    }
    public void openDataBase() throws SQLException {
        String dbPath = DATABASE_PATH + DATABASE_NAME;
        db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        boolean exist = false;
        try {
            String dbPath = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(dbPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            Log.v("db log", "database does't exist");
        }

        if (checkDB != null) {
            exist = true;
            checkDB.close();
        }
        return exist;
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
        db = this.getReadableDatabase();
    }

    public void read() throws SQLException {
        db = this.getReadableDatabase();
    }

    public boolean insertData(String query){

        try {
            db.execSQL(query);
            return true;
        }catch (SQLException e){
            Log.i("insertError", e.toString());
            return false;
        }
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        db.execSQL(CREATE_TABLE_RESEP);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+TABEL_RESEP);
        onCreate(db);

    }

    public List<ResepModel> getListResep() {
        List<ResepModel> resepModelList = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery("select * from "+TABEL_RESEP, null);

        if (c.moveToFirst()) {
            do {
                ResepModel resepModel = new ResepModel();
                // dp.setNama(c.getInt(c.getColumnIndex(KOLOM_ID)));
                resepModel.setId(c.getInt(c.getColumnIndex(kolomidResep)));
                resepModel.setTitle(c.getString(c.getColumnIndex(kolomTitle)));
                resepModel.setDesc(c.getString(c.getColumnIndex(kolomDesc)));
                resepModel.setBahan(c.getString(c.getColumnIndex(kolomBahan)));
                resepModel.setCara(c.getString(c.getColumnIndex(kolomCara)));

               resepModelList.add(resepModel);

            } while (c.moveToNext());
        }
        return resepModelList;
    }

    public List<ResepModel> getListResepHistory() {
        List<ResepModel> resepModelList = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery("select a.* from "+TABEL_RESEP+" as a,"+TABEL_HISTORY+" as b where a.id_resep = b.id_resep order by b.id_history desc", null);

        if (c.moveToFirst()) {
            do {
                ResepModel resepModel = new ResepModel();
                // dp.setNama(c.getInt(c.getColumnIndex(KOLOM_ID)));
                resepModel.setId(c.getInt(c.getColumnIndex(kolomidResep)));
                resepModel.setTitle(c.getString(c.getColumnIndex(kolomTitle)));
                resepModel.setDesc(c.getString(c.getColumnIndex(kolomDesc)));
                resepModel.setBahan(c.getString(c.getColumnIndex(kolomBahan)));
                resepModel.setCara(c.getString(c.getColumnIndex(kolomCara)));

                resepModelList.add(resepModel);

            } while (c.moveToNext());
        }
        return resepModelList;
    }
//
//    public static final String DATABASE_NAME = "food.db";
//    public static final String TABLE_NAME = "categories_table";
//
//    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
//        super(context, name, factory, version);
//    }
//
//
//
//    @Override
//    public void onCreate (SQLiteDatabase db) {
//
//    }


}
