package com.narendra.travelersbridge.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.narendra.travelersbridge.POJO.CityState;
import com.narendra.travelersbridge.POJO.Register;
import com.narendra.travelersbridge.R;
import com.narendra.travelersbridge.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProfile extends AppCompatActivity {
    Toolbar toolbar;
    private AutoCompleteTextView UserState, UserCity;
    private EditText Username, UserCompanyName, UserCountry, UserPinCode, UserPanCode, UserAddress;
    private TextView balance, emailId, contactNumber;
    private ProgressDialog progressDialog;
    private List<String> StateList, CityList;
    private String state = "", city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitle("VIEW PROFILE");
        setSupportActionBar(toolbar);
        StateList = new ArrayList<>();
        CityList = new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progressDialog = new ProgressDialog(ViewProfile.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        initializeVariable();
        UserCity.setEnabled(false);
        Call<Register> call = RetrofitClient.getApi().LoginUser(SearchFlight.emailID.toString(), SearchFlight.exPassword.toString());
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                Username.setText(response.body().getData().getName().toString());
                UserCompanyName.setText(response.body().getData().getCompName());
                UserState.setText(response.body().getData().getState());
                UserCountry.setText(response.body().getData().getCountry());
                UserCity.setText(response.body().getData().getCity());
                UserAddress.setText(response.body().getData().getAddress());
                UserPanCode.setText(response.body().getData().getPan());
                if (response.body().getData().getPincode().toString().equals("0"))
                    UserPinCode.setText("");
               else UserPinCode.setText(response.body().getData().getPincode());
                balance.setText("₹ " + response.body().getData().getBalance());
                emailId.setText(response.body().getData().getEmail().toString());
                contactNumber.setText(response.body().getData().getMobile());
                Call<CityState> call1 = RetrofitClient.getApi().findAllState();
                call1.enqueue(new Callback<CityState>() {
                    @Override
                    public void onResponse(Call<CityState> call, Response<CityState> response) {
                        StateList.addAll(response.body().getStateName());
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(ViewProfile.this, android.R.layout.simple_dropdown_item_1line, StateList);
                        UserState.setThreshold(1);
                        UserState.setAdapter(arrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<CityState> call, Throwable t) {

                    }
                });
                if(!TextUtils.isEmpty(UserCity.getText().toString()))
                {
                    Call<CityState> cityStateCall = RetrofitClient.getApi().findAllCitys(UserState.getText().toString());
                    cityStateCall.enqueue(new Callback<CityState>() {
                        @Override
                        public void onResponse(Call<CityState> call, Response<CityState> response) {
                            CityList.addAll(response.body().getCityList());
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter(ViewProfile.this, android.R.layout.simple_dropdown_item_1line, CityList);
                            city=UserCity.getText().toString();
                            UserCity.setThreshold(1);
                            UserCity.setAdapter(arrayAdapter);
                            UserCity.setEnabled(true);
                        }

                        @Override
                        public void onFailure(Call<CityState> call, Throwable t) {

                        }
                    });
                }

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(ViewProfile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        UserState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserCity.setEnabled(true);
                state = parent.getItemAtPosition(position).toString();
                Call<CityState> cityStateCall = RetrofitClient.getApi().findAllCitys(state.toString());
                cityStateCall.enqueue(new Callback<CityState>() {
                    @Override
                    public void onResponse(Call<CityState> call, Response<CityState> response) {
                        CityList.addAll(response.body().getCityList());
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(ViewProfile.this, android.R.layout.simple_dropdown_item_1line, CityList);
                        UserCity.setThreshold(1);
                        UserCity.setAdapter(arrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<CityState> call, Throwable t) {

                    }
                });
            }
        });

        UserCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                city = parent.getItemAtPosition(position).toString();
            }
        });

    }

    private void initializeVariable() {
        Username = findViewById(R.id.user_name);
        UserCompanyName = findViewById(R.id.user_company_name);

        UserState = findViewById(R.id.user_state);
        UserCountry = findViewById(R.id.user_country);
        UserCity = findViewById(R.id.user_city_name);
        UserPinCode = findViewById(R.id.user_pin_code);
        UserPanCode = findViewById(R.id.user_pan_number);

        UserAddress = findViewById(R.id.user_address);

        balance = findViewById(R.id.balance);
        emailId = findViewById(R.id.mailId);
        contactNumber = findViewById(R.id.contact_number);
    }

    //toolbar press back arrow button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void EditProfile(View view) {

        if (TextUtils.isEmpty(Username.getText().toString())) {
            Username.setError("Enter Name");
            return;
        }
        if (TextUtils.isEmpty(UserCompanyName.getText().toString())) {
                UserCompanyName.setError("Enter Company Name");
            return;
        }
        if (TextUtils.isEmpty(UserCountry.getText().toString())) {
            UserCountry.setError("Enter Country Name");
            return;
        }
        if (TextUtils.isEmpty(UserState.getText().toString())) {
            UserState.setError("Enter State Name");
            return;
        }
        if (TextUtils.isEmpty(UserCity.getText().toString())) {
                UserCity.setError("Enter City Name");
            return;
        }
        if (TextUtils.isEmpty(UserPanCode.getText().toString())) {
            UserPanCode.setError("Enter Pan Number");
            return;
        }
        Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
        Matcher matcher = pattern.matcher(UserPanCode.getText().toString());
        if (matcher.matches()) {
            UserPanCode.setError("Enter valid Pan Number");
            return;
        }
        if (TextUtils.isEmpty(UserPinCode.getText().toString())) {
                    UserPinCode.setError("Enter Pin Code");
                    return;
        }
        if (TextUtils.isEmpty(UserAddress.getText().toString())) {
            UserPinCode.setError("Enter Address");
            return;
        }
        progressDialog.show();
        Call<Register> call=RetrofitClient.getApi().editProfile(SearchFlight.userId,Username.getText().toString(),
                UserCompanyName.getText().toString(),state.toString(),city.toString(),UserPinCode.getText().toString(),
                UserPanCode.getText().toString(),UserAddress.getText().toString());
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                Toast.makeText(ViewProfile.this, response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(ViewProfile.this, t.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

}