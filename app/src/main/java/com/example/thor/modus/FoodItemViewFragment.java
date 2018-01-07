package com.example.thor.modus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        Intent intent = getActivity().getIntent();

        title.setText(intent.getExtras().getString(MainActivity.FOOD_ITEM_TITLE_EXTRA));
        weight.setText(intent.getExtras().getString(MainActivity.FOOD_ITEM_WEIGHT_EXTRA));
        date.setText(intent.getExtras().getString(MainActivity.FOOD_ITEM_DATE_EXTRA));


        FoodItem.Category cat = (FoodItem.Category) intent.getSerializableExtra(MainActivity.FOOD_ITEM_CATEGORY_EXTRA);
        icon.setImageResource(FoodItem.categoryToDrawable(cat));

        // Inflate the layout for this fragment
        return fragmentLayout;
    }

}
