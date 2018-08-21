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
import com.android.jsonregistercheck.model.ContactClass;

import java.util.List;

/**
 * Created by user on 8/15/2018.
 */

public class ContactAdapter  extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    Context context;
    List<ContactClass> models;


    public ContactAdapter(Context context, List<ContactClass> models) {
        this.context = context;//this. method is used to clear the variable between which takes the data and send the data having the same variable name;
        this.models = models;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactadapter,parent,false);
        return  new ContactAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        final ContactClass model=models.get(position);
        holder.telephone.setText(model.getTel_no());
        holder.postal.setText(model.getPostal_add());
        holder.email.setText(model.getEmail());
        holder.faxno.setText(model.getFax_no());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{//Here recyclerview.viewholder is the parent class

        TextView telephone,postal,email,faxno;

        public ViewHolder(View itemView) {
            super(itemView);

            telephone= (TextView) itemView.findViewById(R.id.tel_no);
            postal= (TextView) itemView.findViewById(R.id.postal_add);
            email= (TextView) itemView.findViewById(R.id.email);
            faxno= (TextView) itemView.findViewById(R.id.fax_no);

        }
    }
}
