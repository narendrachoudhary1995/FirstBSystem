package com.narendra.travelersbridge.Adapater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.narendra.travelersbridge.POJO.Ticket;
import com.narendra.travelersbridge.R;

import java.util.List;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.PostViewHolder> {
List<Ticket> ticketList;
Context context;

    public TransactionHistoryAdapter(List<Ticket> ticketList, Context context) {
        this.ticketList = ticketList;
        this.context = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.transaction_history_item,parent,false);
       return new PostViewHolder(view);

    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Ticket ticket=ticketList.get(position);
        holder.SerialNo.setText(String.valueOf(ticket.getId()));
        holder.Type.setText(ticket.getType().toString());
        holder.TransactionId.setText(ticket.getTransactionId().toString());
        holder.Date.setText(ticket.getReportsNew().getCreatedAt().toString());
        holder.FlightName.setText(ticket.getFlightName());
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder
    {
        TextView SerialNo,Date,TransactionId,FlightName,Type;
        public PostViewHolder(View itemView) {
            super(itemView);
            SerialNo=itemView.findViewById(R.id.transaction_sno);
            Date=itemView.findViewById(R.id.transaction_date);
            TransactionId=itemView.findViewById(R.id.transaction_id);
            FlightName=itemView.findViewById(R.id.transaction_airway_name);
            Type=itemView.findViewById(R.id.transaction_type);
        }
    }
}
