package com.example.script;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private Button btn_mainact_signup;
    private Button btn_mainact_signin;
    private EditText et_signin_email;
    private EditText et_signin_password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
mAuth.signOut();

btn_mainact_signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent= new Intent(MainActivity.this,Signup.class);
    }
});

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
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!(task.isSuccessful())){
                        Toast.makeText(MainActivity.this, "Sign In Failed.", Toast.LENGTH_SHORT).show();
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
