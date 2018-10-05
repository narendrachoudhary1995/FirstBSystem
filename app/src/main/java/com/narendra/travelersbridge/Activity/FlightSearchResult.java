package com.narendra.travelersbridge.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.narendra.travelersbridge.Adapater.OneWaySearch;
import com.narendra.travelersbridge.POJO.Result;
import com.narendra.travelersbridge.POJO.SearchLocation;
import com.narendra.travelersbridge.R;
import com.narendra.travelersbridge.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightSearchResult extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView nonStopOnly;
    private ImageView calenderOpen;
    private HorizontalCalendar horizontalCalendar;
    private RecyclerView recyclerView;
    private TextView ToolbarOrigin, ToolbarDestination, ToolbarPassenger, ToolbarDate;
    public static List<List<Result>> results;
    private ProgressDialog progressDialog;
    private TextView NoFlightFound;
    OneWaySearch oneWaySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search_result);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Flight Search Result");
        ToolbarOrigin = toolbar.findViewById(R.id.toolbarorigin);
        ToolbarDestination = toolbar.findViewById(R.id.toolbardestination);
        ToolbarPassenger = toolbar.findViewById(R.id.toolbarpassenger);
        ToolbarDate = toolbar.findViewById(R.id.toolbardate);
        setSupportActionBar(toolbar);
        Intent fromOneWay = getIntent();
        int numberofPassenger = Integer.parseInt(fromOneWay.getStringExtra("adults")) + Integer.parseInt(fromOneWay.getStringExtra("Infants")) + Integer.parseInt(fromOneWay.getStringExtra("children"));
        ToolbarOrigin.setText(fromOneWay.getStringExtra("originfullName"));
        ToolbarDestination.setText(fromOneWay.getStringExtra("destinationfullname"));
        ToolbarDate.setText(fromOneWay.getStringExtra("dateSelectedFromOneWay"));
        ToolbarPassenger.setText(String.valueOf(numberofPassenger) + " Passengers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 2);
        nonStopOnly = findViewById(R.id.non_stop_only);
        calenderOpen = findViewById(R.id.calendar_direct_open);
        recyclerView = findViewById(R.id.one_way_search_flight_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(FlightSearchResult.this));
        results = new ArrayList<>();
        progressDialog = new ProgressDialog(FlightSearchResult.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        NoFlightFound = findViewById(R.id.noflightfound);
        NoFlightFound.setVisibility(View.GONE);
        oneWaySearch = new OneWaySearch(FlightSearchResult.this, results);
        Call<SearchLocation> call = RetrofitClient.getApi().searchResult(SearchFlight.userId.toString(), fromOneWay.getStringExtra("origin").toString(), fromOneWay.getStringExtra("destination").toString(),
                fromOneWay.getStringExtra("startDate"), fromOneWay.getStringExtra("domesticOrIntern"), "OneWay", fromOneWay.getStringExtra("adults"), fromOneWay.getStringExtra("children"), fromOneWay.getStringExtra("Infants"), fromOneWay.getStringExtra("class"), "");
        call.enqueue(new Callback<SearchLocation>() {
            @Override
            public void onResponse(Call<SearchLocation> call, Response<SearchLocation> response) {
                if (response.body().getResponseCode().toString().equals("200")) {
                    results.clear();
                    if (!response.body().getSearchResult().getResults().isEmpty()) {
                        recyclerView.setVisibility(View.VISIBLE);
                        NoFlightFound.setVisibility(View.GONE);
                        results.addAll(response.body().getSearchResult().getResults());
                        oneWaySearch.notifyDataSetChanged();
                        recyclerView.setAdapter(oneWaySearch);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        NoFlightFound.setVisibility(View.VISIBLE);
                    }
                } else if (response.body().getResponseMessage().toString().equals("No Result Found")) {
                    recyclerView.setVisibility(View.GONE);
                    NoFlightFound.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(FlightSearchResult.this, response.body().getResponseMessage(), Toast.LENGTH_LONG).show();
                    /* startActivity(new Intent(FlightSearchResult.this,SearchFlight.class));*/
                    onBackPressed();
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<SearchLocation> call, Throwable t) {
                Toast.makeText(FlightSearchResult.this, t.toString(), Toast.LENGTH_SHORT).show();
                onBackPressed();

            }
        });
        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calrndar_view).range(startDate, endDate).build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Toast.makeText(FlightSearchResult.this, date + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });
      /*  nonStopOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                Intent j = new Intent(FlightSearchResult.this, PassengerDetails.class);
                j.putExtra("adults", i.getStringExtra("adults"));
                j.putExtra("children", i.getStringExtra("children"));
                j.putExtra("Infants", i.getStringExtra("Infants"));
                startActivity(j);
            }
        });*/
        calenderOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year, month, day;
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(FlightSearchResult.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
    }

    //toolbar press back arrow button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
