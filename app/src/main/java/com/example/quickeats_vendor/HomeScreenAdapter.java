package com.example.quickeats_vendor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by badiparvaneh on 4/29/18.
 */

public class HomeScreenAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<VendorOptions> mHome;

    public HomeScreenAdapter(Context mContext, ArrayList<VendorOptions> mHome) {
        this.mContext = mContext;
        this.mHome = mHome;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_screen_cell, parent, false);
        return new HomeScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final VendorOptions option = mHome.get(position);
        ((HomeScreenViewHolder) holder).bind(option);
    }

    @Override
    public int getItemCount() {
        return mHome.size();
    }
}

class HomeScreenViewHolder extends RecyclerView.ViewHolder {
    //add fields and UI elements
    private ImageView homeOption;
    private CardView homeCard;
    private String optionTitle;
    private int imageId;


    public HomeScreenViewHolder(View itemView) {
        super(itemView);

        //hook up UI elements
        homeCard = itemView.findViewById(R.id.option_card);
        homeOption = itemView.findViewById(R.id.option_image);

        homeOption.setImageResource(imageId);

        //click listener on the card takes us to the restaurant
        homeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    void bind(VendorOptions option) {
        //bind the stuff we need
        imageId = option.getImageId();
        optionTitle = option.getName();
    }
}

