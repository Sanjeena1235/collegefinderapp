package com.android.jsonregistercheck.collegeinfo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.model.FaqClass;
import com.android.jsonregistercheck.retrofit_apiclient.RetrofitClient;
import com.android.jsonregistercheck.retrofit_interface.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        progressDialog = new ProgressDialog(FaqActivity.this);
        progressDialog.setMessage("Loading..........");
        progressDialog.setCancelable(false);
        progressDialog.show();


        String collegeid = getIntent().getExtras().getString("id");
        getMenuJson(collegeid);
    }

    public void getMenuJson(String collegeid) {

        ApiInterface retrofitInterfaceadapter = RetrofitClient.getFormData().create(ApiInterface.class);
        // Call<List<College_Aboutus>> model = retrofitInterfaceadapter.getdetails(collegeid);
        Call<List<FaqClass>> model = retrofitInterfaceadapter.getfaq(collegeid);

        model.enqueue(new Callback<List<FaqClass>>() {

            @Override
            public void onResponse(Call<List<FaqClass>> call, Response<List<FaqClass>> response) {
                List<FaqClass> models = response.body();
                FaqAdapter recyclerviewadapter = new FaqAdapter(FaqActivity.this, models);
                //Recyclerviewadapter recyclerviewadapter1 =new Recyclerviewadapter(MainActivity.this,response.body());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(FaqActivity.this, 1);
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
            public void onFailure(Call<List<FaqClass>> call, Throwable t) {

            }
        });
    }
}
