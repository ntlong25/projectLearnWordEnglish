package com.example.admin.doan5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Admin on 06/11/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private Context dbcontext;
    private SQLiteDatabase db;
    private static final String dbPath = "/data/data/com.example.admin.doan5/databases/English";
    private static final String dbName = "English.sqlite";
    private static final int dbVersion = 1;

    public DatabaseHelper(Context context) {
        super(context,  dbName, null, dbVersion);
        this.dbcontext = context;
    }

    public void copyDB2SDCard() throws SQLiteException {
        boolean check = false;
        try {
            File file = new File(dbPath);
            check = file.exists();
            if (check) {
                //file.delete();
                this.close();
            } else if (!check) {
                this.getReadableDatabase();
                //
                InputStream myInput = dbcontext.getAssets().open(dbName);
                String outFileName = dbPath;
                OutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        } catch (Exception ex) {
            throw new Error("Error! Can't not copy");
        }
    }

    void OpenDB() {
        db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }


    public Cursor getCursor(String strSQL) {
        OpenDB();
        Cursor c = db.rawQuery(strSQL, null);
        return c;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
