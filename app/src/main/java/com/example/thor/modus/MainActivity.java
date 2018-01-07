package com.example.thor.modus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends BaseActivity{

    public static final String FOOD_ITEM_ID_EXTRA = "com.example.thor.modus.Identifier";
    public static final String FOOD_ITEM_TITLE_EXTRA = "com.example.thor.modus.Title";
    public static final String FOOD_ITEM_DATE_EXTRA = "com.example.thor.modus.Date";
    public static final String FOOD_ITEM_WEIGHT_EXTRA = "com.example.thor.modus.Weight";
    public static final String FOOD_ITEM_CATEGORY_EXTRA = "com.example.thor.modus.Category";

    public enum FragementToLaunch {VIEW, EDIT, CREATE}
    public static final String FOOD_ITEM_FRAGMENT_TO_LOAD_EXTRA = "com.example.thor.modus.Fragment_To_Load ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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



    private void launchItemListActivity() {

        Intent intent = new Intent(this, ItemList.class);
        startActivity(intent);

    }

    private void launchCalendarActivity() {

        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);

    }
}
