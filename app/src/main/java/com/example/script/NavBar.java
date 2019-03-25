package com.example.script;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NavBar extends AppCompatActivity {
private DatabaseReference myRef;
private FirebaseAuth mAuth2;
private FirebaseDatabase myFirebaseDatabase;
    public User user= new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
        //firebase initializations

mAuth2= FirebaseAuth.getInstance();
myFirebaseDatabase= FirebaseDatabase.getInstance();
myRef= myFirebaseDatabase.getReference();
FirebaseUser firebase_user= mAuth2.getCurrentUser();

String UserID= firebase_user.getUid();

//getting values from firebase

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.BottomNavBar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                {
                    case R.id.ic_item_home:
//                        TODO: logic to choose intent based on userType.

                        String type= user.getUserType();
                        if(type=="Supervisor")
                        {
                            Intent intent1 = new Intent(NavBar.this,s_dash.class );
                            startActivity(intent1);

                        }
                        else
                        {
                            Intent intent1 = new Intent(NavBar.this,r_dash.class );
                            startActivity(intent1);


                        }
                       // Intent intent1 = new Intent(NavBar.this, s_dash.class);
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

    private void showData(DataSnapshot dataSnapshot) {
        //for loop to iterate through the snapshot
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            //use User class to read data
            String userID= FirebaseAuth.getInstance().getUid();
            user.setFirstName(ds.child(userID).getValue(User.class).getFirstName());
            user.setLastName(ds.child(userID).getValue(User.class).getLastName());
            user.setEmail(ds.child(userID).getValue(User.class).getEmail());
            user.setUserType(ds.child(userID).getValue(User.class).getUserType());
            Log.d("DATA", user.getUserType());



        }
    }
}
