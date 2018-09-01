package com.example.quickeats_vendor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Spinner spinner = findViewById(R.id.spinner);
        Button button = findViewById(R.id.button);

        //final String name;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final String s;
                switch (i) {
                    case 0:
                        name = "Al Attles' California Cheesesteak";
                        break;
                    case 1:
                        name = "Banh Mi Sandwiches";
                        break;
                    case 2:
                        name = "BurgerQueen";
                        break;
                    case 3:
                        name = "Cocktail Bar";
                        break;
                    case 4:
                        name = "Coors Light Club";
                        break;
                    case 5:
                        name = "Dippin Dots";
                        break;
                    case 6:
                        name = "Dub Greens";
                        break;
                    case 7:
                        name = "DubNation Sandwiches";
                        break;
                    case 8:
                        name = "Farina Pizza";
                        break;
                    case 9:
                        name = "Fuelburger";
                        break;
                    case 10:
                        name = "Golden State Greats";
                        break;
                    case 11:
                        name = "Loaded Nachos, Dogs";
                        break;
                    case 12:
                        name = "Peet's Coffee";
                        break;
                    case 13:
                        name = "Popcorn Cart";
                        break;
                    case 14:
                        name = "Salad Champion";
                        break;
                    case 15:
                        name = "Shock Top Beer";
                        break;
                    case 16:
                        name = "Slices and Suds";
                        break;
                    case 17:
                        name = "Smokin Oakland Grill";
                        break;

                    default:
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                name = "";
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.equals("")) {
                    Toast.makeText(LogIn.this, "Please select a restaurant to proceed", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(LogIn.this, HomeScreenActivity.class);
                    intent.putExtra("username", name);
                    startActivity(intent);
                }
            }
        });

    }
}
