package com.example.admin.doan5;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 06/11/2016.
 */
public class title_adapter extends BaseAdapter {

    Context context;
    ArrayList<String> resultID, resultTitle, resultMeaning;
    ArrayList<byte[]> resultPicture;
    private static LayoutInflater inflater = null;
    String data;
    Intent in;
    String type;

    //constructor
    public title_adapter(chooseTitle_actitvity chooseTitle, ArrayList<String> arrID, ArrayList<String> arrTitle, ArrayList<String> arrMeaning, ArrayList<byte[]> arrPicture) {
        // TODO Auto-generated constructor stub
        context = chooseTitle;
        resultID = arrID;
        resultTitle = arrTitle;
        resultMeaning = arrMeaning;
        resultPicture = arrPicture;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return resultID.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    //CLASS HOLDER
    public class Holder {
        TextView tvTitle, tvMeaning, tvID;
        ImageView imgTitle;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        //
        rowView = inflater.inflate(R.layout.title_adapter, null);
        //
        holder.tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        holder.tvMeaning = (TextView) rowView.findViewById(R.id.tvMeaning);
        holder.imgTitle = (ImageView) rowView.findViewById(R.id.imgPicture);

        //
        holder.tvTitle.setText(resultTitle.get(position));
        holder.tvMeaning.setText(resultMeaning.get(position));

        Bitmap bmp = BitmapFactory.decodeByteArray(resultPicture.get(position), 0, resultPicture.get(position).length);
        holder.imgTitle.setImageBitmap(bmp);
        //
        rowView.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           // TODO Auto-generated method stub
                                           //Gọi Activity khác nếu cần
                                           Bundle bundle;
                                           Intent in = ((chooseTitle_actitvity) context).getIntent();
                                           bundle = in.getBundleExtra("Data");
                                           type = bundle.getString("Type");

                                           if (type.equals("review")) {
                                               bundle.putString("ID", resultID.get(position));
                                               in = new Intent(v.getContext(), choose_type_review_activity.class);
                                               in.putExtra("Data", bundle);
                                               context.startActivity(in);
                                           } else if (type.equals("learning")) {
                                               bundle.putString("ID", resultID.get(position));
                                               in = new Intent(v.getContext(), learning_actitvity.class);
                                               in.putExtra("Data", bundle);
                                               context.startActivity(in);
                                           }

                                       }
                                   }

        );
        return rowView;
    }
}
