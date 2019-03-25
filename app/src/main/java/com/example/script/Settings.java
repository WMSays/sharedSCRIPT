package com.example.script;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {
private Button btn_signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.Settings_BottomNavBar);
        btn_signout =(Button)findViewById(R.id.btn_signout);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                {
                    case R.id.ic_item_home:
//                        TODO: logic to choose intent based on userType.
                        Intent intent1 = new Intent(Settings.this, s_dash.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_item_notifications:
                        Intent intent2 = new Intent(Settings.this, S_Notification.class);
                        startActivity(intent2);


                        break;
                    case R.id.ic_item_settings:
                        Intent intent3 = new Intent(Settings.this, Settings.class);
                        startActivity(intent3);

                        break;

                }

                return false;
            }
        });
   btn_signout.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           FirebaseAuth.getInstance().signOut();
           Intent intent = new Intent(Settings.this, MainActivity.class);
           startActivity(intent);
finish();
       }
   });
    }
}
