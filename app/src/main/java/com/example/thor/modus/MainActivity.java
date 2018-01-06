package com.example.thor.modus;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton calender = (ImageButton)findViewById(R.id.calender);
        final ImageButton itemList = (ImageButton)findViewById(R.id.itemList);
        final ImageButton recipes = (ImageButton)findViewById(R.id.recipes);
        final ImageButton settings = (ImageButton)findViewById(R.id.settings);

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Hello", "calender button selected");
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

}
