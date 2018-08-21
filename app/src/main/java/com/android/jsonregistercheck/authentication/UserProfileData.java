package com.android.jsonregistercheck.authentication;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.jsonregistercheck.model.UserDataModel;
import com.android.jsonregistercheck.retrofit_apiclient.RetrofitClient;
import com.android.jsonregistercheck.retrofit_interface.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 8/8/2018.
 */

public class UserProfileData {
    Context context;
    SharedPreferences sharedPreferences;
    String image,username,id,email,password,status,address,usertype;


    public UserProfileData(Context context) {
        this.context = context;

    }


    public void getUserData(String usernames,String passwords){

        ApiInterface userApi = RetrofitClient.getFormData().create(ApiInterface.class);

        Call<UserDataModel>userDataModelCall = userApi.getUserProfileData(usernames,passwords);

        userDataModelCall.enqueue(new Callback<UserDataModel>() {
            @Override
            public void onResponse(Call<UserDataModel> call, Response<UserDataModel> response) {



                image = response.body().getMessage().getImage();
                username = response.body().getMessage().getName();
                id = response.body().getMessage().getId();
                email = response.body().getMessage().getEmail();
                password = response.body().getMessage().getPassword();
                status = response.body().getMessage().getStatus();
                address = response.body().getMessage().getAddress();
                usertype = response.body().getMessage().getUser_type();





                sharedPreferences = context.getSharedPreferences("UserDetail",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",username);
                editor.putString("password",password);
                editor.putString("id",id);
                editor.putString("email",email);
                editor.putString("image",image);
                editor.putString("status",status);
                editor.putString("address",address);
                editor.putString("usertype",usertype);
                editor.apply();
            }

            @Override
            public void onFailure(Call<UserDataModel> call, Throwable t) {

                System.out.println("Error "+t.getMessage());

            }
        });

    }

}
