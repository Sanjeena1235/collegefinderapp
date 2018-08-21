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
import com.android.jsonregistercheck.model.FaqClass;

import java.util.List;

/**
 * Created by user on 8/15/2018.
 */

public class FaqAdapter  extends RecyclerView.Adapter<FaqAdapter.ViewHolder>{

    Context context;
    List<FaqClass> models;


    public FaqAdapter (Context context, List<FaqClass> models) {
        this.context = context;//this. method is used to clear the variable between which takes the data and send the data having the same variable name;
        this.models = models;
    }

    @NonNull
    @Override
    public FaqAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faqadapter,parent,false);
        return  new FaqAdapter .ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqAdapter.ViewHolder holder, int position) {
        final FaqClass model=models.get(position);
        holder.texttitle.setText(model.getQuestions());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{//Here recyclerview.viewholder is the parent class

        TextView texttitle;

        public ViewHolder(View itemView) {
            super(itemView);

            texttitle= (TextView) itemView.findViewById(R.id.titl);

        }
    }
}
