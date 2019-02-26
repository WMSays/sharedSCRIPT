package com.example.script;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class NavBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.BottomNavBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                {
                    case R.id.ic_item_home:
//                        TODO: logic to choose intent based on userType.
                        Intent intent1 = new Intent(NavBar.this, s_dash.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_item_notifications:
                        Intent intent2 = new Intent(NavBar.this, S_Notification.class);
                        startActivity(intent2);


                        break;
                    case R.id.ic_item_settings:
                        Intent intent3 = new Intent(NavBar.this, Settings.class);
                        startActivity(intent3);

                        break;

                }

                return false;
            }
        });
    }
}
