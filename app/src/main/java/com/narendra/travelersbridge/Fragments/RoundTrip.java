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

import com.narendra.travelersbridge.Activity.LoginActivity;
import com.narendra.travelersbridge.Activity.RoundTripResult;
import com.narendra.travelersbridge.Activity.SearchCity;
import com.narendra.travelersbridge.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoundTrip extends Fragment {
    public static String DomesticOrInternational = "Domestic";
    private static String OriginCityName, DestinationCityName;
    private String DepartureDateShort = "29 AUG", ReturnDateShort = "29 AUG";
    private String classOfPassenger = "0";
    private String DepartureDateSting, ReturnDateString;
    private RadioButton Domestic, International;
    private ImageView DestinationDropDownImage;
    private LinearLayout PassengerOriginDialog, PassengerDestinationDialog, DepartureCalendar, ReturnCalendar, AdultsMinus, ChildrenMinus, InfantsMinus, SearchFlight;
    private TextView DepartureDate, ReturnDate, AdultsPlus, AdultNumber,
            ChildrenPlus, ChildrenNumber, InfantsPlus, InfantsNumber;
    private static TextView passengerOriginInShort, passengerOriginInFull, passengerDestinationInShort,
            passengerDestinationInFull;
    private int totalNumberOfPassenger = 1;
    public static int j = 0;
    private Spinner PassengerClass;
    private DatePickerDialog datePickerDialogDeparture, datePickerDialogReturn;

    public RoundTrip() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_round_trip, container, false);
        International = v.findViewById(R.id.international);
        Domestic = v.findViewById(R.id.domestic);
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
        PassengerClass = v.findViewById(R.id.spinner_for_select_class);
        DepartureDate.setText(findTodayDateDefault(1).toString());
        ReturnDate.setText(findTodayDateDefault(2).toString());
        PassengerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        classOfPassenger = "0";
                        break;
                    case 1:
                        classOfPassenger = "1";
                        break;
                    case 2:
                        classOfPassenger = "2";
                        break;
                    case 3:
                        classOfPassenger = "3";
                        break;
                    case 4:
                        classOfPassenger = "4";
                        break;
                    case 5:
                        classOfPassenger = "5";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                classOfPassenger = "0";
            }
        });
        Domestic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DomesticOrInternational = "Domestic";
                passengerDestinationInFull.setText("Destination");
                passengerOriginInFull.setText("Origin");
            }
        });
        International.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DomesticOrInternational = "International";
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
                        month++;
                        if (String.valueOf(dayOfMonth).toString().length() == 1 && String.valueOf(month).toString().length() == 1) {
                            DepartureDateSting = "0" + dayOfMonth + "-0" + month + "-" + year;
                            DepartureDateShort = "0" + dayOfMonth + " 0" + monthYear;
                        } else if (String.valueOf(dayOfMonth).toString().length() == 2 && String.valueOf(month).length() == 1) {
                            DepartureDateSting = dayOfMonth + "-0" + month + "-" + year;
                            DepartureDateShort = dayOfMonth + " 0" + monthYear;
                        } else if (String.valueOf(dayOfMonth).length() == 1 && String.valueOf(month).length() == 2) {
                            DepartureDateSting = "0" + dayOfMonth + "-" + month + "-" + year;
                            DepartureDateShort = "0" + dayOfMonth + " " + monthYear;
                        } else {
                            DepartureDateSting = dayOfMonth + "-" + month + "-" + year;
                            DepartureDateShort = dayOfMonth + " " + monthYear;
                        }
                        yearText = String.valueOf(year % 100);

                        DepartureDate.setText(DepartureDateShort + " " + yearText);
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
                i.putExtra("itsFrom", "RoundTrip");
                startActivity(i);
            }
        });
        ReturnCalendar.setOnClickListener(new View.OnClickListener() {
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
                        month++;
                        if (String.valueOf(dayOfMonth).toString().length() == 1 && String.valueOf(month).toString().length() == 1) {
                            ReturnDateString = "0" + dayOfMonth + "-0" + month + "-" + year;
                            ReturnDateShort = "0" + dayOfMonth + " 0" + monthYear;
                        } else if (String.valueOf(dayOfMonth).toString().length() == 2 && String.valueOf(month).length() == 1) {
                            ReturnDateString = dayOfMonth + "-0" + month + "-" + year;
                            ReturnDateShort = dayOfMonth + " 0" + monthYear;
                        } else if (String.valueOf(dayOfMonth).length() == 1 && String.valueOf(month).length() == 2) {
                            ReturnDateString = "0" + dayOfMonth + "-" + month + "-" + year;
                            ReturnDateShort = "0" + dayOfMonth + " " + monthYear;
                        } else {
                            ReturnDateString = dayOfMonth + "-" + month + "-" + year;
                            ReturnDateShort = dayOfMonth + " " + monthYear;
                        }
                        yearText = String.valueOf(year % 100);
                        ReturnDate.setText(ReturnDateShort + " " + yearText);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });
        PassengerDestinationDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SearchCity.class);
                i.putExtra("cityName", passengerOriginInFull.getText());
                i.putExtra("from", "To");
                i.putExtra("itsFrom", "RoundTrip");
                startActivity(i);
            }
        });
        SearchFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passengerOriginInFull.getText().toString().equals("Origin")) {
                    Toast.makeText(getActivity(), "Select Origin Location", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passengerDestinationInFull.getText().toString().equals("destination")) {
                    Toast.makeText(getActivity(), "Select Destination Location", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(getActivity(), RoundTripResult.class);
                if (DomesticOrInternational.equals("Domestic"))
                    i.putExtra("domesticOrInternational", "DOME");
                else
                    i.putExtra("domesticOrInternational", "INTL");
                i.putExtra("originShort", passengerOriginInShort.getText().toString());
                i.putExtra("originFull", passengerOriginInFull.getText().toString());
                i.putExtra("destinationShort", passengerDestinationInShort.getText().toString());
                i.putExtra("destinationFull", passengerDestinationInFull.getText().toString());
                i.putExtra("dateFrom", DepartureDateSting.toString());
                i.putExtra("dateFromShort", DepartureDateShort.toString());
                i.putExtra("dateTo", ReturnDateString.toString());
                i.putExtra("dateToShort", ReturnDateShort.toString());
                i.putExtra("adults", AdultNumber.getText().toString());
                i.putExtra("children", ChildrenNumber.getText().toString());
                i.putExtra("infants", InfantsNumber.getText().toString());
                i.putExtra("class", classOfPassenger);
                startActivity(i);
            }
        });
        return v;
    }

    public static void findStringFromSearchFlight(String s) {
        OriginCityName = s;
        String TitlePart = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
        passengerOriginInShort.setText(TitlePart.toString());
        TitlePart = s.substring(s.indexOf("- ") + 2);
        passengerOriginInFull.setText(TitlePart.toString());
        if (DomesticOrInternational.equals("Domestic"))
            j = LoginActivity.domesticList.indexOf(s) + 1;
        else
            j = LoginActivity.internationalList.indexOf(s) + 1;
    }

    public static void findStringToSearchFlight(String s) {
        DestinationCityName = s;
        String TitlePart = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
        passengerDestinationInShort.setText(TitlePart.toString());
        TitlePart = s.substring(s.indexOf("- ") + 2);
        passengerDestinationInFull.setText(TitlePart.toString());
        if (DomesticOrInternational.equals("Domestic"))
            j = LoginActivity.domesticList.indexOf(s) + 1;
        else
            j = LoginActivity.internationalList.indexOf(s) + 1;
    }

    private String findTodayDateDefault(int i) {

        Calendar calendar = Calendar.getInstance();
        if (i == 2) {
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int year = calendar.get(Calendar.YEAR);
            String monthYear;
            String Return_Date_String, Return_Date_Short;
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
            month++;
            if (String.valueOf(dayOfMonth).toString().length() == 1 && String.valueOf(month).toString().length() == 1) {
                ReturnDateString = "0" + dayOfMonth + "-0" + month + "-" + year;
                ReturnDateShort = "0" + dayOfMonth + " 0" + monthYear;
            } else if (String.valueOf(dayOfMonth).toString().length() == 2 && String.valueOf(month).length() == 1) {
                ReturnDateString = dayOfMonth + "-0" + month + "-" + year;
                ReturnDateShort = dayOfMonth + " 0" + monthYear;
            } else if (String.valueOf(dayOfMonth).length() == 1 && String.valueOf(month).length() == 2) {
                ReturnDateString = "0" + dayOfMonth + "-" + month + "-" + year;
                ReturnDateShort = "0" + dayOfMonth + " " + monthYear;
            } else {
                ReturnDateString = dayOfMonth + "-" + month + "-" + year;
                ReturnDateShort = dayOfMonth + " " + monthYear;
            }
            String s = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + " " +
                    monthYear + " " + String.valueOf(calendar.get(Calendar.YEAR) % 100);
            return s;
        } else {
            calendar.add(Calendar.DAY_OF_YEAR, 30);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int year = calendar.get(Calendar.YEAR);
            String monthYear;
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
            month++;
            if (String.valueOf(dayOfMonth).toString().length() == 1 && String.valueOf(month).toString().length() == 1) {
                DepartureDateSting = "0" + dayOfMonth + "-0" + month + "-" + year;
                DepartureDateShort = "0" + dayOfMonth + " 0" + monthYear;
            } else if (String.valueOf(dayOfMonth).toString().length() == 2 && String.valueOf(month).length() == 1) {
                DepartureDateSting = dayOfMonth + "-0" + month + "-" + year;
                DepartureDateShort = dayOfMonth + " 0" + monthYear;
            } else if (String.valueOf(dayOfMonth).length() == 1 && String.valueOf(month).length() == 2) {
                DepartureDateSting = "0" + dayOfMonth + "-" + month + "-" + year;
                DepartureDateShort = "0" + dayOfMonth + " " + monthYear;
            } else {
                DepartureDateSting = dayOfMonth + "-" + month + "-" + year;
                DepartureDateShort = dayOfMonth + " " + monthYear;
            }
            String s = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + " " +
                    monthYear + " " + String.valueOf(calendar.get(Calendar.YEAR) % 100);
            return s;
        }
    }
}
