package com.example.thor.modus;

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
    private Long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        final CalendarView cv = findViewById(R.id.calendarView);


        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                //date = cv.getDate();


                Toast.makeText(view.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();

                String string_date = dayOfMonth+"-"+month+"-"+year;

                SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date d = f.parse(string_date);
                    date = d.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.i("cur date", date.toString());

            }
        });


        // Find the ListView resource.
        mainListView = (ListView) findViewById( R.id.explistView );



        // Create ArrayAdapter using the planet list.

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.

        foodItems = new ArrayList<FoodItem>();
        foodItems.add(new FoodItem("Milk", "1/13/18", "10", FoodItem.Category.DAIRY));
        foodItems.add(new FoodItem("Peach", "4/13/18", "10", FoodItem.Category.FRUITS));
        foodItems.add(new FoodItem("Rice", "1/13/18", "10", FoodItem.Category.GRAINS));
        foodItems.add(new FoodItem("Chicken", "1/13/18", "10", FoodItem.Category.MEAT));
        foodItems.add(new FoodItem("Broccoli", "1/13/18", "10", FoodItem.Category.VEGETABLES));

        foodItemAdapter = new FoodItemAdapter(this, foodItems);


        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( foodItemAdapter );
    }

}
