package com.android.jsonregistercheck.collegeinfo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.model.College_Aboutus;
import com.android.jsonregistercheck.model.StaffClass;
import com.android.jsonregistercheck.retrofit_apiclient.RetrofitClient;
import com.android.jsonregistercheck.retrofit_interface.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        progressDialog = new ProgressDialog(StaffActivity.this);
        progressDialog.setMessage("Loading..........");
        progressDialog.setCancelable(false);
        progressDialog.show();


        String collegeid = getIntent().getExtras().getString("id");
        getMenuJson(collegeid);

    }

    public void getMenuJson(String collegeid) {

        ApiInterface retrofitInterfaceadapter = RetrofitClient.getFormData().create(ApiInterface.class);
        // Call<List<College_Aboutus>> model = retrofitInterfaceadapter.getdetails(collegeid);
        Call<List<StaffClass>> model = retrofitInterfaceadapter.getstaff(collegeid);

        model.enqueue(new Callback<List<StaffClass>>() {


            @Override
            public void onResponse(Call<List<StaffClass>> call, Response<List<StaffClass>> response) {
                List<StaffClass> models = response.body();
                StaffAdapter recyclerviewadapter = new StaffAdapter(StaffActivity.this, models);
                //Recyclerviewadapter recyclerviewadapter1 =new Recyclerviewadapter(MainActivity.this,response.body());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(StaffActivity.this, 1);
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
            public void onFailure(Call<List<StaffClass>> call, Throwable t) {
                System.out.println("Error : " + t.getMessage());
            }
        });
    }

}
