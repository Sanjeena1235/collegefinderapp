package com.android.jsonregistercheck.collegeinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.model.Review_withdetails;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by user on 8/12/2018.
 */

public class Reviewadapter extends BaseAdapter{
    private Context context;
    private List<Review_withdetails> reviewsList;

    public Reviewadapter(Context context, List<Review_withdetails> reviewsList) {
        this.context = context;
        this.reviewsList = reviewsList;
    }

    @Override
    public int getCount() {


        return reviewsList.size();
    }

    @Override
    public Object getItem(int i) {
        return reviewsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            LayoutInflater layoutInflater =   (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.review_adapter, viewGroup, false);
        }

        TextView txt_username = view.findViewById(R.id.user_name);
        TextView txt_desc = view.findViewById(R.id.review_desc);
        ImageView user_image = view.findViewById(R.id.user_image);

        Review_withdetails reviews = reviewsList.get(i);

        txt_username.setText(reviews.getUsername());
        txt_desc.setText(reviews.getReview());

        System.out.println("Review : "+reviews.getReview());

        Glide.with(context).load(reviews.getImage()).error(R.drawable.ic_camera_alt_black_24dp).into(user_image);

        return view;
    }
}
