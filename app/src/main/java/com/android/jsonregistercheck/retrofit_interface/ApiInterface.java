package com.android.jsonregistercheck.retrofit_interface;

import com.android.jsonregistercheck.model.College_Aboutus;
import com.android.jsonregistercheck.model.College_list;
import com.android.jsonregistercheck.model.ContactClass;
import com.android.jsonregistercheck.model.FaqClass;
import com.android.jsonregistercheck.model.ProgramClass;
import com.android.jsonregistercheck.model.Review_withdetails;
import com.android.jsonregistercheck.model.Searchmodel;
import com.android.jsonregistercheck.model.StaffClass;
import com.android.jsonregistercheck.model.UserDataModel;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by user on 8/6/2018.
 */

public interface ApiInterface {

    @POST("practiceone.php")
    Call<ResponseBody> submitData (@Body RequestBody body);

    @GET("usercheck.php")
    Call<UserDataModel>getUserProfileData(@Query("username") String usernames,@Query("password") String passwords);


    @GET("collegelist.php")
    Call<List<College_list>> getMenu();

    @POST("login.php")
    Call<ResponseBody> postLoginData(@Body RequestBody body);

    @GET("get_information.php")
    Call<List<College_Aboutus>> getdetails(@Query("college_id")String collegeid);


    @POST("postreview.php")
    Call<ResponseBody> postReview(@Body RequestBody body);

    @GET("get_review.php")
    Call<List<Review_withdetails>> getReview(@Query("college_id") String menu_items_id);

    @GET("searchfinal.php")
    Call<List<Searchmodel>> getresult(@Query("name") String name, @Query("location") String location, @Query("hitno") String popularity, @Query("bias") String quality);

    @POST("collegehitno.php")
    Call<ResponseBody> postinterested(@Body RequestBody body);

    @GET("getprogram.php")
    Call<List<ProgramClass>> getprogram(@Query("college_id")String collegeid);

    @GET("getstaff.php")
    Call<List<StaffClass>> getstaff(@Query("college_id")String collegeid);

    @GET("getcontact.php")
    Call<List<ContactClass>> getcontact(@Query("college_id")String collegeid);

    @GET("getfaq.php")
    Call<List<FaqClass>> getfaq(@Query("college_id")String collegeid);

    @POST("login.php")
    Call<ResponseBody> postregistercollege(@Body RequestBody body);



}
