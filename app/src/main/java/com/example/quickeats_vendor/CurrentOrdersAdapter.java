package com.example.quickeats_vendor;

/**
 * Created by badiparvaneh on 4/27/18.
 */

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
 * Created by badiparvaneh on 4/17/18.
 */

public class CurrentOrdersAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Order> mOrders;

    public CurrentOrdersAdapter(Context mContext, ArrayList<Order> mOrders) {
        this.mContext = mContext;
        this.mOrders = mOrders;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_orders_cell, parent, false);
        return new CurrentOrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Order order = mOrders.get(position);
        ((CurrentOrdersViewHolder) holder).bind(order);
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }
}

class CurrentOrdersViewHolder extends RecyclerView.ViewHolder {
    //add fields and UI elements
    private ImageView categoryImage;
    private TextView orderItems;
    private TextView orderUser;
    private CardView orderCard;
    private String userName;
    private String orderDetails = "";


    public CurrentOrdersViewHolder(View itemView) {
        super(itemView);

        //hook up UI elements
        orderItems = itemView.findViewById(R.id.order_item);
        orderUser = itemView.findViewById(R.id.order_user);
        orderCard = itemView.findViewById(R.id.curr_order_card);

        //click listener on the card takes us to the restaurant
        orderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), DeliverActivity.class);
                Bundle b = new Bundle();
                b.putString("userName", userName);
                b.putString("orderDetails", orderDetails);
                intent.putExtras(b);
                view.getContext().startActivity(intent);

            }
        });

    }

    void bind(Order order) {
        //bind the stuff we need
        userName = order.getCustomerName();
        for (Food f : order.getCart()) {
            orderDetails += " " + f.getName();
        }

        orderUser.setText(userName);
        orderItems.setText(orderDetails);
    }
}