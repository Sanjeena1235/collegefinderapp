package com.android.jsonregistercheck.collegeinfo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.model.ContactClass;
import com.android.jsonregistercheck.retrofit_apiclient.RetrofitClient;
import com.android.jsonregistercheck.retrofit_interface.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        progressDialog = new ProgressDialog(ContactActivity.this);
        progressDialog.setMessage("Loading..........");
        progressDialog.setCancelable(false);
        progressDialog.show();


        String collegeid = getIntent().getExtras().getString("id");
        getMenuJson(collegeid);
    }

    public void getMenuJson(String collegeid) {

        ApiInterface retrofitInterfaceadapter = RetrofitClient.getFormData().create(ApiInterface.class);
        // Call<List<College_Aboutus>> model = retrofitInterfaceadapter.getdetails(collegeid);
        Call<List<ContactClass>> model = retrofitInterfaceadapter.getcontact(collegeid);

        model.enqueue(new Callback<List<ContactClass>>() {

            @Override
            public void onResponse(Call<List<ContactClass>> call, Response<List<ContactClass>> response) {
                List<ContactClass> models = response.body();
                ContactAdapter recyclerviewadapter = new ContactAdapter(ContactActivity.this, models);
                //Recyclerviewadapter recyclerviewadapter1 =new Recyclerviewadapter(MainActivity.this,response.body());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ContactActivity.this, 1);
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
            public void onFailure(Call<List<ContactClass>> call, Throwable t) {

            }
        });
    }
}
