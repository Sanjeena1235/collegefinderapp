package com.android.jsonregistercheck.collegeinfo;

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

import com.android.jsonregistercheck.collegedetails.College_Details;
import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.model.College_list;

import java.util.List;

/**
 * Created by user on 8/9/2018.
 */

public class CollegeAdapter  extends  RecyclerView.Adapter<CollegeAdapter.ViewHolder> {
    Context context;
    List<College_list> models;

    public CollegeAdapter(Context context, List<College_list> models) {
        this.context = context;//this. method is used to clear the variable between which takes the data and send the data having the same variable name;
        this.models = models;
    }

    @NonNull
    @Override
    public CollegeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view,parent,false);
        return  new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CollegeAdapter.ViewHolder holder, int position) {
        final College_list model=models.get(position);
        holder.textname.setText(model.getName());
        holder.textlinks.setText(model.getLinks());

        //Glide.with(context).load("http://vedisapp.berlin-webdesign-agentur.de/Image/"+model.getImage())
        //  .placeholder(R.mipmap.ic_launcher)
        //   .into(holder.image);

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

        TextView textname,textlinks;

        public ViewHolder(View itemView) {
            super(itemView);

            textname= (TextView) itemView.findViewById(R.id.name);
            textlinks= (TextView) itemView.findViewById(R.id.link);

        }
    }
}
