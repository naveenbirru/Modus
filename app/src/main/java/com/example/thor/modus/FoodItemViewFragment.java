package com.example.thor.modus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class FoodItemViewFragment extends Fragment {

    public FoodItemViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentLayout = inflater.inflate(R.layout.fragment_food_item_view, container, false);

        TextView title = (TextView)fragmentLayout.findViewById(R.id.foodItemDetailTitle);
        TextView weight = (TextView) fragmentLayout.findViewById(R.id.foodItemDetailWeight);
        TextView date = (TextView) fragmentLayout.findViewById(R.id.foodItemDetailDate);
        ImageView icon = (ImageView)fragmentLayout.findViewById(R.id.foodItemDetailImg);
        TextView categoryTextView = (TextView)fragmentLayout.findViewById(R.id.foodItemDetailCategory);
        Button editButton = (Button)fragmentLayout.findViewById(R.id.editFoodItem);

        Intent intent = getActivity().getIntent();

        title.setText(intent.getExtras().getString(MainActivity.FOOD_ITEM_TITLE_EXTRA));
        weight.setText(intent.getExtras().getString(MainActivity.FOOD_ITEM_WEIGHT_EXTRA));
        date.setText(intent.getExtras().getString(MainActivity.FOOD_ITEM_DATE_EXTRA));
        long foodItemID = intent.getExtras().getLong(MainActivity.FOOD_ITEM_ID_EXTRA);


        FoodItem.Category cat = (FoodItem.Category) intent.getSerializableExtra(MainActivity.FOOD_ITEM_CATEGORY_EXTRA);
        icon.setImageResource(FoodItem.categoryToDrawable(cat));

        String categoryString = FoodItem.getCategoryString(cat);
        categoryTextView.setText(categoryString);

        final FoodItem foodItem = new FoodItem(title.getText().toString(), weight.getText().toString(), cat, foodItemID, date.getText().toString());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchFoodItemDetialActivity(MainActivity.FragementToLaunch.EDIT, foodItem);
            }
        });

        // Inflate the layout for this fragment
        return fragmentLayout;
    }

    private void launchFoodItemDetialActivity(MainActivity.FragementToLaunch ftl, FoodItem foodItem ) {

        Intent intent = new Intent(getActivity(), FoodItemDetailActivity.class);

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
