package com.example.thor.modus;

import java.util.Date;

/**
 * Created by thor on 1/6/2018.
 */

public class FoodItem {

    private String title;
    private String expiryDate;
    private String weight;
    private Category category;
    public enum Category {DAIRY, FRUITS, GRAINS, MEAT, VEGETABLES};

    public FoodItem(String title, String weight, Category category, long foodItemID, long expiryDateinMilli) {
        this.title = title;
        this.expiryDate = expiryDate;
        this.weight = weight;
        this.category = category;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "title='" + title + '\'' +
                ", expiryDate=" + expiryDate +
                ", weight=" + weight +
                ", category=" + category +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Category getCategory() {
        return category;
    }

    public int getAssociatedDrawble(){
        return categoryToDrawable(category);
    }

    public static int categoryToDrawable(Category noteCategory) {
        Integer cat = R.mipmap.dairy;
        switch (noteCategory){
            case DAIRY:
                cat = R.mipmap.dairy;
                break;
            case FRUITS:
                cat = R.mipmap.fruits;
                break;
            case GRAINS:
                cat = R.mipmap.grains;
                break;
            case MEAT:
                cat = R.mipmap.meat;
                break;
            case VEGETABLES:
                cat = R.mipmap.vegatables;
                break;
        }
        return cat;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}