package com.android.jsonregistercheck.collegeinfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.model.College_Aboutus;

import java.util.List;

/**
 * Created by user on 8/11/2018.
 */

public class Aboutusadapter  extends RecyclerView.Adapter<Aboutusadapter.ViewHolder>{

    Context context;
    List<College_Aboutus> models;


    public Aboutusadapter(Context context, List<College_Aboutus> models) {
        this.context = context;//this. method is used to clear the variable between which takes the data and send the data having the same variable name;
        this.models = models;
    }

    @NonNull
    @Override
    public Aboutusadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aboutadapter,parent,false);
        return  new Aboutusadapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Aboutusadapter.ViewHolder holder, int position) {
        final College_Aboutus model=models.get(position);
        holder.texttitle.setText(model.getTitle());
        holder.textdesc.setText(model.getDescription());



    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{//Here recyclerview.viewholder is the parent class

        TextView texttitle,textdesc;

        public ViewHolder(View itemView) {
            super(itemView);

            texttitle= (TextView) itemView.findViewById(R.id.titl);
            textdesc= (TextView) itemView.findViewById(R.id.descrip);

        }
    }
}
