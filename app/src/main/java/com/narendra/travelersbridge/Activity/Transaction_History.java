package com.narendra.travelersbridge.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.narendra.travelersbridge.Adapater.TransactionHistoryAdapter;
import com.narendra.travelersbridge.POJO.Ticket;
import com.narendra.travelersbridge.POJO.Transection;
import com.narendra.travelersbridge.R;
import com.narendra.travelersbridge.Retrofit.RetrofitClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Transaction_History extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText SearchEditText;
    private RecyclerView transactionContainer;
    private TransactionHistoryAdapter transactionHistoryAdapter;
    private List<Ticket> list;
    private TextView NoDataFound,StartDateText,ToDateText;
    private String StartDate,ToDate;
    private LinearLayout FromDateLayout,ToDateLayout,SearchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction__history);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("TRANSACTION HISTORY");
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StartDateText=findViewById(R.id.from_date_text);
        ToDateText=findViewById(R.id.to_date_text);
        FromDateLayout =findViewById(R.id.from_date);
        ToDateLayout=findViewById(R.id.to_date);
        SearchButton=findViewById(R.id.search_button);
        SearchEditText=findViewById(R.id.search_transaction_edit_text);
        list = new ArrayList<>();
        findDateforDefaultTransaction();
        StartDateText.setText(StartDate.toString());
        StartDateText.setText(ToDate.toString());
        NoDataFound=findViewById(R.id.no_data_found);
        NoDataFound.setVisibility(View.GONE);
        transactionContainer = findViewById(R.id.transaction_recycler_view);
        transactionContainer.setLayoutManager(new LinearLayoutManager(Transaction_History.this));
        transactionHistoryAdapter = new TransactionHistoryAdapter(list, Transaction_History.this);
        Call<Transection> call = RetrofitClient.getApi().transactionHistory("3", "06-01-2018", "07-06-2018");
        call.enqueue(new Callback<Transection>() {
            @Override
            public void onResponse(Call<Transection> call, Response<Transection> response) {
                if (response.body().getResponseCode().toString().equals("200")) {
                    if(!response.body().getTickets().isEmpty()) {
                        NoDataFound.setVisibility(View.GONE);
                        list.clear();
                        list.addAll(response.body().getTickets());
                        transactionHistoryAdapter.notifyDataSetChanged();
                        transactionContainer.setAdapter(transactionHistoryAdapter);
                    }
                    else
                    {
                        transactionContainer.setVisibility(View.GONE);
                        NoDataFound.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onFailure(Call<Transection> call, Throwable t) {
                Toast.makeText(Transaction_History.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
        FromDateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year,month,day;
                Calendar calendar = Calendar.getInstance();
                try {
                    java.util.Date date=new SimpleDateFormat("dd/MM/YYYY").parse(StartDateText.getText().toString());
                    calendar.setTime(date);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(Transaction_History.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        StartDateText.setText(String.valueOf(dayOfMonth)+"-"+String.valueOf(month+1)+"-"+String.valueOf(year));
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        ToDateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int year,month,day;
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(Transaction_History.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ToDateText.setText(String.valueOf(dayOfMonth)+"-"+String.valueOf(month+1)+"-"+String.valueOf(year));
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        SearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Ticket> list1=new ArrayList<>();
                for(Ticket item:list)
                {
                    if(item.getTransactionId().toLowerCase().contains(s.toString().toLowerCase()))
                    {
                        list1.add(item);
                    }
                }
                TransactionHistoryAdapter transactionHistoryAdapter1 = new TransactionHistoryAdapter(list1, Transaction_History.this);
                transactionHistoryAdapter1.notifyDataSetChanged();
                transactionContainer.setAdapter(transactionHistoryAdapter1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void findDateforDefaultTransaction() {
        java.util.Date today =new java.util.Date();
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-30);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-YYYY");
        StartDate=simpleDateFormat.format(calendar.getTimeInMillis()).toString();
        calendar.setTime(today);
        ToDate=simpleDateFormat.format(calendar.getTimeInMillis());
        String s;
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
