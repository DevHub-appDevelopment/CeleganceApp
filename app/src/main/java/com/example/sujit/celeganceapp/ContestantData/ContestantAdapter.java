package com.example.sujit.celeganceapp.ContestantData;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sujit.celeganceapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sujit on 2/28/2018.
 */

public class ContestantAdapter extends RecyclerView.Adapter<ContestHolder>  {
    boolean visible = false;
    List<ContestantData> data = new ArrayList<ContestantData>();
    Context context;
   Participant participant;


   int type;
    public ContestantAdapter(Context context,List data, int type)
    {
        this.data = data;
        this.context = context;
        participant = (Participant)context;
        this.type =type;

    }

    @Override
    public ContestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=(LayoutInflater.from(parent.getContext())).inflate(R.layout.contestant_layout,parent,false);
        return new ContestHolder(view,participant,type);
    }

    @Override
    public void onBindViewHolder(final ContestHolder holder, final int position) {

        holder.name.setText(data.get(position).getName());
        holder.phone.setText(data.get(position).getPhone());
        holder.branch.setText(data.get(position).getBranch());
        holder.reg.setText(data.get(position).getReg());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:123"));
                context.startActivity(intent);
            }
        });

      if(!participant.action_mode) {

            holder.checkBox.setVisibility(View.GONE);
            holder.call.setVisibility(View.VISIBLE);
        }
        else {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);
            holder.call.setVisibility(View.GONE);
        }




    }


    @Override
    public int getItemCount() {
        return data.size();
    }


}
