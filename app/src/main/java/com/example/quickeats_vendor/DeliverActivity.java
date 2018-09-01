package com.example.quickeats_vendor;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class DeliverActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    byte[] byteArray = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_drawer_layout_deliver);

        //FloatingActionButton userIcon;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_current_orders);
        navigationView.setNavigationItemSelectedListener(this);
       // userIcon = findViewById(R.id.profile_button);
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


        //update status: ask for more time
        //send notifcation so the user gets updates on the order
        RelativeLayout mRel;
        TextView orderOwner = findViewById(R.id.order_owner);
        TextView orderDetails = findViewById(R.id.order_details);
        Button sendNotification = findViewById(R.id.send);

        Bundle b = getIntent().getExtras();
        String username = "";
        String details = "";
        if (b != null) {
            username = b.getString("userName");
            details = b.getString("orderDetails");
        }
        orderOwner.setText(username);
        orderDetails.setText(details);

        final String usernamePickup = username;

        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliverActivity.this, SendNotification.class);
                Bundle b = new Bundle();
                b.putString("userNamePickup", usernamePickup);
                intent.putExtras(b);
                if(byteArray!=null){intent.putExtra("image",byteArray);}
                startActivity(intent);
//
//                CharSequence options[] = new CharSequence[] {"Yes", "No"};
//
//                final AlertDialog.Builder builder = new AlertDialog.Builder(DeliverActivity.this);
//                builder.setTitle("Alert");
//                builder.setMessage("Are you sure you want to send pickup notification?");
//                builder.setItems(options, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (which == 0) {
//                            //TODO: jump to SendNotification Activity
//
//
//
//                        } else {
//                            dialog.dismiss();
//                        }
//                    }
//                });
//                builder.show();
            }
        });



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
