package com.example.sujit.celeganceapp.ContestantData;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.sujit.celeganceapp.R;

/**
 * Created by sujit on 2/28/2018.
 */

public class ContestHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView name, phone, call, reg,branch;
    CheckBox checkBox;
    CardView cardView;
    Participant participant;




    int type;
    public ContestHolder(View itemView, final Participant participant, final int type) {
        super(itemView);
        this.type =type;
        this.participant = participant;
        name= (TextView)itemView.findViewById(R.id.name);
        reg= (TextView)itemView.findViewById(R.id.reg);
        branch= (TextView)itemView.findViewById(R.id.branch);
        phone = (TextView)itemView.findViewById(R.id.phone);
        call = (TextView)itemView.findViewById(R.id.call);
        checkBox = (CheckBox)itemView.findViewById(R.id.checkBox);
        cardView =itemView.findViewById(R.id.cardView);
        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                participant.onLongClick(type);
                return true;
            }
        });
        checkBox.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        participant.prepareSelection(view,getAdapterPosition(),type);
    }
}
