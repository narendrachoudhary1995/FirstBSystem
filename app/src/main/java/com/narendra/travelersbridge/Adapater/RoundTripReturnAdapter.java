package com.narendra.travelersbridge.Adapater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.narendra.travelersbridge.Activity.RoundTripResult;
import com.narendra.travelersbridge.POJO.Result;
import com.narendra.travelersbridge.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RoundTripReturnAdapter extends RecyclerView.Adapter<RoundTripReturnAdapter.ViewHolder> {

    private Context context;
    private List<List<Result>> resultList;

    public RoundTripReturnAdapter(Context context, List<List<Result>> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @Override
    public RoundTripReturnAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.round_trip_return_item, parent, false);
        return new RoundTripReturnAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RoundTripReturnAdapter.ViewHolder holder, final int position) {
        final List<Result> results = resultList.get(0);
        String imageUrl = "http://traveller.projectnyou.com/public/images/flight/flight_logo/" + results.get(position).getSegments().get(0).get(0).getAirline().getAirlineCode() + ".png";
        holder.FlightNumber.setText(results.get(position).getSegments().get(0).get(0).getAirline().getAirlineCode().toString().concat("-" + results.get(position).getSegments().get(0).get(0).getAirline().getFlightNumber().toString()));
        String s = results.get(position).getSegments().get(0).get(0).getOrigin().getDepTime().toString();
        s = s.substring(s.indexOf("T") + 1);
        s=s.substring(0,s.indexOf(":00"));
        holder.DepartTime.setText(s.toString());
        s = results.get(position).getSegments().get(0).get(0).getDestination().getArrTime().toString();
        s = s.substring(s.indexOf("T") + 1);
        s=s.substring(0,s.indexOf(":00"));
        holder.ReachTime.setText(s.toString());
        s = results.get(position).getSegments().get(0).get(0).getDuration().toString();
        if (Integer.valueOf(s) < 60)
            holder.Duration.setText(s.toString().concat("m"));
        else {
            int h = Integer.valueOf(s) / 60;
            int m = Integer.valueOf(s) % 60;
            if (m == 0)
                holder.Duration.setText(String.valueOf(h).concat("h"));
            else
                holder.Duration.setText(String.valueOf(h).concat("h:").concat(String.valueOf(m).concat("m")));
        }
        if (results.get(position).getSegments().get(0).get(0).getStopOver())
            holder.StopNonStop.setText("Stop");
        else
            holder.StopNonStop.setText("Non Stop");
        s=String.valueOf(results.get(position).getFare().getBaseFare()+results.get(position).getFare().getTax()+150);
        holder.Price.setText("₹ "+s);
        Picasso.with(context).load(imageUrl).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=String.valueOf(results.get(position).getFare().getBaseFare()+results.get(position).getFare().getTax()+150);
                RoundTripResult.TopbarReturnPrice.setText(s);
                RoundTripResult.TopbarTotalPrice.setText("₹"+String.valueOf(Double.valueOf(RoundTripResult.TopbarDeaprturePrice.getText().toString())
                        +Double.valueOf(RoundTripResult.TopbarReturnPrice.getText().toString())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultList.get(0).size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView Price, FlightNumber, PaidMeal, DepartTime, ReachTime, Duration, StopNonStop;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.flight_icon_return_round_trip);
            Price = itemView.findViewById(R.id.price_return_round_trip);
            FlightNumber = itemView.findViewById(R.id.flight_number_return_round_trip);
            PaidMeal = itemView.findViewById(R.id.paid_meal_return_round_trip);
            DepartTime = itemView.findViewById(R.id.depart_time_return_round_trip);
            ReachTime = itemView.findViewById(R.id.reach_time_return_round_trip);
            Duration = itemView.findViewById(R.id.duration_return_round_trip);
            StopNonStop = itemView.findViewById(R.id.stop_or_not_return_round_trip);
        }
    }
}
