package com.android.jsonregistercheck.collegeinfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jsonregistercheck.MainActivity;
import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.authentication.Login;
import com.android.jsonregistercheck.authentication.Register;
import com.android.jsonregistercheck.authentication.UserProfileData;
import com.android.jsonregistercheck.dark_statusbar.DarkStatusBar;
import com.android.jsonregistercheck.list_expandable_height.ExpandableListViewHeight;
import com.android.jsonregistercheck.model.College_Aboutus;
import com.android.jsonregistercheck.model.ReviewClass;
import com.android.jsonregistercheck.model.Review_withdetails;
import com.android.jsonregistercheck.retrofit_apiclient.RetrofitClient;
import com.android.jsonregistercheck.retrofit_interface.ApiInterface;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class College_Info extends AppCompatActivity {
    ExpandableListViewHeight listView;
    Toolbar toolbar;
    TextView toolbar_title, txt_comments_count, txt_no_review;
    ImageView item_image;
    EditText edit_comment;
    RelativeLayout post_review;
    String review, menu_items_id,number;
    int hit;
    Button btn_about;
    String image,userid,username;
    SharedPreferences sharedPreferences;
    UserProfileData userData;
    Button hitbtn,program,faq,staff,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college__info);

        listView = findViewById(R.id.review_list);
        toolbar = findViewById(R.id.tool_bar);
        item_image = findViewById(R.id.item_image);
        edit_comment = findViewById(R.id.edit_comment);
        txt_comments_count = findViewById(R.id.txt_comments);
        post_review = findViewById(R.id.post_review);
        btn_about=findViewById(R.id.about_us);
        hitbtn=findViewById(R.id.interest);
        program=findViewById(R.id.program);
        faq=findViewById(R.id.faq);
        contact=findViewById(R.id.contact);
        staff=findViewById(R.id.staff);

        toolbar_title = toolbar.findViewById(R.id.toolbar_title);

        menu_items_id = getIntent().getExtras().getString("id");
        final String collegename = getIntent().getExtras().getString("college_name");


        btn_about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                try{
                    Intent intent = new Intent(College_Info.this,Aboutus_Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id",menu_items_id);
                    intent.putExtra("college_name",collegename);



                    startActivity(intent);
                }catch (AndroidRuntimeException e){
                    e.printStackTrace();
                }


                Toast.makeText(College_Info.this,"You clicked "+collegename, Toast.LENGTH_SHORT).show();
            }
        });
        program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(College_Info.this,ProgramActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id",menu_items_id);
                    intent.putExtra("college_name",collegename);



                    startActivity(intent);
                }catch (AndroidRuntimeException e){
                    e.printStackTrace();
                }


                Toast.makeText(College_Info.this,"You clicked "+collegename, Toast.LENGTH_SHORT).show();


            }
        });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(College_Info.this,FaqActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id",menu_items_id);
                    intent.putExtra("college_name",collegename);



                    startActivity(intent);
                }catch (AndroidRuntimeException e){
                    e.printStackTrace();
                }


                Toast.makeText(College_Info.this,"You clicked "+collegename, Toast.LENGTH_SHORT).show();

            }
        });
     contact.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             try{
                 Intent intent = new Intent(College_Info.this,ContactActivity.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 intent.putExtra("id",menu_items_id);
                 intent.putExtra("college_name",collegename);



                 startActivity(intent);
             }catch (AndroidRuntimeException e){
                 e.printStackTrace();
             }


             Toast.makeText(College_Info.this,"You clicked "+collegename, Toast.LENGTH_SHORT).show();

         }
     });
    staff.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try{
                Intent intent = new Intent(College_Info.this,StaffActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",menu_items_id);
                intent.putExtra("college_name",collegename);



                startActivity(intent);
            }catch (AndroidRuntimeException e){
                e.printStackTrace();
            }


            Toast.makeText(College_Info.this,"You clicked "+collegename, Toast.LENGTH_SHORT).show();

        }
    });
        toolbar_title.setText(collegename);


        Window window = getWindow();
        View view = window.getDecorView();
        DarkStatusBar.setLightStatusBar(view, this);

        listView.setFocusable(false);

        post_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               reviewEdit();
            }
        });
        hitbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hit = 1;
                        number = Integer.toString(hit);
                        postinterest(number,menu_items_id);
                    }
        });

        getReviews(menu_items_id);




    }


    public void postinterest(String hit,final String Colzid) {

        ApiInterface postint = RetrofitClient.getFormData().create(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("hitno", hit)
                .addFormDataPart("id", Colzid)
                .build();
        postint.postinterested(requestBody).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){

                    try {


                        Toast.makeText(College_Info.this, "Response:"+response.body().string(), Toast.LENGTH_LONG).show();
                        JSONObject jsonObject = new JSONObject(response.body().string());

                        String succes = jsonObject.optString("success");
                        String message = jsonObject.optString("message");

                        if (succes.equals("0")){
                            Toast.makeText(College_Info.this, message, Toast.LENGTH_SHORT).show();
                        }else if (succes.equals("1")){


                            Toast.makeText(College_Info.this, message, Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(College_Info.this, message, Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    catch (NullPointerException ex) {
                        ex.printStackTrace();
                    }

                    catch (IOException e) {
                        e.printStackTrace();
                    }


                }else{
                    System.out.println("Error : "+String .valueOf(response.errorBody()));
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public void getReviews(String menu_items_id){


        ApiInterface menuItemApiInterface = RetrofitClient.getFormData().create(ApiInterface.class);



        Call<List<Review_withdetails>> model = menuItemApiInterface.getReview(menu_items_id);
         model.enqueue(new Callback<List<Review_withdetails>>() {
             @Override
             public void onResponse(Call<List<Review_withdetails>> call, Response<List<Review_withdetails>> response) {
                 List<Review_withdetails> reviews= response.body();
                 Reviewadapter recyclerviewadapter = new Reviewadapter(College_Info.this, reviews);
                 //Recyclerviewadapter recyclerviewadapter1 =new Recyclerviewadapter(MainActivity.this,response.body());
                 RecyclerView.LayoutManager layoutManager = new GridLayoutManager(College_Info.this, 1);
                 //  LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(College_Display.this, LinearLayoutManager.HORIZONTAL, false);
                 //recyclerView.setLayoutManager(horizontalLayoutManager);
                 listView.setAdapter(recyclerviewadapter);
                 listView.setExpanded(true);

                 recyclerviewadapter.notifyDataSetChanged();
             }

             @Override
             public void onFailure(Call<List<Review_withdetails>> call, Throwable t) {

             }


         });

   }

    public void reviewEdit(){

        review = edit_comment.getText().toString();

        if (review.equals("")){
            Toast.makeText(this, "Please write some review", Toast.LENGTH_SHORT).show();
       }else {
            userData = new UserProfileData(College_Info.this);
            //// retrive data from shared preference //////


            sharedPreferences  = getSharedPreferences("UserDetail", Context.MODE_PRIVATE);
            image = sharedPreferences.getString("image",null);
            username = sharedPreferences.getString("username","");
            userid = sharedPreferences.getString("id","");
            Toast.makeText(College_Info.this, username, Toast.LENGTH_SHORT).show();


            if (!username.equals("") && !userid.equals("")){
                reviewPost(menu_items_id,userid,username,review,image);
                edit_comment.setText("");
            }






        }

    }
   public void reviewPost(final String collegeid, String  userid, String username, String review, String image){

        ApiInterface postReviewApi = RetrofitClient.getFormData().create(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("college_id",collegeid)
                .addFormDataPart("user_id",userid)
                .addFormDataPart("username",username)
                .addFormDataPart("review",review)
               .addFormDataPart("image",image)


                .build();



        postReviewApi.postReview(requestBody).enqueue(new Callback<ResponseBody>() {
           @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

               if (response.isSuccessful()){

                   try {


                       //Toast.makeText(College_Info.this, "Response:"+response.body().string(), Toast.LENGTH_LONG).show();
                       JSONObject jsonObject = new JSONObject(response.body().string());

                       String succes = jsonObject.optString("success");
                       String message = jsonObject.optString("message");

                       if (succes.equals("10")){
                           Toast.makeText(College_Info.this, message, Toast.LENGTH_SHORT).show();
                       }else if (succes.equals("1")){


                           Toast.makeText(College_Info.this, message, Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),College_Display.class));
                       }else {
                           Toast.makeText(College_Info.this, message, Toast.LENGTH_SHORT).show();
                       }

                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
                   catch (NullPointerException ex) {
                       ex.printStackTrace();
                   }

                   catch (IOException e) {
                       e.printStackTrace();
                   }


               }else{
                   System.out.println("Error : "+String .valueOf(response.errorBody()));
               }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
    public void backArrow(View view) {
        finish();
    }
}

