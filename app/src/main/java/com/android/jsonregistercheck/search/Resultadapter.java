package com.android.jsonregistercheck.search;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AndroidRuntimeException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.collegeinfo.CollegeAdapter;
import com.android.jsonregistercheck.collegeinfo.College_Info;
import com.android.jsonregistercheck.model.College_list;
import com.android.jsonregistercheck.model.Searchmodel;

import java.util.List;

/**
 * Created by user on 8/14/2018.
 */

public class Resultadapter extends RecyclerView.Adapter<Resultadapter.ViewHolder> {
    Context context;
    List<Searchmodel> models;

        public Resultadapter(Context context, List<Searchmodel> models) {
            this.context = context;//this. method is used to clear the variable between which takes the data and send the data having the same variable name;
            this.models = models;
        }

    @NonNull
    @Override
    public Resultadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resultadapter,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Resultadapter.ViewHolder holder, int position) {
        final Searchmodel model=models.get(position);
        holder.textname.setText(model.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    Intent intent = new Intent(context,College_Info.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id",model.getId());
                    intent.putExtra("college_name",model.getName());


                    context.startActivity(intent);
                }catch (AndroidRuntimeException e){
                    e.printStackTrace();
                }


                Toast.makeText(context, "You clicked "+model.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{//Here recyclerview.viewholder is the parent class

        TextView textname;

        public ViewHolder(View itemView) {
            super(itemView);

            textname= (TextView) itemView.findViewById(R.id.name);


        }
    }
}
