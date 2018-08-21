package com.android.jsonregistercheck.authentication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jsonregistercheck.MainActivity;
import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.collegeinfo.College_Display;
import com.android.jsonregistercheck.retrofit_apiclient.RetrofitClient;
import com.android.jsonregistercheck.retrofit_interface.ApiInterface;
import com.android.jsonregistercheck.validation.Validate_Class;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextView txt_signUp;
    EditText edit_phone, edit_password;
    Button btn_forget_password, btn_facebook_login, btn_gmail_login, btn_login;
    Toolbar toolbar;
    String email, password;
    SharedPreferences sharedPreferences;
    ProgressBar progressBar;
    ImageView img_back_arrow;
    UserProfileData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_login);
        userData = new UserProfileData(Login.this);

        try {
            sharedPreferences = getSharedPreferences("UserDetail", Context.MODE_PRIVATE);
            email = sharedPreferences.getString("email", null);
            password = sharedPreferences.getString("password", "");

            //check if string is null or not

            if (!email.equals("") && !password.equals("")) {
               // startActivity(new Intent(getApplicationContext(), Register.class));
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        toolbar = findViewById(R.id.toolbar);
        txt_signUp = findViewById(R.id.txt_SignUp);
        btn_login = findViewById(R.id.btn_sign_in);
        edit_password = findViewById(R.id.edit_password);
        edit_phone = findViewById(R.id.edit_phone);
        btn_forget_password = findViewById(R.id.btn_forgetPassword);
        txt_signUp = findViewById(R.id.txt_SignUp);
        progressBar = findViewById(R.id.progressbar);

        img_back_arrow = toolbar.findViewById(R.id.back_arrow);

        img_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        btn_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgetPasswordActivity.class));
            }
        });

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

        String first = "New Here ? ";
        String next = "<font color='#69c730'>Sign Up</font>";
        txt_signUp.setText(Html.fromHtml(first + next));

        //toolbar_height

        int statusBar = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBar = getResources().getDimensionPixelSize(resourceId);
        }


    }

    public void LoggedIn() {

        //assigning the edit_phone_text to phone string
        email = edit_phone.getText().toString();

        //assigning the edit_password_text to password string
        password = edit_password.getText().toString();

        ///// validating the string if it is empty or not

        if (email.equals("")) {
            Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show();
        } else if (!email.matches(Validate_Class.emailPattern)) {
            Toast.makeText(this, "Enter valid email", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(this, "The password must be at least 6 characters", Toast.LENGTH_SHORT).show();
        } else {

            //// calling the UserLogIn function with parameter phone and password

            UserLogIn(email, password);

        }

    }

    public void UserLogIn(final String email, final String password) {

        ApiInterface loginApiInterface = RetrofitClient.getFormData().create(ApiInterface.class);


        //// Passing the form field using MultiPartBody

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .build();




        loginApiInterface.postLoginData(requestBody).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String succes = jsonObject.optString("success");
                        String message = jsonObject.optString("message");

                        if (succes.equals("1")) {

                            startActivity(new Intent(getApplicationContext(), College_Display.class));
                            Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                        } else if(succes.equals("01")) {
                            Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                        }
                        else{

                            Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
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
