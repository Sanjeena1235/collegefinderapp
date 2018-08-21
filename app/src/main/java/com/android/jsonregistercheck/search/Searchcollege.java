package com.android.jsonregistercheck.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.android.jsonregistercheck.MainActivity;
import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.authentication.Register;
import com.android.jsonregistercheck.collegeinfo.College_Info;
import com.android.jsonregistercheck.model.Searchmodel;

public class Searchcollege extends AppCompatActivity {

     EditText collegename,collegelocation;
     TextView popular,Quality;
     CheckBox high1,high2,medium1,medium2,low1,low2,checkcolz,checklocation;
     Button submit;
     String name,location,popularity,quality;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchcollege);

        collegename=(EditText)findViewById(R.id.editText2);
        collegelocation=(EditText)findViewById(R.id.editText3);
         popular=(TextView)findViewById(R.id.textView4);
         Quality=(TextView)findViewById(R.id.textView5);
         checkcolz=(CheckBox)findViewById(R.id.checkBox);
         checklocation=(CheckBox)findViewById(R.id.checkBox2);
         high1=(CheckBox)findViewById(R.id.checkBox3);
         high2=(CheckBox)findViewById(R.id.checkBox6);
         medium1=(CheckBox)findViewById(R.id.checkBox4);
         medium2=(CheckBox)findViewById(R.id.checkBox7);
         low1=(CheckBox)findViewById(R.id.checkBox5);
         low2=(CheckBox)findViewById(R.id.checkBox8);
         submit=(Button)findViewById(R.id.button);

         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 input();

             }
         });


    }
    public void input(){

        if(checkcolz.isChecked()){
            name="";

        }
        else{
            name = collegename.getText().toString();
        }
        if(checklocation.isChecked()){
            location="";
        }
        else{
            location=collegelocation.getText().toString();
        }
        if(high1.isChecked()){
            popularity="high";

        }else if(medium1.isChecked()){
            popularity="medium";
        }else if(low1.isChecked()){
            popularity="low";
        }else{
            popularity="";
        }
        if(high2.isChecked()){
            quality="high";
        }else if(medium2.isChecked()){
            quality="medium";
        }else if(low2.isChecked()){
            quality="low";
        }else{
            quality="";
        }

        search(name,location,popularity,quality);

    }
    public void  search(String name,String location,String popularity,String quality){
        try{
            Intent intent =new Intent(Searchcollege.this,Result.class);

            intent.putExtra("name",name);
            intent.putExtra("location",location);
            intent.putExtra("popularity",popularity);
            intent.putExtra("quality",quality);
            startActivity(intent);
        }catch (AndroidRuntimeException e){
            e.printStackTrace();
        }

    }


}





