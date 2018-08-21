package com.android.jsonregistercheck.collegeinfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.jsonregistercheck.R;
import com.android.jsonregistercheck.model.ProgramClass;
import com.android.jsonregistercheck.model.StaffClass;

import java.util.List;

/**
 * Created by user on 8/15/2018.
 */

public class StaffAdapter  extends RecyclerView.Adapter<StaffAdapter.ViewHolder>{

    Context context;
    List<StaffClass> models;


    public StaffAdapter(Context context, List<StaffClass> models) {
        this.context = context;//this. method is used to clear the variable between which takes the data and send the data having the same variable name;
        this.models = models;
    }

    @NonNull
    @Override
    public StaffAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staffadapter,parent,false);
        return  new StaffAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffAdapter.ViewHolder holder, int position) {
        final StaffClass model=models.get(position);
        holder.texttitle.setText(model.getStaff());

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
