package com.example.thor.modus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private ListView mainListView ;
    private ArrayList<FoodItem> foodItems;
    private ArrayAdapter<FoodItemAdapter> foodItemAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        final CalendarView cv = findViewById(R.id.calendarView);
        cv.setDate(System.currentTimeMillis(),false,true);


        // Find the ListView resource.
        mainListView = (ListView) findViewById( R.id.explistView );



        // Create ArrayAdapter using the planet list.

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        FoodDbAdapter dbAdapter = new FoodDbAdapter(this.getBaseContext());
        dbAdapter.open();
        foodItems = dbAdapter.getAllFoodItems();
        dbAdapter.close();

        foodItemAdapter = new FoodItemAdapter(this, foodItems);


        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( foodItemAdapter );
    }

}
