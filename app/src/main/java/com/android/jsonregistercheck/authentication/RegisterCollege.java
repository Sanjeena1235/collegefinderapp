package com.android.jsonregistercheck.authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.collegeinfo.College_Display;
import com.android.jsonregistercheck.retrofit_apiclient.RetrofitClient;
import com.android.jsonregistercheck.retrofit_interface.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCollege extends AppCompatActivity {

    TextView txt_signUp;
    EditText edit_name, edit_location, edit_usertype;
    Button btn_forget_password, btn_facebook_login, btn_gmail_login, btn_login;
    Toolbar toolbar;
    String name, location, usertype;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_college);

        toolbar = findViewById(R.id.toolbar);
        txt_signUp = findViewById(R.id.txt_SignUp);
        btn_login = findViewById(R.id.btn_sign_in);
        edit_name = findViewById(R.id.collegename);
        edit_location = findViewById(R.id.location);
        edit_usertype = findViewById(R.id.usertype);
        txt_signUp = findViewById(R.id.txt_SignUp);
        progressBar = findViewById(R.id.progressbar);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoggedIn();
            }
        });

        txt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

    }

    public void LoggedIn() {

        //assigning the edit_phone_text to phone string
        name = edit_name.getText().toString();

        //assigning the edit_password_text to password string
        location = edit_location.getText().toString();
        usertype = edit_location.getText().toString();
        UserLogIn(name, location, usertype);

    }

    public void UserLogIn(final String name, final String location, final String usertype) {

        ApiInterface loginApiInterface = RetrofitClient.getFormData().create(ApiInterface.class);


        //// Passing the form field using MultiPartBody

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("location", location)
                .addFormDataPart("type", usertype)
                .build();

        loginApiInterface.postregistercollege(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String succes = jsonObject.optString("success");
                        String message = jsonObject.optString("message");

                        if (succes.equals("1")) {

                            startActivity(new Intent(getApplicationContext(), College_Display.class));
                            Toast.makeText(RegisterCollege.this, message, Toast.LENGTH_SHORT).show();
                        } else if(succes.equals("01")) {
                            Toast.makeText(RegisterCollege.this, message, Toast.LENGTH_SHORT).show();
                        }
                        else{

                            Toast.makeText(RegisterCollege.this, message, Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (NullPointerException ex) {
                        ex.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("Error : " + String.valueOf(response.errorBody()));
                }
            }



            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Error ", t.getMessage());
            }

            });
        }
        public void backArrow(View view) {
            finish();
        }
}
