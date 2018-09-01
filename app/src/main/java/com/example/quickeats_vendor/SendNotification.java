package com.example.quickeats_vendor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class SendNotification extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    byte[] byteArray = null;
    String pickup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_drawer_layout_send_notification);

        Bundle b = getIntent().getExtras();
        String username = "";
        if (b != null) {
            username = b.getString("usernamePickup");
        }


        Button sendButton = findViewById(R.id.send_final);
        Button cancelButton = findViewById(R.id.cancel);


        final String usernameUpdate = username;

        //final String pickup="";


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText minutes = findViewById(R.id.minutes_needed);
                String mins = "";
                if(minutes.getText() != null) {
                    mins = minutes.getText().toString();
                }
                if(minutes.getText() == null){
                    Toast.makeText(SendNotification.this,"type in minutes", Toast.LENGTH_LONG).show();
                }
                final String newMin= mins;


                //TODO: Get the closest pickup to user using Firebase: access his/her location via username above.
                //randomly generate user's current location generation

                Location currentLocation = new Location("userLocation");
                double minLatitu = 37.7498;
                double maxLatitu = 37.7508;
                double minLongtitu = -122.2035;
                double maxLongtiti = -122.2025;
                double randomLati = minLatitu + Math.random() * (maxLatitu - minLatitu);
                double randomLonti = minLongtitu + Math.random() * (maxLongtiti - minLongtitu);
                currentLocation.setLatitude(randomLati);
                currentLocation.setLongitude(randomLonti);
                //Six pick up locations and distance to it
                Location SouthwestPickup = new Location("1");
                SouthwestPickup.setLatitude(37.7498);
                SouthwestPickup.setLongitude(-122.2035);
                float distance1 = Math.round(currentLocation.distanceTo(SouthwestPickup));

                Location SoutheastPickup = new Location("2");
                SoutheastPickup.setLatitude(37.7498);
                SoutheastPickup.setLongitude(-122.2025);
                float distance2 = Math.round(currentLocation.distanceTo(SoutheastPickup));

                Location NorthwestPickup = new Location("3");
                NorthwestPickup.setLatitude(37.7508);
                NorthwestPickup.setLongitude(-122.2035);
                float distance3 = Math.round(currentLocation.distanceTo(NorthwestPickup));

                Location NortheastPickup = new Location("4");
                NortheastPickup.setLatitude(37.7508);
                NortheastPickup.setLongitude(-122.2025);
                float distance4 = Math.round(currentLocation.distanceTo(NortheastPickup));

                Location midTopPickup = new Location("5");
                midTopPickup.setLatitude(37.7508);
                midTopPickup.setLongitude(-122.2030);
                float distance5 = Math.round(currentLocation.distanceTo(midTopPickup));

                Location midBotPickup = new Location("6");
                midBotPickup.setLatitude(37.7498);
                midBotPickup.setLongitude(-122.2030);
                float distance6 = Math.round(currentLocation.distanceTo(midBotPickup));
                float minDistance = distance1;
                final float[] numbers = {distance1, distance2, distance3, distance4, distance5, distance6};
                int x=0;
                for (int i=0;i<6;i++) {
                    if (numbers[i] < minDistance)
                    {
                        minDistance = numbers[i];
                        x=i;
                    }
                }
                Location[] pickupLocations = {SouthwestPickup,SoutheastPickup,NorthwestPickup,NortheastPickup,midTopPickup,midBotPickup};


                //The nealist pick up points to be returned
                final PickupPoint pickupPoint = new PickupPoint(pickupLocations[x].getProvider(),pickupLocations[x]);
                //final long ONE_MINUTE_IN_MILLIS=60000;//millisecs



                //final Date afterAddingMins=new Date(t + (Integer.parseInt(newMin) * ONE_MINUTE_IN_MILLIS));

//                SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS");
//                Date date = null;
//                try {
//                    date = sdf.parse("2006-01-01 07:14:38.000");
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                Long dateMil = Calendar.getInstance().getTimeInMillis();
                //long timeInMillisSinceEpoch = date.getTime();
               // long timeInMinutesSinceEpoch = timeInMillisSinceEpoch / TimeUnit.MILLISECONDS.toMinutes(timeInMillisSinceEpoch);
                //long firebaseMin = timeInMinutesSinceEpoch+Long.parseLong(newMin);
                long firebaseMin = (dateMil / 1000) / 60;
                firebaseMin += Integer.parseInt(newMin);
                String customerName=getIntent().getStringExtra("userNamePickup");
                //customerName="imran";
                //String customerName= customerName="imran";
                if(getIntent().getStringExtra("userNamePickup")!=null)
                {getIntent().getStringExtra("userNamePickup");}

                FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = mDatabase.getReference("Users").child(customerName);
                myRef.child("ETA").setValue(firebaseMin);
                myRef.child("pickuplocation").setValue(pickupPoint.getName()+","+pickupPoint.getLocation().getLatitude()+", "+pickupPoint.getLocation().getLongitude());

               // pickup = pick
                //TODO: update order status and put it on Firebase.

                //TODO: Give the option to ask for more time: this will be an activity

                  Intent intent = new Intent(v.getContext(), UpdateStatusActivity.class);
                Bundle b = new Bundle();
                //b.putString("usernameUpdateStatus", usernameUpdate);
                b.putString("mins", newMin);
                b.putString("pickup", pickupPoint.getName());
                intent.putExtras(b);
//                if(byteArray!=null){intent.putExtra("image",byteArray);}
                  startActivity(intent);

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(), CurrentOrdersActivity.class);
                if(byteArray!=null){i.putExtra("image",byteArray);}
                startActivity(i);
            }
        });

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
