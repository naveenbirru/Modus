package com.example.thor.modus;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FoodItemDetailActivity extends AppCompatActivity {
    
    public final static String NEW_NOTE_EXTRA = "New Note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_detail);
        createAndFragment();
    }

    private void createAndFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        Intent intent = getIntent();
        MainActivity.FragementToLaunch f = (MainActivity.FragementToLaunch)intent.getSerializableExtra(MainActivity.FOOD_ITEM_FRAGMENT_TO_LOAD_EXTRA);

        switch(f) {
            case VIEW:
                FoodItemViewFragment foodItemViewFragment = new FoodItemViewFragment();
                setTitle(R.string.view_fragment_title);
                fragmentTransaction.add(R.id.food_item_container, foodItemViewFragment, "FOOD_ITEM_VIEW_FRAGMENT" );
                break;
            case EDIT:
                FoodItemEditFragment foodItemEditFragment = new FoodItemEditFragment();
                setTitle(R.string.edit_fragment_title);
                fragmentTransaction.add(R.id.food_item_container, foodItemEditFragment, "FOOD_ITEM_EDIT_FRAGMENT" );
                break;
           /* case CREATE:
                NoteEditFragment noteCreateFragment = new NoteEditFragment();
                setTitle(R.string.create_fragment_title);
                Bundle bundle = new Bundle();
                bundle.putBoolean(NEW_NOTE_EXTRA, true);

                noteCreateFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.note_container, noteCreateFragment, "NOTE_CREATE_FRAGMENT" );
                break;
                */
        }

        fragmentTransaction.commit();
    }
}
