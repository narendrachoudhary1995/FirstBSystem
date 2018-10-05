package com.narendra.travelersbridge.Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.narendra.travelersbridge.Activity.FlightSearchResult;
import com.narendra.travelersbridge.Activity.LoginActivity;
import com.narendra.travelersbridge.Activity.SearchCity;
import com.narendra.travelersbridge.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneWay extends Fragment {
    public static int i=0;
    private static String originSelectedLocation="origin";
    private static String destinationSelectedLocation="destination";
    private String departureDate="notSelected";
    private String classOfPassenger="notSelected";
    Date date =new Date();
    private String dataInShortForm;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-YYYY");
    public static String DomesticOrInternational="Domestic";
    private static List<com.narendra.travelersbridge.POJO.Domestic> domestics;
    private static List<String> domesticTitle,internationalTitle,domesticFullPart,internationalFullPart;
    private static List<com.narendra.travelersbridge.POJO.International> internationals;
    private RadioButton Domestic, International;
    private ImageView DestinationDropDownImage;
    private LinearLayout PassengerOriginDialog, PassengerDestinationDialog, DepartureCalendar, ReturnCalendar, AdultsMinus, ChildrenMinus, InfantsMinus, SearchFlight;
    public static TextView DepartureDate;
    private TextView  ReturnDate, AdultsPlus,
            ChildrenPlus,  InfantsPlus;
    public static TextView  AdultNumber,ChildrenNumber, InfantsNumber;
    public static TextView passengerOriginInShort, passengerOriginInFull, passengerDestinationInShort,
            passengerDestinationInFull;
    private int totalNumberOfPassenger = 1;
    private Spinner passengerClass;
    public OneWay() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_one_way, container, false);
        International = v.findViewById(R.id.international);
        Domestic = v.findViewById(R.id.domestic);
        domestics=new ArrayList<>();
        domesticTitle=new ArrayList<>();
        internationalTitle=new ArrayList<>();
        domesticFullPart=new ArrayList<>();
        internationalFullPart=new ArrayList<>();
        internationals=new ArrayList<>();
        departureDate=simpleDateFormat.format(date);
        domestics= LoginActivity.domesticList;
        passengerClass=v.findViewById(R.id.spinner_for_select_class);
        internationals=LoginActivity.internationalList;
        findSubPartOfLocation();
        PassengerOriginDialog = v.findViewById(R.id.from_location_dialog_text_linear_layout);
        passengerOriginInShort = v.findViewById(R.id.selected_from_city_short);
        passengerOriginInFull = v.findViewById(R.id.selected_from_city);
        PassengerDestinationDialog = v.findViewById(R.id.to_location_dialog_text_linear_layout);
        DestinationDropDownImage = v.findViewById(R.id.destination_drop_down);
        passengerDestinationInShort = v.findViewById(R.id.to_location_dialog_text);
        passengerDestinationInFull = v.findViewById(R.id.destination_full_city);
        DepartureCalendar = v.findViewById(R.id.departure_calender_open);
        ReturnCalendar = v.findViewById(R.id.return_calender_open);
        DepartureDate = v.findViewById(R.id.departure_date_text);
        ReturnDate = v.findViewById(R.id.return_date_text);
        AdultsPlus = v.findViewById(R.id.adults_plus);
        AdultsMinus = v.findViewById(R.id.adults_minus);
        AdultNumber = v.findViewById(R.id.adults_number);
        ChildrenPlus = v.findViewById(R.id.children_plus);
        ChildrenMinus = v.findViewById(R.id.children_minus);
        ChildrenNumber = v.findViewById(R.id.children_number);
        InfantsPlus = v.findViewById(R.id.infants_plus);
        InfantsMinus = v.findViewById(R.id.infants_minus);
        InfantsNumber = v.findViewById(R.id.infants_number);
        SearchFlight = v.findViewById(R.id.search_flight);
        Domestic.setChecked(true);
        DepartureDate.setText(setDepartureDate(date).toString());
        DepartureDate.setText(simpleDateFormat.format(date));
        passengerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        classOfPassenger="0";
                        break;
                    case 1:
                        classOfPassenger="1";
                        break;
                    case 2:
                        classOfPassenger="2";
                        break;
                    case 3:
                        classOfPassenger="3";
                        break;
                    case 4:
                        classOfPassenger="4";
                        break;
                    case 5:
                        classOfPassenger="5";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Domestic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DomesticOrInternational="Domestic";
                passengerDestinationInFull.setText("Destination");
                passengerOriginInFull.setText("Origin");
            }
        });
        International.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DomesticOrInternational="International";
                passengerDestinationInFull.setText("Destination");
                passengerOriginInFull.setText("Origin");
            }
        });
        AdultsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(AdultNumber.getText().toString());
                i = i + 1;
                if (totalNumberOfPassenger < 9) {
                    AdultNumber.setText(String.valueOf(i));
                    totalNumberOfPassenger += 1;
                } else if (totalNumberOfPassenger >= 9)
                    Toast.makeText(getActivity(), "Upto 9 Passengers can Booked at a time", Toast.LENGTH_SHORT).show();
            }
        });
        AdultsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(AdultNumber.getText().toString());
                i--;
                if (i >= 1) {
                    AdultNumber.setText(String.valueOf(i));
                    totalNumberOfPassenger -= 1;
                }
            }
        });
        ChildrenPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(ChildrenNumber.getText().toString());
                i++;
                if (totalNumberOfPassenger < 9) {
                    ChildrenNumber.setText(String.valueOf(i));
                    totalNumberOfPassenger += 1;
                } else if (totalNumberOfPassenger >= 9)
                    Toast.makeText(getActivity(), "Upto 9 Passengers can Booked at a time", Toast.LENGTH_SHORT).show();
            }
        });
        ChildrenMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(ChildrenNumber.getText().toString());
                i--;
                if (i >= 0) {
                    ChildrenNumber.setText(String.valueOf(i));
                    totalNumberOfPassenger -= 1;
                }
            }
        });
        InfantsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(InfantsNumber.getText().toString());
                i++;
                if (totalNumberOfPassenger < 9) {
                    InfantsNumber.setText(String.valueOf(i));
                    totalNumberOfPassenger += 1;
                } else if (totalNumberOfPassenger >= 9)
                    Toast.makeText(getActivity(), "Upto 9 Passenger can Booked at a time", Toast.LENGTH_SHORT).show();
            }
        });
        InfantsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(InfantsNumber.getText().toString());
                i--;
                if (i >= 0) {
                    InfantsNumber.setText(String.valueOf(i));
                    totalNumberOfPassenger -= 1;
                }
            }
        });
        DepartureCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year, month, day;
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String monthYear, yearText;
                        if (month == 0)
                            monthYear = "Jan";
                        else if (month == 1)
                            monthYear = "Feb";
                        else if (month == 2)
                            monthYear = "Mar";
                        else if (month == 3)
                            monthYear = "Apr";
                        else if (month == 4)
                            monthYear = "May";
                        else if (month == 5)
                            monthYear = "Jun";
                        else if (month == 6)
                            monthYear = "Jul";
                        else if (month == 7)
                            monthYear = "Aug";
                        else if (month == 8)
                            monthYear = "Sep";
                        else if (month == 9)
                            monthYear = "Oct";
                        else if (month == 10)
                            monthYear = "Nov";
                        else
                            monthYear = "Dec";
                        yearText = String.valueOf(year % 100);
                        dataInShortForm=dayOfMonth + " " + monthYear;
                        month++;
                        if(String.valueOf(dayOfMonth).toString().length()==1&&String.valueOf(month).toString().length()==1)
                        departureDate="0"+dayOfMonth+"-0"+month+"-"+year;
                        else if(String.valueOf(dayOfMonth).toString().length()==2&&String.valueOf(month).length()==1)
                            departureDate=dayOfMonth+"-0"+month+"-"+year;
                        else if(String.valueOf(dayOfMonth).length()==1&&String.valueOf(month).length()==2)
                            departureDate="0"+dayOfMonth+"-"+month+"-"+year;
                        else
                            departureDate=dayOfMonth+"-"+month+"-"+year;
                        DepartureDate.setText(dayOfMonth + " " + monthYear + " " + yearText);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });
        PassengerOriginDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SearchCity.class);
                i.putExtra("cityName", passengerDestinationInFull.getText().toString());
                i.putExtra("from", "From");
                i.putExtra("itsFrom","OneWay");
                startActivity(i);
            }
        });
        ReturnCalendar.setEnabled(false);

        PassengerDestinationDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SearchCity.class);
                i.putExtra("cityName", passengerOriginInFull.getText());
                i.putExtra("from", "To");
                i.putExtra("itsFrom","OneWay");
                startActivity(i);
            }
        });
        SearchFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(originSelectedLocation.equals("origin"))
                {
                    Toast.makeText(getActivity(), "Select Origin Location", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(destinationSelectedLocation.equals("destination"))
                {
                    Toast.makeText(getActivity(), "Select Destination Location", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(getActivity(), FlightSearchResult.class);
                i.putExtra("adults", AdultNumber.getText().toString());
                i.putExtra("children", ChildrenNumber.getText().toString());
                i.putExtra("Infants", InfantsNumber.getText().toString());
                i.putExtra("origin",originSelectedLocation);
                i.putExtra("destination",destinationSelectedLocation);
                i.putExtra("startDate",departureDate);
                i.putExtra("class",classOfPassenger);
                if(DomesticOrInternational.equals("Domestic"))
                    i.putExtra("domesticOrIntern","DOME");
                else
                    i.putExtra("domesticOrIntern","INTL");
                i.putExtra("originfullName",passengerOriginInFull.getText().toString());
                i.putExtra("destinationfullname",passengerDestinationInFull.getText().toString());
                i.putExtra("dateSelectedFromOneWay",dataInShortForm);
                startActivity(i);
            }
        });
        return v;
    }

    private String setDepartureDate(Date date) {

        String monthYear="";
        String month=new SimpleDateFormat("MM").format(date);
        if (month.equals("00"))
            monthYear = "Jan";
        else if (month.equals("01"))
            monthYear = "Feb";
        else if (month.equals("02"))
            monthYear = "Mar";
        else if (month.equals("03"))
            monthYear = "Apr";
        else if (month.equals("04"))
            monthYear = "May";
        else if (month.equals("05"))
            monthYear = "Jun";
        else if (month.equals("06"))
            monthYear = "Jul";
        else if (month.equals("07"))
            monthYear = "Aug";
        else if (month.equals("08"))
            monthYear = "Sep";
        else if (month.equals("09"))
            monthYear = "Oct";
        else if (month.equals("10"))
            monthYear = "Nov";
        else
            monthYear = "Dec";
        dataInShortForm=new SimpleDateFormat("dd").format(date)+" "+monthYear;
        return (new SimpleDateFormat("dd").format(date)+" "+monthYear+" "+new SimpleDateFormat("YY").format(date));

    }


    private void findSubPartOfLocation() {

        for (int i=0;i<domestics.size();i++) {
            String s=new String(domestics.get(i).getValue());
            String s1=s.substring(s.indexOf("[")+1);
            s1=s1.substring(0,s1.indexOf("]"));
            domesticTitle.add(s1);
            s1=s.substring(s.indexOf("- ")+2);
            domesticFullPart.add(s1);
        }
        for(int i=0;i<internationals.size();i++)
        {
            String s=new String(internationals.get(i).getValue());
            String s1=s.substring(s.indexOf("[")+1);
            s1=s1.substring(0,s1.indexOf("]"));
            internationalTitle.add(s1);
            s1=s.substring(s.indexOf("- ")+2);
            s1=s1.substring(0,s1.indexOf(","));
            internationalFullPart.add(s1);
        }
    }
    public static void selectedFromCity(String s) {
       originSelectedLocation=s;
       if(DomesticOrInternational.equals("Domestic"))
       {
        for(i=0;i<LoginActivity.domesticList.size();i++)
        {
            if(LoginActivity.domesticList.get(i).getValue().equals(s))
                break;
        }

        passengerOriginInShort.setText(domesticTitle.get(i));
        passengerOriginInFull.setText(domesticFullPart.get(i));
    }
    else
       {
           for(i=0;i<LoginActivity.internationalList.size();i++)
           {
               if(LoginActivity.internationalList.get(i).getValue().equals(s))
                   break;
           }
           passengerOriginInShort.setText(internationalTitle.get(i));
           passengerOriginInFull.setText(internationalFullPart.get(i));
       }
    }

    public static void selectedToCity(String s) {
        destinationSelectedLocation=s;
        if(DomesticOrInternational.equals("Domestic"))
        {
        for(i=0;i<LoginActivity.domesticList.size();i++)
        {
            if(LoginActivity.domesticList.get(i).getValue().equals(s))
                break;
        }
        passengerDestinationInShort.setText(domesticTitle.get(i));
        passengerDestinationInFull.setText(domesticFullPart.get(i));
    }
    else
        {
            for(i=0;i<LoginActivity.internationalList.size();i++)
            {
                if(LoginActivity.internationalList.get(i).getValue().equals(s))
                    break;
            }
            passengerDestinationInShort.setText(internationalTitle.get(i));
            passengerDestinationInFull.setText(internationalFullPart.get(i));
        }
    }
}
