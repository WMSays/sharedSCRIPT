package com.example.script;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class S_Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s__notification);
        //S_Notification_BottomNavBar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.S_dash_BottomNavBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                {
                    case R.id.ic_item_home:
//                        TODO: logic to choose intent based on userType.
                        Intent intent1 = new Intent(S_Notification.this, s_dash.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_item_notifications:
                        Intent intent2 = new Intent(S_Notification.this, S_Notification.class);
                        startActivity(intent2);


                        break;
                    case R.id.ic_item_settings:
                        Intent intent3 = new Intent(S_Notification.this, Settings.class);
                        startActivity(intent3);

                        break;

                }

                return false;
            }
        });
    }
    }

