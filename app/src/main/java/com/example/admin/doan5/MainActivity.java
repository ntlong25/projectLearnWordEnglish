package com.example.admin.doan5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ChooseAction(View view) {
        Intent in;

        switch (view.getId()) {

            case R.id.btnLeaning:
                bundle = new Bundle();
                bundle.putString("Type", "learning");
                in = new Intent(view.getContext(), chooseTitle_actitvity.class);
                in.putExtra("Data", bundle);
                startActivity(in);

                break;
            case R.id.btnReview:
                bundle = new Bundle();
                bundle.putString("Type", "review");
                in = new Intent(view.getContext(), chooseTitle_actitvity.class);
                in.putExtra("Data", bundle);
                startActivity(in);;

                break;
          /*  case R.id.btnTesting:
                in = new Intent(view.getContext(), caculator_activity.class);
                startActivity(in);
                break;
            case R.id.btnDictionary:
                in = new Intent(view.getContext(), thongtincn_activity.class);
                startActivity(in);
                break;
            case R.id.btnManage:
                in = new Intent(view.getContext(), test_activity.class);
                startActivity(in);
                break;*/
        }

    }
}
