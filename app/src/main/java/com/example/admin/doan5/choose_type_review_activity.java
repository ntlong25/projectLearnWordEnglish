package com.example.admin.doan5;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Admin on 23/11/2016.
 */
public class choose_type_review_activity extends AppCompatActivity {

    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    TextView tvTitleName;
    RadioButton rbWordPicture, rbPictureWord, rbWord;
    String strID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_type_review_layout);

        Intent in = getIntent();
        Bundle bundle = in.getBundleExtra("Data");
        strID = bundle.getString("ID");

        tvTitleName = (TextView) findViewById(R.id.titleName);
        rbPictureWord = (RadioButton) findViewById(R.id.rbPictureWord);
        rbWord = (RadioButton) findViewById(R.id.rbWord);
        rbWordPicture = (RadioButton) findViewById(R.id.rbWordPicture);

        GetTitleName(tvTitleName, "SELECT Title FROM Title WHERE IDtitle=" + strID);
    }

    public void GetTitleName(TextView tvName, String strSELECT) {
        String name = null;
        Cursor cur = databaseHelper.getCursor(strSELECT);
        if (cur.moveToFirst()) {
            do {
                name = cur.getString(0);
            } while (cur.moveToNext());
        }

        tvName.setText(name);

    }

    public void ChooseTypeReviewing(View view) {
        Bundle bundle;
        Intent in;
        switch (view.getId()) {
            case R.id.btnStart:
                if (rbWord.isChecked()) {
                    bundle = new Bundle();
                    bundle.putString("Type", strID);
                    in = new Intent(view.getContext(), word_with_pic_activity.class);
                    in.putExtra("Data", bundle);
                    startActivity(in);
                }

                if (rbWordPicture.isChecked()) {
                    bundle = new Bundle();
                    bundle.putString("Type", strID);
                    in = new Intent(view.getContext(), word_pic_activity.class);
                    in.putExtra("Data", bundle);
                    startActivity(in);
                }


                if (rbPictureWord.isChecked()) {
                    bundle = new Bundle();
                    bundle.putString("Type", strID);
                    in = new Intent(view.getContext(), pic_word_activity.class);
                    in.putExtra("Data", bundle);
                    startActivity(in);
                }

                if (rbPictureWord.isChecked() == false && rbWordPicture.isChecked() == false && rbWord.isChecked() == false) {
                    Toast.makeText(choose_type_review_activity.this, "Please choose type of review to start", Toast.LENGTH_SHORT).show();
                }
        }
    }


}
