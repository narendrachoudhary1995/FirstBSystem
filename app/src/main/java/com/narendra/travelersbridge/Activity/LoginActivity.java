package com.narendra.travelersbridge.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.narendra.travelersbridge.POJO.Domestic;
import com.narendra.travelersbridge.POJO.International;
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

public class LoginActivity extends AppCompatActivity {
    private EditText emailLogin, passwordLogin;
    private TextView forGotPassword, SignUp;
    private LinearLayout loginButton;
    private ProgressDialog progressDialog;
    public  static List<Domestic> domesticList; // domestic flight search location find at login time
    public static  List<International> internationalList;//international flight search location find at login time
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // assigned value to filed

        emailLogin = findViewById(R.id.login_email_edit_text);
        passwordLogin = findViewById(R.id.login_password_edit_text);
        forGotPassword = findViewById(R.id.login_forgot_password);
        SignUp = findViewById(R.id.login_sign_up);
        loginButton = findViewById(R.id.login_button);
        internationalList=new ArrayList<>();
        domesticList=new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        // onclick listener for signUp
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CreateAccount.class));
            }
        });
        // Login onClick listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if (isValidateFields()) {

                    Call<Register> userLoginCall = RetrofitClient.getApi().LoginUser(emailLogin.getText().toString(), passwordLogin.getText().toString());
                    userLoginCall.enqueue(new Callback<Register>() {
                        @Override
                        public void onResponse(Call<Register> call, Response<Register> response) {
                            Register register=response.body();
                            if (register.getResponseCode().equals("200")) {
                                final Intent i=new Intent(LoginActivity.this,SearchFlight.class);
                                i.putExtra("oldPassword",register.getData().getExPassword());
                                i.putExtra("userId",register.getData().getId());
                                i.putExtra("eamilid",register.getData().getEmail());
                                Call<Register> getLocation=RetrofitClient.getApi().FlightSearchLocation();
                                getLocation.enqueue(new Callback<Register>() {
                                    @Override
                                    public void onResponse(Call<Register> call, Response<Register> response) {
                                        if (response.body().getResponseCode().toString().equals("200")) {
                                            domesticList = response.body().getDomestic();
                                            internationalList = response.body().getInternational();
                                            progressDialog.dismiss();
                                            startActivity(i);
                                        }
                                        else
                                        {
                                            Toast.makeText(LoginActivity.this, response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<Register> call, Throwable t) {

                                    }
                                });
                            } else {
                                Toast.makeText(LoginActivity.this, "Email-Id or Password is wrong", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<Register> call, Throwable t) {

                        }
                    });
                }
            }
        });
        // onclick listener for forgotPassword
        forGotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   startActivity(new Intent(LoginActivity.this,SearchFlight.class));
            }
        });
    }

    private boolean isValidateFields() {
        boolean isEmailValidate = false, isPasswordValidate = false;
        String validEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                "\\@" +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" +

                "\\." +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                ")+";
        if (emailLogin.getText().length() == 0) {
            emailLogin.setError("Enter Email-ID");

        } else {
            Matcher matcher = Pattern.compile(validEmail).matcher(emailLogin.getText());
            if (matcher.matches())
                isEmailValidate = true;
            else
                emailLogin.setError("inValidate Mail-ID");
        }
        if (passwordLogin.getText().length() == 0) {
            passwordLogin.setError("Enter Password");
        } else {
            if (passwordLogin.getText().length() >= 6)
                isPasswordValidate = true;
            else
                passwordLogin.setError("password should be more then 6 character");
        }
        if (isEmailValidate && isPasswordValidate)
            return true;
        else
            return false;
    }
}
