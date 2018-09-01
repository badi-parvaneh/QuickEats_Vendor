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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MoreTimeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    byte[] byteArray = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_dawer_layout_more_time);

        EditText mins_more = findViewById(R.id.mins_more);
        EditText reason = findViewById(R.id.reason_text);

        Button update_status = findViewById(R.id.more_time_update_button);
        Button cancel_update = findViewById(R.id.more_time_cancel_button);

        TextView user = findViewById(R.id.user_more_time);


        Bundle b = getIntent().getExtras();
        String username = "";

        if (b != null) {
            username = b.getString("usernameMoreTime");
        }

        user.setText(username);

        String time = mins_more.getText().toString();
        String reasonFor = reason.getText().toString();

        update_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Access user from firebase using the username above

                //TODO: Push an update to Firebase to update the status

                EditText minutes = findViewById(R.id.mins_more);
                String mins = "";
                if(minutes.getText() != null) {
                    mins = minutes.getText().toString();
                }
                if(minutes.getText() == null){
                    Toast.makeText(MoreTimeActivity.this,"type in minutes", Toast.LENGTH_LONG).show();
                }
                final String newMin= mins;

                SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS");
                Date date = null;
                try {
                    date = sdf.parse("2006-01-01 07:14:38.000");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long timeInMillisSinceEpoch = date.getTime();
                long timeInMinutesSinceEpoch = timeInMillisSinceEpoch / TimeUnit.MILLISECONDS.toMinutes(timeInMillisSinceEpoch);
                long firebaseMin = timeInMinutesSinceEpoch+Long.parseLong(newMin);
                String customerName=getIntent().getStringExtra("userNamePickup");
                customerName="imran";

                FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = mDatabase.getReference("Users").child(customerName);
                myRef.child("ETA").setValue(firebaseMin);


            

            }
        });

        cancel_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(), CurrentOrdersActivity.class);
                if(byteArray!=null){i.putExtra("image",byteArray);}
                startActivity(i);
            }
        });
        FloatingActionButton userIcon;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_current_orders);
        navigationView.setNavigationItemSelectedListener(this);
        userIcon = findViewById(R.id.profile_button);
        userIcon.setImageResource(R.mipmap.ic_launcher_round);
        ImageView userImageHomeScreen =navigationView.getHeaderView(0).findViewById(R.id.imageViewUser);
        byteArray=getIntent().getByteArrayExtra("image");
        if (byteArray!=null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            userImageHomeScreen.setImageBitmap(bitmap);
            userIcon.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
        }

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
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
