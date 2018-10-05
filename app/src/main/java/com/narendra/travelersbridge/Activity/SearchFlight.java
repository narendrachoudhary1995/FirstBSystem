package com.narendra.travelersbridge.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.narendra.travelersbridge.Fragments.OneWay;
import com.narendra.travelersbridge.Fragments.RoundTrip;
import com.narendra.travelersbridge.POJO.Register;
import com.narendra.travelersbridge.R;
import com.narendra.travelersbridge.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFlight extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ViewPager mViewPager;
    private NavigationView navigationView;
    public static String exPassword,userId,emailID;
    private AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("SEARCH FLIGHT");
        setSupportActionBar(toolbar);
        getDataFromLoginActivity();
        drawerLayout=findViewById(R.id.drawer_layout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this ,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_edit_profile:
                        startActivity(new Intent(SearchFlight.this,ViewProfile.class));
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.navigation_transaction:
                        drawerLayout.closeDrawers();
                        startActivity(new Intent(SearchFlight.this,Transaction_History.class));
                        return  true;
                    case R.id.navigation_change_password:
                        drawerLayout.closeDrawers();
                        final Button cancel,changePassword;
                        final EditText oldPassword,newPassword,conformPassword;
                        dialog=new AlertDialog.Builder(SearchFlight.this);
                        LayoutInflater inflater=SearchFlight.this.getLayoutInflater();
                        View dialogView=inflater.inflate(R.layout.change_password,null);
                        dialog.setView(dialogView);
                        final AlertDialog alertDialog=dialog.create();
                      alertDialog.setCanceledOnTouchOutside(false);
                        cancel=dialogView.findViewById(R.id.cancel_password_change);
                        changePassword=dialogView.findViewById(R.id.change_password_change);
                        oldPassword=dialogView.findViewById(R.id.oldPassword);
                        newPassword=dialogView.findViewById(R.id.newPassword);
                        conformPassword=dialogView.findViewById(R.id.conformPassword);
                        alertDialog.show();
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                        changePassword.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(TextUtils.isEmpty(oldPassword.getText().toString()))
                                {
                                    oldPassword.setError("Enter old Password");
                                    oldPassword.requestFocus();
                                    return;
                                }
                                if(!oldPassword.getText().toString().equals(exPassword))
                                {
                                    Toast.makeText(SearchFlight.this, "Wrong Old Password", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if(TextUtils.isEmpty(newPassword.getText().toString()))
                                {
                                    newPassword.setError("Enter new Password");
                                    newPassword.requestFocus();
                                    return;
                                }
                                if(TextUtils.isEmpty(conformPassword.getText().toString()))
                                {
                                    conformPassword.setError("Enter conform Password");
                                    conformPassword.requestFocus();
                                    return;
                                }
                                if(!newPassword.getText().toString().equals(conformPassword.getText().toString()))
                                {
                                    conformPassword.setError("Password is not matched");
                                    newPassword.requestFocus();
                                    return;
                                }
                                Call<Register> call= RetrofitClient.getApi().changePassword(userId,oldPassword.getText().toString(),newPassword.getText().toString());
                                call.enqueue(new Callback<Register>() {
                                    @Override
                                    public void onResponse(Call<Register> call, Response<Register> response) {
                                        Toast.makeText(SearchFlight.this, response.body().getResponseMessage(), Toast.LENGTH_SHORT).show();
                                        exPassword=newPassword.getText().toString();
                                    }

                                    @Override
                                    public void onFailure(Call<Register> call, Throwable t) {

                                    }
                                });
                                alertDialog.dismiss();
                            }
                        });
                        return  true;
                    case R.id.navigation_query:
                        drawerLayout.closeDrawers();
                        startActivity(new Intent(SearchFlight.this,SendQuery.class));
                        return  true;
                    case R.id.navigation_logout:
                        drawerLayout.closeDrawers();
                        return  true;
                }
                return  false;
            }
        });
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    private void getDataFromLoginActivity() {
    Intent i=getIntent();
    exPassword=i.getStringExtra("oldPassword");
    userId=i.getStringExtra("userId");
    emailID=i.getStringExtra("eamilid");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){return true;}

        return super.onOptionsItemSelected(item);
    }

    //toolbar press back arrow button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position)
            {
                case 0:
                    OneWay oneWay=new OneWay();
                    return oneWay;
                case 1:
                    RoundTrip roundTrip=new RoundTrip();
                    return roundTrip;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position)
        {
            switch(position)
            {
                case 0:
                    return "ONE WAY";
                case 1:
                    return "ROUND TRIP";


            }
            return null;
        }
    }

}
