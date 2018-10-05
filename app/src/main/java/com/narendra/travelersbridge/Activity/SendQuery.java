package com.narendra.travelersbridge.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.narendra.travelersbridge.POJO.Register;
import com.narendra.travelersbridge.R;
import com.narendra.travelersbridge.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendQuery extends AppCompatActivity {
    private Toolbar toolbar;
    private Spinner queryType,userType;
    private EditText Subject,message;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_query);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Enquiry");
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        queryType=findViewById(R.id.enquiryType);
        userType=findViewById(R.id.adminOrUser);
        Subject=findViewById(R.id.subject);
        message=findViewById(R.id.message_content);
        progressDialog=new ProgressDialog(SendQuery.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
    }

    //toolbar press back arrow button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void sendQuery(View view) {

        if(TextUtils.isEmpty(Subject.getText().toString()))
        {
            Subject.setError("Enter Subject");
            return;
        }
        if(TextUtils.isEmpty(message.getText().toString()))
        {
            message.setError("Enter Enter Message");
            return;
        }
        if(message.getText().toString().length()<15)
        {
            Toast.makeText(this, "message should be more then 15 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.show();
        String s=queryType.getSelectedItem().toString(),s1=userType.getSelectedItem().toString();
        Call<Register> call= RetrofitClient.getApi().contactUs(queryType.getSelectedItem().toString(),userType.getSelectedItem().toString(),
                Subject.getText().toString(),message.getText().toString());
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                progressDialog.dismiss();
                Toast.makeText(SendQuery.this, response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {

            }
        });
    }
}
