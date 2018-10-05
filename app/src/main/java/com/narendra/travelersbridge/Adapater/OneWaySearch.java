package com.narendra.travelersbridge.Adapater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.narendra.travelersbridge.Activity.PassengerDetails;
import com.narendra.travelersbridge.POJO.Result;
import com.narendra.travelersbridge.R;
import com.squareup.picasso.Picasso;

import java.util.List;

//fare TotalSeatCharges; farerule-destination arrTime;origin DepTime;Airline airlineName;segment duration; segment stopOver(true/false)
public class OneWaySearch extends RecyclerView.Adapter<OneWaySearch.PostViewHolder> {
    private Context context;
    private List<List<Result>> results;

    public OneWaySearch(Context context,List<List<Result>> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.one_way_search_item,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, final int position) {
        final List<Result> result1=results.get(0);
        holder.flightName.setText(result1.get(position).getSegments().get(0).get(0).getAirline().getAirlineName());
        String s=result1.get(position).getSegments().get(0).get(0).getOrigin().getDepTime();
        s=s.substring(s.indexOf("T")+1);
        s=s.substring(0,s.indexOf(":00"));
        holder.departureTime.setText(s);
        s=result1.get(position).getSegments().get(0).get(0).getDestination().getArrTime();
        s=s.substring(s.indexOf("T")+1);
        s=s.substring(0,s.indexOf(":00"));
        holder.reachTime.setText(s);
        String time=result1.get(position).getSegments().get(0).get(0).getDuration().toString();
        int i=Integer.valueOf(time);
        if(i<60)
            holder.travelTime.setText(String.valueOf(i).concat("m"));
        else
        {
            int h=i/60,m=i%60;
            holder.travelTime.setText(String.valueOf(h).concat("h:").concat(String.valueOf(m).concat("m")));
        }
        if(result1.get(position).getSegments().get(0).get(0).getStopOver())
            holder.stopOrNonStop.setText("Stop");
        else
            holder.stopOrNonStop.setText("Non Stop");
        s=String.valueOf(result1.get(position).getFare().getBaseFare()+result1.get(position).getFare().getTax()+150);
        holder.price.setText("₹ "+s);
        final String imageUrl="http://traveller.projectnyou.com/public/images/flight/flight_logo/"+result1.get(position).getSegments().get(0).get(0).getAirline().getAirlineCode()+".png";
        Picasso.with(context).load(imageUrl).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context, PassengerDetails.class);
                i.putExtra("position",position);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  results.get(0).size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView flightName, departureTime, travelTime, reachTime, stopOrNonStop, price;
        ImageView imageView;
        Context context;
        List<Result> results;
        public PostViewHolder(View itemView) {
            super(itemView);
            flightName = itemView.findViewById(R.id.flight_name_one_way);
            departureTime = itemView.findViewById(R.id.depart_time_one_way);
            travelTime = itemView.findViewById(R.id.travel_time_one_way);
            reachTime = itemView.findViewById(R.id.reach_time_one_way);
            stopOrNonStop = itemView.findViewById(R.id.stop_non_stop_one_way);
            price = itemView.findViewById(R.id.price_one_way);
            imageView=itemView.findViewById(R.id.icon_flight);
        }
    }
}
