package com.narendra.travelersbridge.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.narendra.travelersbridge.Fragments.OneWay;
import com.narendra.travelersbridge.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassengerDetails extends AppCompatActivity {
    private Toolbar toolbar;
    private int position;
    private List<EditText> NameOfPassenger;
    private List<EditText> LastNameOfPassenger;
    private List<TextView> DateOfBirthPassenger;
    private RadioButton walletCredit, Ebs;
    private static TextView bothLocation, AirWayName, FlightNumber, LocationOrigin, OriginDate, OriginTime, OriginAirPortName, OriginAirportTerminal, durationBetweenLocation;
    private static TextView LocationDestination, DestinationDate, DestinationTime, DestinationAirPortName, DestinationTerminal;
    private static ImageView flightIcon;
    private TextView basePrice, texPrice, totalPrice;
    private int adults = 0, children = 0, infants = 0;
    private LinearLayout passengerFirstLayout, passengerSecondLayout, passengerThirdLayout,
            passengerFourthLayout, passengerFifthLayout, passengerSixthLayout,
            passengerSeventhLayout, passengerEighthLayout,
            passengerNinthLayout;
    private Spinner firstPassengerFirstSpinner, firstPassengerSecondSpinner;
    private EditText firstPassengerFirstName, firstPassengerLastName, firstPassengerEmail, firstPassengerMobileNumber, firstPassengerAddress, firstPassengerReference;
    private Spinner secondPassengerFirstSpinner, secondPassengerSecondSpinner;
    private EditText secondPassengerFirstName, secondPassengerLastName;
    private TextView secondPassengerDateOfBirth;
    private Spinner thirdPassengerFirstSpinner, thirdPassengerSecondSpinner;
    private EditText thirdPassengerFirstName, thirdPassengerLastName;
    private TextView thirdPassengerDateOfBirth;
    private Spinner fourthPassengerFirstSpinner, fourthPassengerSecondSpinner;
    private EditText fourthPassengerFirstName, fourthPassengerLastName;
    private TextView fourthPassengerDateOfBirth;
    private Spinner fifthPassengerFirstSpinner, fifthPassengerSecondSpinner;
    private EditText fifthPassengerFirstName, fifthPassengerLastName;
    private TextView fifthPassengerDateOfBirth;
    private Spinner sixthPassengerFirstSpinner, sixthPassengerSecondSpinner;
    private EditText sixthPassengerFirstName, sixthPassengerLastName;
    private TextView sixthPassengerDateOfBirth;
    private Spinner seventhPassengerFirstSpinner, seventhPassengerSecondSpinner;
    private EditText seventhPassengerFirstName, seventhPassengerLastName;
    private TextView seventhPassengerDateOfBirth;
    private Spinner eighthPassengerFirstSpinner, eighthPassengerSecondSpinner;
    private EditText eighthPassengerFirstName, eighthPassengerLastName;
    private TextView eighthPassengerDateOfBirth;
    private Spinner ninthPassengerFirstSpinner, ninthPassengerSecondSpinner;
    private EditText ninthPassengerFirstName, ninthPassengerLastName;
    private TextView ninthPassengerDateOfBirth;
    private TextView FirstPassengerText, SecondPassengerText, ThirdPassengerText, FourthPassengerText, FifthPassengerText, SixthPassengerText, SeventhPassengerText, EighthPassengerText, NinthPassengerText;
    private LinearLayout FirstPassengerLayoutClick, SecondPassengerLayoutClick, ThirdPassengerLayoutClick, FourthPassengerLayoutClick, FifthPassengerLayoutClick, SixthPassengerLayoutClick, SeventhPassengerLayoutClick, EighthPassengerLayoutClick, NinthPassengerLayoutClick;
    private boolean First = false, second = false, third = false, fourth = false, fifth = false, sixth = false, seventh = false, eighth = false, ninth = false;
    private ArrayList<LinearLayout> arrayListLayout = new ArrayList<>();
    private ArrayList<TextView> arrayListTextView = new ArrayList<>();
    private ArrayList<String> AdultString = new ArrayList<>();
    private ArrayList<String> ChildrenString = new ArrayList<>();
    private ArrayList<String> InfantString = new ArrayList<>();
    private ArrayList<TextView> CalendarLayout = new ArrayList<>();
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_details);
        setToolbar();
        getIntentValues();
        initializeVariables();
        arryListAddValues();
        contentVisibleOrGoneByDefault();
        // this method is for which layout gone to visible and which is invisible
        setVisibleOrNot();
        contentOnClickListener();
        activity = PassengerDetails.this;
        bothLocation.setText(OneWay.passengerOriginInFull.getText().toString().concat(" To ").concat(OneWay.passengerDestinationInFull.getText().toString()));
        LocationOrigin.setText(OneWay.passengerOriginInFull.getText().toString());
        LocationDestination.setText(OneWay.passengerDestinationInFull.getText().toString());
        position = getIntent().getIntExtra("position", 0);
        fromAdapter(position);
        walletCredit.setChecked(true);
    }

    private void arryListAddValues() {
        arrayListLayout.add(FirstPassengerLayoutClick);
        arrayListLayout.add(SecondPassengerLayoutClick);
        arrayListLayout.add(ThirdPassengerLayoutClick);
        arrayListLayout.add(FourthPassengerLayoutClick);
        arrayListLayout.add(FifthPassengerLayoutClick);
        arrayListLayout.add(SixthPassengerLayoutClick);
        arrayListLayout.add(SeventhPassengerLayoutClick);
        arrayListLayout.add(EighthPassengerLayoutClick);
        arrayListLayout.add(NinthPassengerLayoutClick);
        arrayListTextView.add(FirstPassengerText);
        arrayListTextView.add(SecondPassengerText);
        arrayListTextView.add(ThirdPassengerText);
        arrayListTextView.add(FourthPassengerText);
        arrayListTextView.add(FifthPassengerText);
        arrayListTextView.add(SixthPassengerText);
        arrayListTextView.add(SeventhPassengerText);
        arrayListTextView.add(EighthPassengerText);
        arrayListTextView.add(NinthPassengerText);
        AdultString.add(getString(R.string.first_adults));
        AdultString.add(getString(R.string.second_adults));
        AdultString.add(getString(R.string.third_adults));
        AdultString.add(getString(R.string.fourth_adults));
        AdultString.add(getString(R.string.fifth_adults));
        AdultString.add(getString(R.string.sixth_adults));
        AdultString.add(getString(R.string.seventh_adults));
        AdultString.add(getString(R.string.eighth_adults));
        AdultString.add(getString(R.string.ninth_adults));
        ChildrenString.add(getString(R.string.first_children));
        ChildrenString.add(getString(R.string.second_children));
        ChildrenString.add(getString(R.string.third_children));
        ChildrenString.add(getString(R.string.fourth_children));
        ChildrenString.add(getString(R.string.fifth_children));
        ChildrenString.add(getString(R.string.sixth_children));
        ChildrenString.add(getString(R.string.seventh_children));
        ChildrenString.add(getString(R.string.eighth_children));
        ChildrenString.add(getString(R.string.ninth_children));
        InfantString.add(getString(R.string.first_infant));
        InfantString.add(getString(R.string.second_infant));
        InfantString.add(getString(R.string.third_infant));
        InfantString.add(getString(R.string.fourth_infant));
        InfantString.add(getString(R.string.fifth_infant));
        InfantString.add(getString(R.string.sixth_infant));
        InfantString.add(getString(R.string.seventh_infant));
        InfantString.add(getString(R.string.eighth_infants));
        InfantString.add(getString(R.string.ninth_infants));
        CalendarLayout.add(secondPassengerDateOfBirth);
        CalendarLayout.add(thirdPassengerDateOfBirth);
        CalendarLayout.add(fourthPassengerDateOfBirth);
        CalendarLayout.add(fifthPassengerDateOfBirth);
        CalendarLayout.add(sixthPassengerDateOfBirth);
        CalendarLayout.add(seventhPassengerDateOfBirth);
        CalendarLayout.add(eighthPassengerDateOfBirth);
        CalendarLayout.add(ninthPassengerDateOfBirth);
        NameOfPassenger.add(secondPassengerFirstName);
        NameOfPassenger.add(thirdPassengerFirstName);
        NameOfPassenger.add(fourthPassengerFirstName);
        NameOfPassenger.add(fifthPassengerFirstName);
        NameOfPassenger.add(sixthPassengerFirstName);
        NameOfPassenger.add(seventhPassengerFirstName);
        NameOfPassenger.add(eighthPassengerFirstName);
        NameOfPassenger.add(ninthPassengerFirstName);
        LastNameOfPassenger.add(secondPassengerLastName);
        LastNameOfPassenger.add(thirdPassengerLastName);
        LastNameOfPassenger.add(fourthPassengerLastName);
        LastNameOfPassenger.add(fifthPassengerLastName);
        LastNameOfPassenger.add(sixthPassengerLastName);
        LastNameOfPassenger.add(seventhPassengerLastName);
        LastNameOfPassenger.add(eighthPassengerLastName);
        LastNameOfPassenger.add(ninthPassengerLastName);
        DateOfBirthPassenger.add(secondPassengerDateOfBirth);
        DateOfBirthPassenger.add(thirdPassengerDateOfBirth);
        DateOfBirthPassenger.add(fourthPassengerDateOfBirth);
        DateOfBirthPassenger.add(fifthPassengerDateOfBirth);
        DateOfBirthPassenger.add(sixthPassengerDateOfBirth);
        DateOfBirthPassenger.add(seventhPassengerDateOfBirth);
        DateOfBirthPassenger.add(eighthPassengerDateOfBirth);
        DateOfBirthPassenger.add(ninthPassengerDateOfBirth);


    }

    private void setVisibleOrNot() {
        for (int i = 0; i < adults; i++) {
            arrayListLayout.get(i).setVisibility(View.VISIBLE);
            arrayListTextView.get(i).setText(AdultString.get(i));
        }
        for (int j = adults, i = 0; j < adults + children; j++, i++) {
            arrayListLayout.get(j).setVisibility(View.VISIBLE);
            arrayListTextView.get(j).setText(ChildrenString.get(i));
            CalendarLayout.get(j - 1).setVisibility(View.VISIBLE);
        }
        for (int j = adults + children, i = 0; j < adults + children + infants; j++, i++) {
            arrayListLayout.get(j).setVisibility(View.VISIBLE);
            arrayListTextView.get(j).setText(InfantString.get(i));
            CalendarLayout.get(j - 1).setVisibility(View.VISIBLE);
        }
    }

    private void contentOnClickListener() {
        FirstPassengerLayoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!First) {
                    passengerFirstLayout.setVisibility(View.GONE);
                    First = true;
                } else {

                    passengerFirstLayout.setVisibility(View.VISIBLE);
                    First = false;
                }

            }
        });
        SecondPassengerLayoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, v.getHeight());
                translateAnimation.setDuration(500);
                translateAnimation.setFillAfter(true);

                if (!second) {
                    // v.startAnimation(translateAnimation);
                    passengerSecondLayout.setVisibility(View.VISIBLE);
                    second = true;
                } else {
                    passengerSecondLayout.setVisibility(View.GONE);
                    second = false;
                }

            }
        });
        ThirdPassengerLayoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!third) {
                    passengerThirdLayout.setVisibility(View.VISIBLE);
                    third = true;
                } else {
                    passengerThirdLayout.setVisibility(View.GONE);
                    third = false;
                }

            }
        });
        FourthPassengerLayoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fourth) {
                    passengerFourthLayout.setVisibility(View.VISIBLE);
                    fourth = true;
                } else {
                    passengerFourthLayout.setVisibility(View.GONE);
                    fourth = false;
                }

            }
        });
        FifthPassengerLayoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fifth) {
                    passengerFifthLayout.setVisibility(View.VISIBLE);
                    fifth = true;
                } else {
                    passengerFifthLayout.setVisibility(View.GONE);
                    fifth = false;
                }

            }
        });
        SixthPassengerLayoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sixth) {
                    passengerSixthLayout.setVisibility(View.VISIBLE);
                    sixth = true;
                } else {
                    passengerSixthLayout.setVisibility(View.GONE);
                    sixth = false;
                }

            }
        });
        SeventhPassengerLayoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!seventh) {
                    passengerSeventhLayout.setVisibility(View.VISIBLE);
                    seventh = true;
                } else {
                    passengerSeventhLayout.setVisibility(View.GONE);
                    seventh = false;
                }

            }
        });
        EighthPassengerLayoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!eighth) {
                    passengerEighthLayout.setVisibility(View.VISIBLE);
                    eighth = true;
                } else {
                    passengerEighthLayout.setVisibility(View.GONE);
                    eighth = false;
                }

            }
        });
        NinthPassengerLayoutClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ninth) {
                    passengerNinthLayout.setVisibility(View.VISIBLE);
                    ninth = true;
                } else {
                    passengerNinthLayout.setVisibility(View.GONE);
                    ninth = false;
                }

            }
        });
        final int year, month, day;
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        secondPassengerDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PassengerDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String monthYear;
                        if (month == 0)
                            monthYear = getString(R.string.january);
                        else if (month == 1)
                            monthYear = getString(R.string.fabruary);
                        else if (month == 2)
                            monthYear = getString(R.string.march);
                        else if (month == 3)
                            monthYear = getString(R.string.april);
                        else if (month == 4)
                            monthYear = getString(R.string.may);
                        else if (month == 5)
                            monthYear = getString(R.string.jun);
                        else if (month == 6)
                            monthYear = getString(R.string.july);
                        else if (month == 7)
                            monthYear = getString(R.string.august);
                        else if (month == 8)
                            monthYear = getString(R.string.september);
                        else if (month == 9)
                            monthYear = getString(R.string.october);
                        else if (month == 10)
                            monthYear = getString(R.string.november);
                        else
                            monthYear = getString(R.string.december);
                        secondPassengerDateOfBirth.setText(dayOfMonth + getString(R.string.date_partition) + monthYear + getString(R.string.date_partition) + year);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        thirdPassengerDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PassengerDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String monthYear;
                        if (month == 0)
                            monthYear = getString(R.string.january);
                        else if (month == 1)
                            monthYear = getString(R.string.fabruary);
                        else if (month == 2)
                            monthYear = getString(R.string.march);
                        else if (month == 3)
                            monthYear = getString(R.string.april);
                        else if (month == 4)
                            monthYear = getString(R.string.may);
                        else if (month == 5)
                            monthYear = getString(R.string.jun);
                        else if (month == 6)
                            monthYear = getString(R.string.july);
                        else if (month == 7)
                            monthYear = getString(R.string.august);
                        else if (month == 8)
                            monthYear = getString(R.string.september);
                        else if (month == 9)
                            monthYear = getString(R.string.october);
                        else if (month == 10)
                            monthYear = getString(R.string.november);
                        else
                            monthYear = getString(R.string.december);
                        thirdPassengerDateOfBirth.setText(dayOfMonth + getString(R.string.date_partition) + monthYear + getString(R.string.date_partition) + year);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }

        });
        fourthPassengerDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PassengerDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String monthYear;
                        if (month == 0)
                            monthYear = getString(R.string.january);
                        else if (month == 1)
                            monthYear = getString(R.string.fabruary);
                        else if (month == 2)
                            monthYear = getString(R.string.march);
                        else if (month == 3)
                            monthYear = getString(R.string.april);
                        else if (month == 4)
                            monthYear = getString(R.string.may);
                        else if (month == 5)
                            monthYear = getString(R.string.jun);
                        else if (month == 6)
                            monthYear = getString(R.string.july);
                        else if (month == 7)
                            monthYear = getString(R.string.august);
                        else if (month == 8)
                            monthYear = getString(R.string.september);
                        else if (month == 9)
                            monthYear = getString(R.string.october);
                        else if (month == 10)
                            monthYear = getString(R.string.november);
                        else
                            monthYear = getString(R.string.december);
                        fourthPassengerDateOfBirth.setText(dayOfMonth + getString(R.string.date_partition) + monthYear + getString(R.string.date_partition) + year);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        fifthPassengerDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PassengerDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String monthYear;
                        if (month == 0)
                            monthYear = getString(R.string.january);
                        else if (month == 1)
                            monthYear = getString(R.string.fabruary);
                        else if (month == 2)
                            monthYear = getString(R.string.march);
                        else if (month == 3)
                            monthYear = getString(R.string.april);
                        else if (month == 4)
                            monthYear = getString(R.string.may);
                        else if (month == 5)
                            monthYear = getString(R.string.jun);
                        else if (month == 6)
                            monthYear = getString(R.string.july);
                        else if (month == 7)
                            monthYear = getString(R.string.august);
                        else if (month == 8)
                            monthYear = getString(R.string.september);
                        else if (month == 9)
                            monthYear = getString(R.string.october);
                        else if (month == 10)
                            monthYear = getString(R.string.november);
                        else
                            monthYear = getString(R.string.december);
                        fifthPassengerDateOfBirth.setText(dayOfMonth + getString(R.string.date_partition) + monthYear + getString(R.string.date_partition) + year);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        sixthPassengerDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PassengerDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String monthYear;
                        if (month == 0)
                            monthYear = getString(R.string.january);
                        else if (month == 1)
                            monthYear = getString(R.string.fabruary);
                        else if (month == 2)
                            monthYear = getString(R.string.march);
                        else if (month == 3)
                            monthYear = getString(R.string.april);
                        else if (month == 4)
                            monthYear = getString(R.string.may);
                        else if (month == 5)
                            monthYear = getString(R.string.jun);
                        else if (month == 6)
                            monthYear = getString(R.string.july);
                        else if (month == 7)
                            monthYear = getString(R.string.august);
                        else if (month == 8)
                            monthYear = getString(R.string.september);
                        else if (month == 9)
                            monthYear = getString(R.string.october);
                        else if (month == 10)
                            monthYear = getString(R.string.november);
                        else
                            monthYear = getString(R.string.december);
                        sixthPassengerDateOfBirth.setText(dayOfMonth + getString(R.string.date_partition) + monthYear + getString(R.string.date_partition) + year);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        seventhPassengerDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PassengerDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String monthYear;
                        if (month == 0)
                            monthYear = getString(R.string.january);
                        else if (month == 1)
                            monthYear = getString(R.string.fabruary);
                        else if (month == 2)
                            monthYear = getString(R.string.march);
                        else if (month == 3)
                            monthYear = getString(R.string.april);
                        else if (month == 4)
                            monthYear = getString(R.string.may);
                        else if (month == 5)
                            monthYear = getString(R.string.jun);
                        else if (month == 6)
                            monthYear = getString(R.string.july);
                        else if (month == 7)
                            monthYear = getString(R.string.august);
                        else if (month == 8)
                            monthYear = getString(R.string.september);
                        else if (month == 9)
                            monthYear = getString(R.string.october);
                        else if (month == 10)
                            monthYear = getString(R.string.november);
                        else
                            monthYear = getString(R.string.december);
                        seventhPassengerDateOfBirth.setText(dayOfMonth + getString(R.string.date_partition) + monthYear + getString(R.string.date_partition) + year);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        eighthPassengerDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PassengerDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String monthYear;
                        if (month == 0)
                            monthYear = getString(R.string.january);
                        else if (month == 1)
                            monthYear = getString(R.string.fabruary);
                        else if (month == 2)
                            monthYear = getString(R.string.march);
                        else if (month == 3)
                            monthYear = getString(R.string.april);
                        else if (month == 4)
                            monthYear = getString(R.string.may);
                        else if (month == 5)
                            monthYear = getString(R.string.jun);
                        else if (month == 6)
                            monthYear = getString(R.string.july);
                        else if (month == 7)
                            monthYear = getString(R.string.august);
                        else if (month == 8)
                            monthYear = getString(R.string.september);
                        else if (month == 9)
                            monthYear = getString(R.string.october);
                        else if (month == 10)
                            monthYear = getString(R.string.november);
                        else
                            monthYear = getString(R.string.december);
                        eighthPassengerDateOfBirth.setText(dayOfMonth + getString(R.string.date_partition) + monthYear + getString(R.string.date_partition) + year);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        ninthPassengerDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PassengerDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String monthYear;
                        if (month == 0)
                            monthYear = getString(R.string.january);
                        else if (month == 1)
                            monthYear = getString(R.string.fabruary);
                        else if (month == 2)
                            monthYear = getString(R.string.march);
                        else if (month == 3)
                            monthYear = getString(R.string.april);
                        else if (month == 4)
                            monthYear = getString(R.string.may);
                        else if (month == 5)
                            monthYear = getString(R.string.jun);
                        else if (month == 6)
                            monthYear = getString(R.string.july);
                        else if (month == 7)
                            monthYear = getString(R.string.august);
                        else if (month == 8)
                            monthYear = getString(R.string.september);
                        else if (month == 9)
                            monthYear = getString(R.string.october);
                        else if (month == 10)
                            monthYear = getString(R.string.november);
                        else
                            monthYear = getString(R.string.december);
                        ninthPassengerDateOfBirth.setText(dayOfMonth + getString(R.string.date_partition) + monthYear + getString(R.string.date_partition) + year);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
    }

    private void contentVisibleOrGoneByDefault() {
        // all layout except are by default invisible
        SecondPassengerLayoutClick.setVisibility(View.GONE);
        ThirdPassengerLayoutClick.setVisibility(View.GONE);
        FourthPassengerLayoutClick.setVisibility(View.GONE);
        FifthPassengerLayoutClick.setVisibility(View.GONE);
        SixthPassengerLayoutClick.setVisibility(View.GONE);
        SeventhPassengerLayoutClick.setVisibility(View.GONE);
        EighthPassengerLayoutClick.setVisibility(View.GONE);
        NinthPassengerLayoutClick.setVisibility(View.GONE);
        passengerSecondLayout.setVisibility(View.GONE);
        passengerThirdLayout.setVisibility(View.GONE);
        passengerFourthLayout.setVisibility(View.GONE);
        passengerFifthLayout.setVisibility(View.GONE);
        passengerSixthLayout.setVisibility(View.GONE);
        passengerSeventhLayout.setVisibility(View.GONE);
        passengerEighthLayout.setVisibility(View.GONE);
        passengerNinthLayout.setVisibility(View.GONE);
        secondPassengerDateOfBirth.setVisibility(View.GONE);
        thirdPassengerDateOfBirth.setVisibility(View.GONE);
        fourthPassengerDateOfBirth.setVisibility(View.GONE);
        fifthPassengerDateOfBirth.setVisibility(View.GONE);
        sixthPassengerDateOfBirth.setVisibility(View.GONE);
        seventhPassengerDateOfBirth.setVisibility(View.GONE);
        eighthPassengerDateOfBirth.setVisibility(View.GONE);
        ninthPassengerDateOfBirth.setVisibility(View.GONE);

    }

    private void getIntentValues() {

        adults = Integer.valueOf(OneWay.AdultNumber.getText().toString());
        children = Integer.valueOf(OneWay.ChildrenNumber.getText().toString());
        infants = Integer.valueOf(OneWay.InfantsNumber.getText().toString());


    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("TRAVELER DETAIL");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private boolean checkValidation(String s,int layoutPosition) {
        if (s.contains("Adults")) {
            int i;
            for (i = 0; i < 9; i++) {
                if (AdultString.get(i).toString().equals(s)) {
                    break;
                }
            }

            if (TextUtils.isEmpty(NameOfPassenger.get(i-1).getText().toString())) {
                Snackbar.make(findViewById(android.R.id.content),"Fill First Name Of "+AdultString.get(i).toString(),Snackbar.LENGTH_LONG).show();
                //Toast.makeText(activity, "Fill First Name Of "+AdultString.get(i).toString(), Toast.LENGTH_SHORT).show();
                  return false;
            }
            if (TextUtils.isEmpty(LastNameOfPassenger.get(i-1).getText().toString())) {
                Snackbar.make(findViewById(android.R.id.content),"Fill Last First Name Of "+AdultString.get(i).toString(),Snackbar.LENGTH_LONG).show();

                //Toast.makeText(activity, "Fill Last First Name Of "+AdultString.get(i).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        } else if (s.contains("Children")) {
            int i;
            for (i = 0; i < 9; i++) {
                if (ChildrenString.get(i).toString().equals(s)) {
                    break;
                }
            }
            if (TextUtils.isEmpty(NameOfPassenger.get(i-1).getText().toString())) {
                Snackbar.make(findViewById(android.R.id.content),"Fill First Name Of "+ChildrenString.get(layoutPosition).toString(),Snackbar.LENGTH_LONG).show();

               // Toast.makeText(activity, "Fill First Name Of "+ChildrenString.get(layoutPosition).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(LastNameOfPassenger.get(i-1).getText().toString())) {
                Snackbar.make(findViewById(android.R.id.content),"Fill Last First Name Of "+ChildrenString.get(layoutPosition).toString(),Snackbar.LENGTH_LONG).show();

               // Toast.makeText(activity, "Fill Last First Name Of "+ChildrenString.get(layoutPosition).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(DateOfBirthPassenger.get(i-1).getText().toString())) {
                Snackbar.make(findViewById(android.R.id.content),"Fill Date Of Birth Name Of "+ChildrenString.get(layoutPosition).toString(),Snackbar.LENGTH_LONG).show();

             //   Toast.makeText(activity, "Fill Date Of Birth Name Of "+ChildrenString.get(layoutPosition).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        } else if (s.contains("Infant")) {
            int i;
            for (i = 0; i < 9; i++) {
                if (InfantString.get(i).toString().equals(s)) {
                    break;
                }
            }
            if (TextUtils.isEmpty(NameOfPassenger.get(i-1).getText().toString())) {
                Snackbar.make(findViewById(android.R.id.content),"Fill First Name Of "+InfantString.get(layoutPosition).toString(),Snackbar.LENGTH_LONG).show();

               // Toast.makeText(activity, "Fill First Name Of "+InfantString.get(layoutPosition).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(LastNameOfPassenger.get(i-1).getText().toString())) {
                Snackbar.make(findViewById(android.R.id.content),"Fill Last First Name Of "+InfantString.get(layoutPosition).toString(),Snackbar.LENGTH_LONG).show();

             //   Toast.makeText(activity, "Fill Last First Name Of "+InfantString.get(layoutPosition).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(DateOfBirthPassenger.get(i-1).getText().toString())) {
                Snackbar.make(findViewById(android.R.id.content),"Fill Date Of Birth Name Of "+InfantString.get(layoutPosition).toString(),Snackbar.LENGTH_LONG).show();

            //    Toast.makeText(activity, "Fill Date Of Birth Name Of "+InfantString.get(layoutPosition).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void initializeVariables() {
        passengerFirstLayout = findViewById(R.id.personal_details);
        passengerSecondLayout = findViewById(R.id.personal_details2);
        passengerThirdLayout = findViewById(R.id.personal_details3);
        passengerFourthLayout = findViewById(R.id.personal_details4);
        passengerFifthLayout = findViewById(R.id.personal_details5);
        passengerSixthLayout = findViewById(R.id.personal_details6);
        passengerSeventhLayout = findViewById(R.id.personal_details7);
        passengerEighthLayout = findViewById(R.id.personal_details8);
        passengerNinthLayout = findViewById(R.id.personal_details9);
        FirstPassengerText = findViewById(R.id.passenger_id1);
        SecondPassengerText = findViewById(R.id.passenger_id2);
        ThirdPassengerText = findViewById(R.id.passenger_id3);
        FourthPassengerText = findViewById(R.id.passenger_id4);
        FifthPassengerText = findViewById(R.id.passenger_id5);
        SixthPassengerText = findViewById(R.id.passenger_id6);
        SeventhPassengerText = findViewById(R.id.passenger_id7);
        EighthPassengerText = findViewById(R.id.passenger_id8);
        NinthPassengerText = findViewById(R.id.passenger_id9);
        FirstPassengerLayoutClick = findViewById(R.id.passenger_id1_layout);
        SecondPassengerLayoutClick = findViewById(R.id.passenger_id2_layout);
        ThirdPassengerLayoutClick = findViewById(R.id.passenger_id3_layout);
        FourthPassengerLayoutClick = findViewById(R.id.passenger_id4_layout);
        FifthPassengerLayoutClick = findViewById(R.id.passenger_id5_layout);
        SixthPassengerLayoutClick = findViewById(R.id.passenger_id6_layout);
        SeventhPassengerLayoutClick = findViewById(R.id.passenger_id7_layout);
        EighthPassengerLayoutClick = findViewById(R.id.passenger_id8_layout);
        NinthPassengerLayoutClick = findViewById(R.id.passenger_id9_layout);
        firstPassengerFirstSpinner = findViewById(R.id.spinner_person_title);
        firstPassengerSecondSpinner = findViewById(R.id.spinner_gender);
        secondPassengerFirstSpinner = findViewById(R.id.spinner_person_title2);
        secondPassengerSecondSpinner = findViewById(R.id.spinner_gender2);
        thirdPassengerFirstSpinner = findViewById(R.id.spinner_person_title3);
        thirdPassengerSecondSpinner = findViewById(R.id.spinner_gender3);
        fourthPassengerFirstSpinner = findViewById(R.id.spinner_person_title4);
        fourthPassengerSecondSpinner = findViewById(R.id.spinner_gender4);
        fifthPassengerFirstSpinner = findViewById(R.id.spinner_person_title5);
        fifthPassengerSecondSpinner = findViewById(R.id.spinner_gender5);
        sixthPassengerFirstSpinner = findViewById(R.id.spinner_person_title6);
        sixthPassengerSecondSpinner = findViewById(R.id.spinner_gender6);
        seventhPassengerFirstSpinner = findViewById(R.id.spinner_person_title7);
        seventhPassengerSecondSpinner = findViewById(R.id.spinner_gender7);
        eighthPassengerFirstSpinner = findViewById(R.id.spinner_person_title8);
        eighthPassengerSecondSpinner = findViewById(R.id.spinner_gender8);
        ninthPassengerFirstSpinner = findViewById(R.id.spinner_person_title9);
        ninthPassengerSecondSpinner = findViewById(R.id.spinner_gender9);
        firstPassengerFirstName = findViewById(R.id.personal_detail_first_name);
        firstPassengerLastName = findViewById(R.id.personal_detail_last_name);
        firstPassengerEmail = findViewById(R.id.personal_detail_email_id);
        firstPassengerMobileNumber = findViewById(R.id.personal_detail_mobile_number);
        firstPassengerAddress = findViewById(R.id.personal_detail_address);
        firstPassengerReference = findViewById(R.id.personal_detail_reference);
        secondPassengerFirstName = findViewById(R.id.personal_detail_first_name2);
        secondPassengerLastName = findViewById(R.id.personal_detail_last_name2);
        secondPassengerDateOfBirth = findViewById(R.id.date_of_birth2);
        thirdPassengerFirstName = findViewById(R.id.personal_detail_first_name3);
        thirdPassengerLastName = findViewById(R.id.personal_detail_last_name3);
        thirdPassengerDateOfBirth = findViewById(R.id.date_of_birth3);
        fourthPassengerFirstName = findViewById(R.id.personal_detail_first_name4);
        fourthPassengerLastName = findViewById(R.id.personal_detail_last_name4);
        fourthPassengerDateOfBirth = findViewById(R.id.date_of_birth4);
        fifthPassengerFirstName = findViewById(R.id.personal_detail_first_name5);
        fifthPassengerLastName = findViewById(R.id.personal_detail_last_name5);
        fifthPassengerDateOfBirth = findViewById(R.id.date_of_birth5);
        sixthPassengerFirstName = findViewById(R.id.personal_detail_first_name6);
        sixthPassengerLastName = findViewById(R.id.personal_detail_last_name6);
        sixthPassengerDateOfBirth = findViewById(R.id.date_of_birth6);
        seventhPassengerFirstName = findViewById(R.id.personal_detail_first_name7);
        seventhPassengerLastName = findViewById(R.id.personal_detail_last_name7);
        seventhPassengerDateOfBirth = findViewById(R.id.date_of_birth7);
        eighthPassengerFirstName = findViewById(R.id.personal_detail_first_name8);
        eighthPassengerLastName = findViewById(R.id.personal_detail_last_name8);
        eighthPassengerDateOfBirth = findViewById(R.id.date_of_birth8);
        ninthPassengerFirstName = findViewById(R.id.personal_detail_first_name9);
        ninthPassengerLastName = findViewById(R.id.personal_detail_last_name9);
        ninthPassengerDateOfBirth = findViewById(R.id.date_of_birth9);
        bothLocation = findViewById(R.id.bothLocations);
        flightIcon = findViewById(R.id.icon_flight_passenger);
        AirWayName = findViewById(R.id.airways_name_passenger);
        FlightNumber = findViewById(R.id.fight_number);
        LocationOrigin = findViewById(R.id.location_origin);
        OriginDate = findViewById(R.id.origin_date);
        OriginTime = findViewById(R.id.origin_time);
        OriginAirPortName = findViewById(R.id.origin_airport_name);
        OriginAirportTerminal = findViewById(R.id.origin_airport_terminal);
        durationBetweenLocation = findViewById(R.id.duration_between_location);
        LocationDestination = findViewById(R.id.destination_location);
        DestinationDate = findViewById(R.id.destination_date);
        DestinationTime = findViewById(R.id.destination_time);
        DestinationAirPortName = findViewById(R.id.destination_airport_name);
        DestinationTerminal = findViewById(R.id.destination_airport_terminal);
        basePrice = findViewById(R.id.base_price_price);
        texPrice = findViewById(R.id.tex_price_price);
        totalPrice = findViewById(R.id.total_price_price);
        walletCredit = findViewById(R.id.wallet_credit);
        Ebs = findViewById(R.id.ebs);
        NameOfPassenger = new ArrayList<>();
        LastNameOfPassenger = new ArrayList<>();
        DateOfBirthPassenger = new ArrayList<>();
    }


    //toolbar press back arrow button

    private void fromAdapter(int position) {
        AirWayName.setText(FlightSearchResult.results.get(0).get(position).getSegments().get(0).get(0).getAirline().getAirlineName().toString());
        FlightNumber.setText(FlightSearchResult.results.get(0).get(position).getSegments().get(0).get(0).getAirline().getAirlineCode().toString().concat("-" + FlightSearchResult.results.get(0).get(position).getSegments().get(0).get(0).getAirline().getFlightNumber().toString()));
        String s = FlightSearchResult.results.get(0).get(position).getSegments().get(0).get(0).getOrigin().getDepTime().toString();
        String s1 = s.substring(s.indexOf("T") + 1);
        OriginTime.setText(s1.toString());
        OriginAirPortName.setText(FlightSearchResult.results.get(0).get(position).getSegments().get(0).get(0).getOrigin().getAirport().getAirportName().toString());
        OriginAirportTerminal.setText((FlightSearchResult.results.get(0).get(position).getSegments().get(0).get(0).getOrigin().getAirport().getTerminal().toString()) + " Terminal");
        String time = (FlightSearchResult.results.get(0).get(position).getSegments().get(0).get(0).getDuration().toString());
        int i = Integer.valueOf(time);
        if (i < 60)
            durationBetweenLocation.setText(String.valueOf(i).concat("m"));
        else {
            int h = i / 60, m = i % 60;
            durationBetweenLocation.setText(String.valueOf(h).concat("h:").concat(String.valueOf(m).concat("m")));
        }
        String y = FlightSearchResult.results.get(0).get(position).getSegments().get(0).get(0).getDestination().getArrTime().toString();
        String y1 = y.substring(y.indexOf("T") + 1);
        DestinationTime.setText(y1.toString());
        DestinationAirPortName.setText(FlightSearchResult.results.get(0).get(position).getSegments().get(0).get(0).getDestination().getAirport().getAirportName().toString());
        DestinationTerminal.setText((FlightSearchResult.results.get(0).get(position).getSegments().get(0).get(0).getDestination().getAirport().getTerminal().toString()) + " Terminal");
        OriginDate.setText(s.substring(0, s.indexOf("T")).toString());
        DestinationDate.setText(y.substring(0, y.indexOf("T")).toString());
        final String imageUrl = "http://traveller.projectnyou.com/public/images/flight/flight_logo/" + FlightSearchResult.results.get(0).get(position).getSegments().get(0).get(0).getAirline().getAirlineCode() + ".png";
        Picasso.with(PassengerDetails.this).load(imageUrl).into(flightIcon);
        basePrice.setText("₹ " + FlightSearchResult.results.get(0).get(position).getFare().getBaseFare().toString());
        texPrice.setText("₹ " + FlightSearchResult.results.get(0).get(position).getFare().getTax().toString());
        totalPrice.setText("₹ " + String.valueOf(FlightSearchResult.results.get(0).get(position).getFare().getBaseFare() + FlightSearchResult.results.get(0).get(position).getFare().getTax() + 150));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void ProceedToPayment(View view) {
        int validatenumber=0;
        boolean name=false,lastname=false,eamil=false,mobnum=false,address=false;
        if(TextUtils.isEmpty(firstPassengerFirstName.getText().toString()))
        {
            Snackbar.make(findViewById(android.R.id.content),"Fill First Name Of First Adult",Snackbar.LENGTH_LONG).show();
            return ;
        }
        else name=true;
        if(TextUtils.isEmpty(firstPassengerLastName.getText().toString()))
        {
            Snackbar.make(findViewById(android.R.id.content),"Fill last Name Of First Adult",Snackbar.LENGTH_LONG).show();
            return ;
        }
        else lastname=true;
        if(TextUtils.isEmpty(firstPassengerEmail.getText().toString()))
        {
            Snackbar.make(findViewById(android.R.id.content),"Fill Email-Id Of First Adult",Snackbar.LENGTH_LONG).show();
            return ;
        }
        else eamil=true;
        String validContactNumber = "[0-9]{10,13}";
        String validEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                "\\@" +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" +

                "\\." +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                ")+";
        Matcher matcher= Pattern.compile(validEmail).matcher(firstPassengerEmail.getText().toString());
        if(matcher.matches())
        {
            Snackbar.make(findViewById(android.R.id.content),"Fill Valid Email-Id Of First Adult",Snackbar.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(firstPassengerMobileNumber.getText().toString()))
        {
            Snackbar.make(findViewById(android.R.id.content),"Fill Contact Number Of First Adult",Snackbar.LENGTH_LONG).show();
            return ;
        }
        else mobnum=true;
        matcher=Pattern.compile(validContactNumber).matcher(firstPassengerMobileNumber.getText().toString());
        if(TextUtils.isEmpty(firstPassengerMobileNumber.getText().toString()))
        {
            Snackbar.make(findViewById(android.R.id.content),"Fill Valid Contact Number Of First Adult",Snackbar.LENGTH_LONG).show();
            return ;
        }
        if(TextUtils.isEmpty(firstPassengerAddress.getText().toString()))
        {
            Snackbar.make(findViewById(android.R.id.content),"Fill Address Of First Adult",Snackbar.LENGTH_LONG).show();
            return ;
        }
        else address=true;
        if(name&&lastname&&eamil&&mobnum&&address)
        {
            validatenumber++;
        }
        for (int i = 1; i < adults; i++) {
            if (checkValidation(AdultString.get(i).toString(),i)) {
                validatenumber++;
            }
            else
            {
                return;
            }

        }
        for (int j = adults, i = 0; j < adults + children; j++, i++) {
            if(checkValidation(ChildrenString.get(j).toString(),i))
            {
                validatenumber++;
            }
            else
            {
                return;
            }
        }
        for (int j = adults + children, i = 0; j < adults + children + infants; j++, i++) {
            if(checkValidation(InfantString.get(j).toString(),i))
            {
                validatenumber++;
            }
            else
                return;
        }
        if(validatenumber==adults+children+infants)
        {
            Toast.makeText(activity, "All Fields are Validate", Toast.LENGTH_SHORT).show();
        }

    }
}
