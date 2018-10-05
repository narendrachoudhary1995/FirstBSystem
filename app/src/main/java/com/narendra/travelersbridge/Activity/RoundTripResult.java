package com.narendra.travelersbridge.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.narendra.travelersbridge.Adapater.RoundTripDepartAdapter;
import com.narendra.travelersbridge.Adapater.RoundTripReturnAdapter;
import com.narendra.travelersbridge.POJO.Result;
import com.narendra.travelersbridge.POJO.SearchLocation;
import com.narendra.travelersbridge.R;
import com.narendra.travelersbridge.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoundTripResult extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView DepartureRecycleView,ReturnRecyclerView;
    private RoundTripDepartAdapter roundTripDepartAdapter;
    private RoundTripReturnAdapter roundTripReturnAdapter;
    private List<List<Result>> departureList,ReturnList;
    private ProgressDialog progressDialog;
    private TextView ToolbarDestination,ToolbarOrigin,ToolbarPassenger,ToolbarDate;
    public static TextView TopbarDeaprturePrice,TopbarReturnPrice,TopbarTotalPrice;
    private TextView DepartureDataPlace,ReturnDatePlace;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_trip_result);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DepartureRecycleView=findViewById(R.id.departure_recycler_view);
        ReturnRecyclerView=findViewById(R.id.return_recycler_view);
        departureList=new ArrayList<>();
        ReturnList=new ArrayList<>();
        ToolbarDestination=findViewById(R.id.toolbar_destination);
        ToolbarOrigin=findViewById(R.id.toolbar_origin);
        ToolbarPassenger=findViewById(R.id.toolbar_total_passenger);
        ToolbarDate=findViewById(R.id.toolbar_date);
        TopbarDeaprturePrice=findViewById(R.id.topbar_depart_price);
        TopbarReturnPrice=findViewById(R.id.topbar_return_price);
        TopbarTotalPrice=findViewById(R.id.topbar_total_price);
        DepartureDataPlace=findViewById(R.id.departure_date_place);
        ReturnDatePlace=findViewById(R.id.return_date_place);
        i=getIntent();
        ToolbarDestination.setText(i.getStringExtra("destinationFull").toString());
        ToolbarOrigin.setText(i.getStringExtra("originFull").toString());
        String TotalNumberPassengers=String.valueOf(Integer.valueOf(i.getStringExtra("adults").toString())
                +Integer.valueOf(i.getStringExtra("infants").toString())+Integer.valueOf(i.getStringExtra("children").toString()));
        ToolbarPassenger.setText(TotalNumberPassengers.toString()+" Passengers");
        TotalNumberPassengers=i.getStringExtra("originShort")+"-"+i.getStringExtra("destinationShort")
                +" "+i.getStringExtra("dateFromShort");
        DepartureDataPlace.setText(TotalNumberPassengers.toString());
        TotalNumberPassengers=i.getStringExtra("destinationShort")+"-"+i.getStringExtra("originShort")
                +" "+i.getStringExtra("dateToShort");
        ReturnDatePlace.setText(TotalNumberPassengers.toString());
        progressDialog=new ProgressDialog(RoundTripResult.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        DepartureRecycleView.setLayoutManager(new LinearLayoutManager(RoundTripResult.this));
        ReturnRecyclerView.setLayoutManager(new LinearLayoutManager(RoundTripResult.this));
        roundTripDepartAdapter=new RoundTripDepartAdapter(RoundTripResult.this,departureList);
        roundTripReturnAdapter=new RoundTripReturnAdapter(RoundTripResult.this,ReturnList);
        progressDialog.show();
        String origin="["+i.getStringExtra("originShort")+"] - "+i.getStringExtra("originFull");
        String destination="["+i.getStringExtra("destinationShort")+"] - "+i.getStringExtra("destinationFull");
        Call<SearchLocation> depart= RetrofitClient.getApi().
                searchResult(SearchFlight.userId,origin.toString(),destination.toString(),
              i.getStringExtra("dateFrom").toString(),i.getStringExtra("domesticOrInternational").toString(),
              "OneWay",i.getStringExtra("adults").toString(),
                        i.getStringExtra("children").toString(),i.getStringExtra("infants").toString(),
                        i.getStringExtra("class"),"");
        depart.enqueue(new Callback<SearchLocation>() {
            @Override
            public void onResponse(Call<SearchLocation> call, Response<SearchLocation> response) {
                departureList.clear();
                if(response.body().getResponseCode().equals("200"))
                {departureList.addAll(response.body().getSearchResult().getResults());
                roundTripDepartAdapter.notifyDataSetChanged();
                DepartureRecycleView.setAdapter(roundTripDepartAdapter);
                    String s=String.valueOf(departureList.get(0).get(0).getFare().getBaseFare()+departureList.get(0).get(0).getFare().getTax()+150);
                    TopbarDeaprturePrice.setText(s);
                }
                else
                    Toast.makeText(RoundTripResult.this, response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SearchLocation> call, Throwable t) {
                Toast.makeText(RoundTripResult.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        Call<SearchLocation> returnResult=RetrofitClient.getApi().searchResult(SearchFlight.userId,destination.toString()
                ,origin.toString(),i.getStringExtra("dateTo").toString(),i.getStringExtra("domesticOrInternational").toString(),
                "OneWay",i.getStringExtra("adults").toString(),
                i.getStringExtra("children").toString(),i.getStringExtra("infants").toString(),
                i.getStringExtra("class"),"");
        returnResult.enqueue(new Callback<SearchLocation>() {
            @Override
            public void onResponse(Call<SearchLocation> call, Response<SearchLocation> response) {
                if(response.body().getResponseCode().equals("200")) {
                    ReturnList.clear();
                    ReturnList.addAll(response.body().getSearchResult().getResults());
                    roundTripReturnAdapter.notifyDataSetChanged();
                    ReturnRecyclerView.setAdapter(roundTripReturnAdapter);
                    String s=String.valueOf(ReturnList.get(0).get(0).getFare().getBaseFare()+ReturnList.get(0).get(0).getFare().getTax()+150);
                    TopbarReturnPrice.setText(s);
                    TopbarTotalPrice.setText("₹"+String.valueOf(Double.valueOf(TopbarDeaprturePrice.getText().toString())
                            +Double.valueOf(TopbarReturnPrice.getText().toString())));
                }
                else
                    Toast.makeText(RoundTripResult.this, response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<SearchLocation> call, Throwable t) {
                Toast.makeText(RoundTripResult.this, t.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
