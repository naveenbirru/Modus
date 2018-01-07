package com.example.thor.modus;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodItemEditFragment extends Fragment {

    private ImageButton imageButton;
    private FoodItem.Category savedButtonCategory;
    private long foodItemID;
    private AlertDialog categoryDialogObject, confirmDialogObject;
    private EditText title;
    private EditText weight;
    private EditText date;
    private final String MODIFIED_CATEGORY = "Modified Category";

    private Boolean newFoodItem = false;


    public FoodItemEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     //   return inflater.inflate(R.layout.fragment_food_item_edit, container, false);

        if(savedInstanceState != null) {
            savedButtonCategory = (FoodItem.Category)savedInstanceState.getSerializable(MODIFIED_CATEGORY);
        }

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            newFoodItem = bundle.getBoolean(FoodItemDetailActivity.NEW_NOTE_EXTRA, false);
        }

        View fragmentLayout = inflater.inflate(R.layout.fragment_food_item_edit, container, false);
        title = (EditText)fragmentLayout.findViewById(R.id.foodItemEditTitle);
        weight = (EditText)fragmentLayout.findViewById(R.id.foodItemEditWeight);
        imageButton = (ImageButton)fragmentLayout.findViewById(R.id.foodItemEditImg);
        date = (EditText) fragmentLayout.findViewById(R.id.foodItemEditDate);
        Button savedButton = (Button)fragmentLayout.findViewById(R.id.saveFoodItem);

        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(MainActivity.FOOD_ITEM_TITLE_EXTRA, ""));
        weight.setText(intent.getExtras().getString(MainActivity.FOOD_ITEM_WEIGHT_EXTRA, ""));
        date.setText(intent.getExtras().getString(MainActivity.FOOD_ITEM_DATE_EXTRA, ""));
        foodItemID = intent.getExtras().getLong(MainActivity.FOOD_ITEM_ID_EXTRA);

        if(savedInstanceState != null) {
            imageButton.setImageResource(FoodItem.categoryToDrawable(savedButtonCategory));
        }
        else if(!newFoodItem){
            FoodItem.Category noteCat = (FoodItem.Category) intent.getSerializableExtra(MainActivity.FOOD_ITEM_CATEGORY_EXTRA);
            savedButtonCategory = noteCat;
            imageButton.setImageResource(FoodItem.categoryToDrawable(noteCat));
        }

        // Inflate the layout for this fragment
        buildCategoryDialog();
        buildConfirmDialog();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryDialogObject.show();
            }
        });
        savedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialogObject.show();
            }
        });
        return fragmentLayout;
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putSerializable(MODIFIED_CATEGORY, savedButtonCategory);
    }

    private void buildCategoryDialog() {
        final String[] categories = new String[] {"DAIRY", "FRUITS", "GRAINS", "MEAT", "VEGETABLES"};
        final AlertDialog.Builder categoryBuilder = new AlertDialog.Builder(getActivity());
        categoryBuilder.setTitle("Choose a category");

        categoryBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int item) {
                categoryDialogObject.cancel();
                switch(item){
                    case 0:
                        savedButtonCategory = FoodItem.Category.DAIRY;
                        imageButton.setImageResource(FoodItem.categoryToDrawable(savedButtonCategory));
                        break;
                    case 1:
                        savedButtonCategory = FoodItem.Category.FRUITS;
                        imageButton.setImageResource(FoodItem.categoryToDrawable(savedButtonCategory));
                        break;
                    case 2:
                        savedButtonCategory = FoodItem.Category.GRAINS;
                        imageButton.setImageResource(FoodItem.categoryToDrawable(savedButtonCategory));
                        break;
                    case 3:
                        savedButtonCategory = FoodItem.Category.MEAT;
                        imageButton.setImageResource(FoodItem.categoryToDrawable(savedButtonCategory));
                        break;
                    case 4:
                        savedButtonCategory = FoodItem.Category.VEGETABLES;
                        imageButton.setImageResource(FoodItem.categoryToDrawable(savedButtonCategory));
                        break;
                }
            }
        });

        categoryDialogObject = categoryBuilder.create();
    }

    public void buildConfirmDialog () {
        AlertDialog.Builder confirmBuilder =  new AlertDialog.Builder(getActivity());
        confirmBuilder.setTitle("Are you sure ?");
        confirmBuilder.setMessage("Are you sure to change the note");

        confirmBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("Confirm Builder", "Title = " + title.getText());

                FoodDbAdapter foodDbAdapter = new FoodDbAdapter(getContext());
                foodDbAdapter.open();

                if(newFoodItem) {
                    foodDbAdapter.createFoodItem(title.getText().toString(), weight.getText().toString(),
                            (savedButtonCategory == null)? FoodItem.Category.VEGETABLES: savedButtonCategory, date.getText().toString());
                } else {
                    foodDbAdapter.updateFoodItem( foodItemID,title.getText().toString()+"", weight.getText().toString() + "",
                            (savedButtonCategory == null)? FoodItem.Category.VEGETABLES: savedButtonCategory, date.getText().toString() );
                }
                foodDbAdapter.close();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }

        });
        confirmBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        confirmDialogObject = confirmBuilder.create();
    }



}
