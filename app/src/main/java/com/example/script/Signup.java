package com.example.script;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    private Button btn_signup_submit;
    private EditText et_signup_email;
    private EditText et_signup_password;
    private FirebaseAuth firebaseAuth_signup;
    private EditText et_signup_first_name;
    private EditText  et_signup_last_name;
    private EditText  et_signup_reg_id;
private EditText et_signup_confirm_password;
private Spinner sp_signup_type;
private ProgressBar pb_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Init();
        btn_signup_submit.setOnClickListener(this);

    }

    private void Init() {
        btn_signup_submit = (Button)findViewById(R.id.btn_signup_submit);
        et_signup_email= (EditText)findViewById(R.id.et_signup_email);
        et_signup_password= (EditText)findViewById(R.id.et_signup_password);
        et_signup_first_name = (EditText)findViewById(R.id.et_signup_first_name);
        et_signup_last_name = (EditText)findViewById(R.id.et_signup_last_name);
        et_signup_reg_id= (EditText)findViewById(R.id.et_signup_reg_id);
        firebaseAuth_signup= FirebaseAuth.getInstance();
        et_signup_confirm_password = (EditText)findViewById(R.id.et_signup_confirm_password);
        sp_signup_type= (Spinner)findViewById(R.id.sp_signup_type);
        pb_signup= (ProgressBar)findViewById(R.id.pb_signup);

    }

    @Override
    public void onClick(View view) {
        if(view==btn_signup_submit)
            RegisterUser();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //check if user is already signed in
        if(firebaseAuth_signup.getCurrentUser()!=null)
        {
//TODO: enter logic to deal with already signed in user
        }
    }

    private void RegisterUser() {
        //get strings from edittexts
        String email= et_signup_email.getText().toString().trim();
        String password= et_signup_password.getText().toString().trim();
        String password2= et_signup_confirm_password.getText().toString().trim();
        final String firstName= et_signup_first_name.getText().toString().trim();
        final String lastName= et_signup_last_name.getText().toString().trim();
        final String regID= et_signup_reg_id.getText().toString().trim();
        final String userType= sp_signup_type.getSelectedItem().toString().trim();
        //ensuring valid input
        if(email.isEmpty())
        {
            et_signup_email.setError("No email entered");
            et_signup_email.requestFocus();
            return;
        }
        if(firstName.isEmpty())
        {
            et_signup_first_name.setError("Enter First Name");
            et_signup_first_name.requestFocus();
            return;
        }
        if(lastName.isEmpty())
        {
            et_signup_last_name.setError("Enter Last Name");
            et_signup_last_name.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            et_signup_password.setError("Create a Password");
            et_signup_password.requestFocus();
            return;
        }
        if(password2.isEmpty())
        {
            et_signup_confirm_password.setError("Repeat Password");
            et_signup_confirm_password.requestFocus();
            return;
        }
        // check if both passwords match
        if(!(password.equals(password2)))
        {
            et_signup_confirm_password.setError("Passwords Dont Match");
            et_signup_confirm_password.requestFocus();
            return;
        }
        if(regID.isEmpty())
        {
            et_signup_reg_id.setError("No ID entered");
            et_signup_reg_id.requestFocus();
            return;
        }


//registering user
        pb_signup.setVisibility(View.VISIBLE);

        firebaseAuth_signup.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            pb_signup.setVisibility(View.GONE);
                        //TODO: insert custom progress bar.

                            //user is successfully registered
                            Toast.makeText(Signup.this, "Registeration successful", Toast.LENGTH_SHORT).show();
                            //store additional info into database
                            User user = new User(
                                    firstName,lastName, regID, userType
                            );
//TODO: create firebase database object here
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);

                        }
                        else
                        {
                            Toast.makeText(Signup.this, "Registeration failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
