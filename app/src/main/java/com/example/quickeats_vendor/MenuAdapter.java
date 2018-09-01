package com.example.quickeats_vendor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by badiparvaneh on 4/29/18.
 */

public class MenuAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<Food> mMenu;

    public MenuAdapter(Context mContext, ArrayList<Food> mMenu) {
        this.mContext = mContext;
        this.mMenu = mMenu;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_cell, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Food food = mMenu.get(position);
        ((MenuViewHolder) holder).bind(food);
    }

    @Override
    public int getItemCount() {
        return mMenu.size();
    }
}

class MenuViewHolder extends RecyclerView.ViewHolder {
    //add fields and UI elements
    private ImageView foodImage;
    private CardView menuCard;
    private TextView foodName;
    private TextView foodPrice;
    private TextView foodIng;
    private String foodNameString;
    private String foodPriceString;
    private String foodIngString;
    private int imageId;


    public MenuViewHolder(View itemView) {
        super(itemView);

        //hook up UI elements
        menuCard = itemView.findViewById(R.id.menu_cell_card);
        //foodImage = itemView.findViewById(R.id.food_image_menu);
        foodName = itemView.findViewById(R.id.food_name_menu);
        foodPrice = itemView.findViewById(R.id.food_price_menu);
        foodIng = itemView.findViewById(R.id.food_ing_menu);



        //click listener on the card takes us to the restaurant
//        menuCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), UpdateMenuFoodActivity.class);
//                Bundle b = new Bundle();
//                b.putString("foodName", foodIngString);
//                b.putString("foodPrice", foodPriceString);
//                b.putString("foodIng", foodIngString);
//                view.getContext().startActivity(intent);
//
//            }
//        });

    }

    void bind(Food food) {
        //bind the stuff we need
        foodNameString = food.getName();
        foodIngString = food.getInfo();
        foodPriceString = food.getPrice();
        //imageId = food.getImageId();

        //foodImage.setImageResource(imageId);
        foodName.setText(foodNameString);
        foodPrice.setText(foodPriceString);
        foodIng.setText(foodIngString);
    }
}
