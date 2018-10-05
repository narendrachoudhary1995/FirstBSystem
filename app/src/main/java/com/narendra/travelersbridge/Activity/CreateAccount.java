package com.narendra.travelersbridge.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.narendra.travelersbridge.POJO.Register;
import com.narendra.travelersbridge.R;
import com.narendra.travelersbridge.Retrofit.RetrofitClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccount extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText EmailId, Password, ContactNumber;
    LinearLayout SignUp;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        //fields assign
        EmailId = findViewById(R.id.account_email);
        Password = findViewById(R.id.account_password);
        ContactNumber = findViewById(R.id.account_contact_number);
        SignUp = findViewById(R.id.sign_up_button);
        // add toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        // add back arrow on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //signUp layout On Click listener
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                // String s=FirstName.getText().toString().concat(" ").concat(LastName.getText().toString());
                if (FieldsValidate()) {

                    Call<Register> userRegisterCall = RetrofitClient.getApi().RegisterUser(EmailId.getText().toString(), ContactNumber.getText().toString(), Password.getText().toString());
                    userRegisterCall.enqueue(new Callback<Register>() {
                        @Override
                        public void onResponse(Call<Register> call, Response<Register> response) {
                            Register register = response.body();
                            if (register.getResponseCode().toString().equals("200")) {
                                progressDialog.dismiss();
                                startActivity(new Intent(CreateAccount.this, LoginActivity.class));
                               // Toast.makeText(CreateAccount.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                            } else if (register.getResponseCode().equals("203")) {
                                Toast.makeText(CreateAccount.this, register.getResponseMessage().toString(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<Register> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(CreateAccount.this, "Email-Id or Mobile already register", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private boolean FieldsValidate() {
        String validContactNumber = "[0-9]{10,13}";
        String validEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                "\\@" +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" +

                "\\." +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                ")+";

        if (TextUtils.isEmpty(ContactNumber.getText().toString())) {
            ContactNumber.setError("Enter Contact Number");
            ContactNumber.requestFocus();
            return false;
        }
        if (!ContactNumber.getText().toString().matches(validContactNumber)) {
            ContactNumber.setError("Enter Valid Mobile Number");
            ContactNumber.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(EmailId.getText().toString())) {
            EmailId.setError("Enter Email-ID");
            EmailId.requestFocus();
            return false;
        }
        Matcher matcher = Pattern.compile(validEmail).matcher(EmailId.getText());
        if (!matcher.matches()) {
            EmailId.setError("Enter Valid Email-Id");
            EmailId.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(Password.getText().toString())) {
            Password.setError("Enter Password");
            Password.requestFocus();
            return false;
        }
        if (Password.getText().toString().length() < 6) {
            Password.setError("Password should be more then 6 character");
            Password.requestFocus();
            return false;
        }
        return true;
    }

    //toolbar press back arrow button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
