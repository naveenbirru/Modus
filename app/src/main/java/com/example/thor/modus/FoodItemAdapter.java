package com.example.thor.modus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by thor on 1/6/2018.
 */

public class FoodItemAdapter extends ArrayAdapter{

    public static class ViewHolder {
        ImageView foodIcon;
        TextView title;
        TextView weight;
        TextView date;
    }

    public FoodItemAdapter(Context context, ArrayList<FoodItem> foodItems) {super(context, 0, foodItems);}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        FoodItem foodItem = (FoodItem)getItem(position);

        ViewHolder viewHolder;

        if(convertView ==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_item_row, parent, false);

            ImageView foodIcon = (ImageView)convertView.findViewById(R.id.listItemImg);
            TextView title = (TextView)convertView.findViewById(R.id.listItemTitle);
            TextView weight = (TextView)convertView.findViewById(R.id.listItemWeight);
            TextView date = (TextView)convertView.findViewById(R.id.listItemDate);

            viewHolder = new ViewHolder();

            viewHolder.title = title;
            viewHolder.weight =weight;
            viewHolder.foodIcon = foodIcon;
            viewHolder.date = date;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.title.setText(foodItem.getTitle());
        viewHolder.weight.setText(foodItem.getWeight());
        viewHolder.date.setText(foodItem.getExpiryDate());
        viewHolder.foodIcon.setImageResource(foodItem.getAssociatedDrawble());

        return convertView;
    }
}
