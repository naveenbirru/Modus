package com.example.thor.modus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class ItemList extends BaseActivity {

    private ArrayList<FoodItem> foodItems;
    private FoodItemAdapter foodItemAdapter;
    private FoodItem.Category savedButtonCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        deleteAllFoodItems(this);

        createItemInDB(this, "Milk", "10", FoodItem.Category.DAIRY, "1-6-2018",1, true);
        createItemInDB(this, "Banana", "10", FoodItem.Category.FRUITS, "1-6-2018",2, true);
        createItemInDB(this, "Tomatoes", "10", FoodItem.Category.GRAINS, "1-6-2018",3, true);
        createItemInDB(this, "Tomatoes", "10", FoodItem.Category.VEGETABLES, "1-7-2018",4, true);
        createItemInDB(this, "Avocado", "10", FoodItem.Category.VEGETABLES, "1-7-2018",5, true);
        createItemInDB(this, "Fish", "10", FoodItem.Category.MEAT, "1-8-2018",6, true);

        FoodDbAdapter dbAdapter = new FoodDbAdapter(this.getBaseContext());
        dbAdapter.open();
        foodItems = dbAdapter.getAllFoodItems();
        dbAdapter.close();
        foodItemAdapter = new FoodItemAdapter(this, foodItems);

        ListView listView = (ListView) findViewById(R.id.foodItemList);
        listView.setAdapter(foodItemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                launchFoodItemDetialActivity(adapterView, MainActivity.FragementToLaunch.VIEW, position);
            }
        });
    }



    public void createItemInDB(Context ctxt, String title, String weight, FoodItem.Category category, String expiryDate, long ID, Boolean newNote){
        FoodDbAdapter foodDbAdapter = new FoodDbAdapter(ctxt);
        foodDbAdapter.open();

        if(newNote) {
           // foodDbAdapter.createFoodItem(title, weight, (savedButtonCategory == null)? FoodItem.Category.VEGETABLES: savedButtonCategory, expiryDateInMilli);
            foodDbAdapter.createFoodItem(title, weight, category , expiryDate);
        } else {
            foodDbAdapter.updateFoodItem( ID, title, weight, (savedButtonCategory == null)? FoodItem.Category.VEGETABLES: savedButtonCategory , expiryDate);
        }
        foodDbAdapter.close();
    }

    public void deleteAllFoodItems(Context ctxt){
        FoodDbAdapter foodDbAdapter = new FoodDbAdapter(ctxt);
        foodDbAdapter.open();
        foodDbAdapter.deleteAllFoodItem();
        foodDbAdapter.close();
    }

    private void launchFoodItemDetialActivity(AdapterView<?> adapterView, MainActivity.FragementToLaunch ftl, int position) {

        FoodItem foodItem = (FoodItem)adapterView.getItemAtPosition(position) ;
        Intent intent = new Intent(this, FoodItemDetailActivity.class);

        intent.putExtra(MainActivity.FOOD_ITEM_TITLE_EXTRA, foodItem.getTitle());
        intent.putExtra(MainActivity.FOOD_ITEM_WEIGHT_EXTRA, foodItem.getWeight());
        intent.putExtra(MainActivity.FOOD_ITEM_ID_EXTRA, foodItem.getID());
        intent.putExtra(MainActivity.FOOD_ITEM_DATE_EXTRA, foodItem.getExpiryDate());
        intent.putExtra(MainActivity.FOOD_ITEM_CATEGORY_EXTRA, foodItem.getCategory());

        switch(ftl) {
            case EDIT:
                intent.putExtra(MainActivity.FOOD_ITEM_FRAGMENT_TO_LOAD_EXTRA, MainActivity.FragementToLaunch.EDIT);
                break;
            case VIEW:
                intent.putExtra(MainActivity.FOOD_ITEM_FRAGMENT_TO_LOAD_EXTRA, MainActivity.FragementToLaunch.VIEW);
                break;
        }

        startActivity(intent);

    }
}
