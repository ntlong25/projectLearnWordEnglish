package com.example.admin.doan5;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Admin on 06/11/2016.
 */
public class chooseTitle_actitvity extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    ListView lvTitle;
    ArrayList<String> arrID, arrTitle, arrMeaning;
    ArrayList<byte[]> arrPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosetitle_layout);

        try {
            db.copyDB2SDCard();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        lvTitle = (ListView) findViewById(R.id.lvTitle);

        HienThi(lvTitle, "SELECT * FROM Title");
        Toast.makeText(chooseTitle_actitvity.this, arrID.size() + "", Toast.LENGTH_LONG).show();
    }

    public void HienThi(ListView lv, String strSelect) {
        arrID = new ArrayList<>();
        arrTitle = new ArrayList<>();
        arrMeaning = new ArrayList<>();
        arrPicture = new ArrayList<>();
        //
        Cursor cur = db.getCursor(strSelect);
        if (cur.moveToFirst()) {
            do {
                arrID.add(cur.getString(0));
                arrTitle.add(cur.getString(1));
                arrPicture.add(cur.getBlob(2));
                arrMeaning.add(cur.getString(3));
            } while (cur.moveToNext());
        }
        lv.setAdapter(new title_adapter(this, arrID, arrTitle, arrMeaning, arrPicture));
    }
}