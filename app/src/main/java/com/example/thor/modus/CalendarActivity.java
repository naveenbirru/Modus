package com.example.thor.modus;

import android.content.Context;
import android.icu.text.IDNA;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarActivity extends AppCompatActivity {

    private ListView mainListView ;
    private ArrayList<FoodItem> foodItems;
    private ArrayAdapter<FoodItemAdapter> foodItemAdapter ;

    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        final CalendarView cv = findViewById(R.id.calendarView);
        // Find the ListView resource.
        mainListView = (ListView) findViewById( R.id.explistView );
        final FoodDbAdapter dbAdapter = new FoodDbAdapter(this.getBaseContext());




        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {


                String string_date = month+1+"-"+dayOfMonth+"-"+year;
                date = string_date;


                dbAdapter.open();
                Log.i("date: ", date.toString());

                foodItems = dbAdapter.getFoodItemsByDate(date);
                dbAdapter.close();


                foodItemAdapter = new FoodItemAdapter(CalendarActivity.this,foodItems);
                // Set the ArrayAdapter as the ListView's adapter.
                mainListView.setAdapter( foodItemAdapter );



            }
        });





    }

}
