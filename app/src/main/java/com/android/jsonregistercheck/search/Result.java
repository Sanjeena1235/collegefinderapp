package com.android.jsonregistercheck.search;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.collegeinfo.CollegeAdapter;
import com.android.jsonregistercheck.collegeinfo.College_Display;
import com.android.jsonregistercheck.model.College_list;
import com.android.jsonregistercheck.model.Searchmodel;
import com.android.jsonregistercheck.retrofit_apiclient.RetrofitClient;
import com.android.jsonregistercheck.retrofit_interface.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Result extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        progressDialog = new ProgressDialog(Result.this);
        progressDialog.setMessage("Loading..........");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String name = getIntent().getExtras().getString("name");
        String location = getIntent().getExtras().getString("location");
        String popularity = getIntent().getExtras().getString("popularity");
        String quality = getIntent().getExtras().getString("quality");
        Toast.makeText(Result.this,popularity, Toast.LENGTH_SHORT).show();
        getMenuJson(name, location, popularity, quality);
    }

    public void getMenuJson(String name, String location, String popularity, String quality) {

        ApiInterface retrofitInterfaceadapter = RetrofitClient.getFormData().create(ApiInterface.class);
        Call<List<Searchmodel>> model = retrofitInterfaceadapter.getresult(name, location, popularity, quality);

        model.enqueue(new Callback<List<Searchmodel>>() {
            @Override
            public void onResponse(Call<List<Searchmodel>> call, Response<List<Searchmodel>> response) {
               // Toast.makeText(Result.this,"Response"+response.body(), Toast.LENGTH_SHORT).show();
                List<Searchmodel> models = response.body();
                Resultadapter recyclerviewadapter = new Resultadapter(Result.this,models);
                //Recyclerviewadapter recyclerviewadapter1 =new Recyclerviewadapter(MainActivity.this,response.body());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Result.this, 1);
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
            public void onFailure(Call<List<Searchmodel>> call, Throwable t) {
                System.out.println("Error : "+t.getMessage());
            }
        });
    }
}