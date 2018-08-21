package com.android.jsonregistercheck.collegeinfo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.model.College_list;
import com.android.jsonregistercheck.retrofit_apiclient.RetrofitClient;
import com.android.jsonregistercheck.retrofit_interface.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class College_Display extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college__display);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        progressDialog = new ProgressDialog(College_Display.this);
        progressDialog.setMessage("Loading..........");
        progressDialog.setCancelable(false);
        progressDialog.show();
        getMenuJson();
    }
    public void getMenuJson() {

        ApiInterface retrofitInterfaceadapter = RetrofitClient.getFormData().create(ApiInterface.class);
        Call<List<College_list>> model = retrofitInterfaceadapter.getMenu();

        model.enqueue(new Callback<List<College_list>>() {
            @Override
            public void onResponse(Call<List<College_list>> call, Response<List<College_list>> response) {
               // Toast.makeText(College_Display.this,"Response"+response.body(), Toast.LENGTH_SHORT).show();
                List<College_list> models = response.body();
                CollegeAdapter recyclerviewadapter = new CollegeAdapter(College_Display.this,models);
                //Recyclerviewadapter recyclerviewadapter1 =new Recyclerviewadapter(MainActivity.this,response.body());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(College_Display.this, 2);
               //  LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(College_Display.this, LinearLayoutManager.HORIZONTAL, false);
                //recyclerView.setLayoutManager(horizontalLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerviewadapter);

                recyclerviewadapter.notifyDataSetChanged();
                if (progressDialog.isShowing()
                        ) {
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<College_list>> call, Throwable t) {
                System.out.println("Error : "+t.getMessage());
            }

        });
    }
}