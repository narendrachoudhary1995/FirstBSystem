package com.narendra.travelersbridge.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.narendra.travelersbridge.Fragments.OneWay;
import com.narendra.travelersbridge.Fragments.RoundTrip;
import com.narendra.travelersbridge.R;

import java.util.ArrayList;
import java.util.List;

public class SearchCity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView ClearText;
    private EditText SearchCityText;
    private ListView CityContainer;
    private String From;
    private List<String> CityName;
    private String s="",itsFrom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
         s= getIntent().getStringExtra("cityName");
         itsFrom=getIntent().getStringExtra("itsFrom");
        From = getIntent().getStringExtra("from");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CityName = new ArrayList<>();
        addAlldataToCityName();
        ClearText = findViewById(R.id.text_clear);
        SearchCityText = findViewById(R.id.search_city_text);
        ClearText.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CityContainer = findViewById(R.id.city_container);
       /* if (!s.equals("Origin")||!s.equals("Destination")) {
            CityName.remove(s.toString());
        }*/
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, CityName);
        CityContainer.setAdapter(arrayAdapter);
        SearchCityText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                ClearText.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ClearText.setVisibility(View.VISIBLE);
                List<String> lstFound = new ArrayList<>();
                for (String item : CityName) {
                    if (item.toLowerCase().contains(s.toString().toLowerCase()))
                        lstFound.add(item);
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(SearchCity.this, android.R.layout.simple_list_item_1, lstFound);
                CityContainer.setAdapter(arrayAdapter);

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (SearchCityText.getText().length() >= 1)
                    ClearText.setVisibility(View.VISIBLE);
                else {
                    ClearText.setVisibility(View.GONE);
                    ArrayAdapter arrayAdapter = new ArrayAdapter(SearchCity.this, android.R.layout.simple_list_item_1, CityName);
                    CityContainer.setAdapter(arrayAdapter);
                }

            }
        });
        CityContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SearchCity.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
               if(itsFrom.equals("OneWay"))
                if (From.equals("To"))
                    OneWay.selectedToCity(parent.getItemAtPosition(position).toString());
                else
                    OneWay.selectedFromCity(parent.getItemAtPosition(position).toString());
                else
               if (From.equals("To"))
                   RoundTrip.findStringToSearchFlight(parent.getItemAtPosition(position).toString());
               else
                   RoundTrip.findStringFromSearchFlight(parent.getItemAtPosition(position).toString());
                onBackPressed();
            }
        });
        ClearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchCityText.setText("");
            }
        });
    }

    private void addAlldataToCityName() {
      if(itsFrom.equals("OneWay"))
      if (OneWay.DomesticOrInternational.equals("Domestic")) {
            for (int i = 0; i < LoginActivity.domesticList.size(); i++) {
                CityName.add(LoginActivity.domesticList.get(i).getValue());
            }
            if (!s.equals("Origin")&&!s.equals("Destination"))
            CityName.remove(OneWay.i);
        } else {
            for (int i = 0; i < LoginActivity.internationalList.size(); i++) {
                CityName.add(LoginActivity.internationalList.get(i).getValue());
            }
            if (!s.equals("Origin")&&!s.equals("Destination"))
            CityName.remove(OneWay.i);
        }
        else
      if (RoundTrip.DomesticOrInternational.equals("Domestic")) {
          for (int i = 0; i < LoginActivity.domesticList.size(); i++) {
              CityName.add(LoginActivity.domesticList.get(i).getValue());
          }
          if (!s.equals("Origin")&&!s.equals("Destination"))
              CityName.remove(RoundTrip.j);
      } else {
          for (int i = 0; i < LoginActivity.internationalList.size(); i++) {
              CityName.add(LoginActivity.internationalList.get(i).getValue());
          }
          if (!s.equals("Origin")&&!s.equals("Destination"))
              CityName.remove(RoundTrip.j);
      }
    }

    //toolbar press back arrow button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
