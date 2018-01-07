package com.example.thor.modus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getActionBar().show();

        final ImageButton calender = (ImageButton)findViewById(R.id.calender);
        final ImageButton itemList = (ImageButton)findViewById(R.id.itemList);
        final ImageButton recipes = (ImageButton)findViewById(R.id.recipes);
        final ImageButton settings = (ImageButton)findViewById(R.id.settings);

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Hello", "calender button selected");
                launchCalendarActivity();

            }
        });
        itemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Hello", "itemlist button selected");

                launchItemListActivity();

            }
        });

        recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Hello", "recipes button selected");
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Hello", "settings button selected");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void launchItemListActivity() {

        Intent intent = new Intent(this, ItemList.class);
        startActivity(intent);

    }

    private void launchCalendarActivity() {

        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);

    }
}
