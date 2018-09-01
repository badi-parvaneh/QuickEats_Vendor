package com.example.quickeats_vendor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderHistoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private FirebaseDatabase mDatabase;
    private ArrayList<Order> mOrderHistory = new ArrayList<>();
    byte[] byteArray = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_drawer_layout_order_history);

        //FloatingActionButton userIcon;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_current_orders);
        navigationView.setNavigationItemSelectedListener(this);
        //userIcon = findViewById(R.id.profile_button);
        //userIcon.setImageResource(R.mipmap.ic_launcher_round);
        ImageView userImageHomeScreen =navigationView.getHeaderView(0).findViewById(R.id.imageViewUser);
        byteArray=getIntent().getByteArrayExtra("image");
        if (byteArray!=null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            userImageHomeScreen.setImageBitmap(bitmap);
            //userIcon.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
        }

//        userIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//                drawer.openDrawer(GravityCompat.START);
//            }
//        });

//        mRecyclerView = (RecyclerView) findViewById(R.id.order_hist_rec);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        //TODO: get the order history from Firebase;
//
//        mAdapter = new OrderHistoryAdapter(this, mOrderHistory);
//        mRecyclerView.setAdapter(mAdapter);

        String username = getIntent().getStringExtra("username");
        mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mDatabase.getReference("Vendors").child(username).child("currentOrders");
        myRef.addValueEventListener(new ValueEventListener() {
            public static final String TAG = "";

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                HashMap value;
                value = (HashMap) dataSnapshot.getValue();
                if (value == null) {
                    Toast.makeText(OrderHistoryActivity.this, "No order history.", Toast.LENGTH_LONG).show();
                } else {
                    String username = (String) value.get("username");



                    ArrayList<Food> currentCart = new ArrayList<>();
                    for (HashMap menuItem : ((ArrayList<HashMap>) value.get("cart"))) {
                        double priceDouble = 0.0;
                        if (((HashMap) menuItem).get("priceDouble").getClass() == Double.class) {
                            priceDouble = (double) ((HashMap) menuItem).get("priceDouble");
                        } else {
                            priceDouble = ((Long) ((HashMap) menuItem).get("priceDouble")).doubleValue();
                        }
                        int imageId = ((Long) ((HashMap) menuItem).get("imageId")).intValue();
                        String price = (String) ((HashMap) menuItem).get("price");
                        String itemName = (String) ((HashMap) menuItem).get("name");
                        String info = (String) ((HashMap) menuItem).get("info");

                        currentCart.add(new Food(itemName, price, priceDouble, info, imageId));

                    }

                    mOrderHistory.add(new Order(username, currentCart, null, null, null));

                    mRecyclerView = (RecyclerView) findViewById(R.id.order_hist_rec);
                    mRecyclerView.setHasFixedSize(true);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(OrderHistoryActivity.this));

                    mAdapter = new OrderHistoryAdapter(OrderHistoryActivity.this, mOrderHistory);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.order_hist_rec);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new OrderHistoryAdapter(this, mOrderHistory);
        mRecyclerView.setAdapter(mAdapter);

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.current_orders) {
            //jump to order now activity
            Intent i = new Intent(this,CurrentOrdersActivity.class);
            i.putExtra("image",byteArray);
            startActivity(i);

        } else if (id == R.id.menu) {
            //jump to payment activity
            Intent i = new Intent(this,MenuActivity.class);
            i.putExtra("image",byteArray);
            startActivity(i);

        } else if (id == R.id.order_history) {
            //jump to order history activity
            Intent i = new Intent(this,OrderHistoryActivity.class);
            i.putExtra("image",byteArray);
            startActivity(i);

        } else if (id == R.id.settings) {
            //jump to settings activity
            Intent i = new Intent(this,settingActivity.class);
            i.putExtra("image",byteArray);
            startActivity(i);
        }
        else if (id == R.id.homeScreen) {
            //jump to settings activity
            Intent i = new Intent(this,HomeScreenActivity.class);
            i.putExtra("image",byteArray);
            startActivity(i);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
