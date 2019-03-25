package com.example.script;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    private Button btn_mainact_signup;
    private Button btn_mainact_signin;
    private EditText et_signin_email;
    private EditText et_signin_password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth2;
    private FirebaseDatabase myFirebaseDatabase;
    public User user= new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        btn_mainact_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,Signup.class);
                startActivity(intent);
            }
        });
//mAuth.signOut();



btn_mainact_signin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        SignIn();
    }
});
    }

    private void SignIn() {
        String SignIn_email= et_signin_email.getText().toString().trim();
        String SignIn_password= et_signin_password.getText().toString().trim();

        //validation
        if(TextUtils.isEmpty(SignIn_email)|| TextUtils.isEmpty(SignIn_password))
        {
            Toast.makeText(MainActivity.this, "Username/Password not entered", Toast.LENGTH_SHORT).show();

        }
        else
        { //if both enteries are filled, sign in
            mAuth.signInWithEmailAndPassword(SignIn_email,SignIn_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull final Task<AuthResult> task) {
                    if(!(task.isSuccessful())){
                        Toast.makeText(MainActivity.this, "Sign In Failed.", Toast.LENGTH_SHORT).show();
                    }
                    //if sign in successful. check user type and intent to dashboard of that usertype
                    else
                    {
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
                                btn_mainact_signin.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String type= user.getUserType();
                                        if(type=="Supervisor")
                                        {
                                            Intent intent1 = new Intent(MainActivity.this,s_dash.class );
                                            startActivity(intent1);

                                        }
                                        else
                                        {
                                            Intent intent1 = new Intent(MainActivity.this,r_dash.class );
                                            startActivity(intent1);
                                        }
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

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
Log.d("signin",task.getException().getMessage() );
                            }
                        });

                        //show Data

                    }



                }
            });}


    }

    private void Init() {
        et_signin_email= (EditText)findViewById(R.id.et_signin_email);
        et_signin_password= (EditText)findViewById(R.id.et_signin_password);
        mAuth= FirebaseAuth.getInstance();
        btn_mainact_signin = (Button)findViewById(R.id.btn_mainact_signin);
        btn_mainact_signup =(Button)findViewById(R.id.btn_mainact_signup);

        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                { //TODO: logic to choose activity based on userType.
                    Toast.makeText(MainActivity.this, "Already signed in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,s_dash.class ));
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}

