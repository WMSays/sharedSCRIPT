package com.example.script;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class NavBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.BottomNavBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }
}
