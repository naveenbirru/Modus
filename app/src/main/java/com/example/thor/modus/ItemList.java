package com.example.thor.modus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ItemList extends AppCompatActivity {

    private ArrayList<FoodItem> foodItems;
    private FoodItemAdapter foodItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        foodItems = new ArrayList<FoodItem>();
        foodItems.add(new FoodItem("Milk", "1/13/18", "10", FoodItem.Category.DAIRY));
        foodItems.add(new FoodItem("Peach", "4/13/18", "10", FoodItem.Category.FRUITS));
        foodItems.add(new FoodItem("Rice", "1/13/18", "10", FoodItem.Category.GRAINS));
        foodItems.add(new FoodItem("Chicken", "1/13/18", "10", FoodItem.Category.MEAT));
        foodItems.add(new FoodItem("Broccoli", "1/13/18", "10", FoodItem.Category.VEGETABLES));

        foodItemAdapter = new FoodItemAdapter(this, foodItems);


        ListView listView = (ListView) findViewById(R.id.foodItemList);
        listView.setAdapter(foodItemAdapter);
    }
}
