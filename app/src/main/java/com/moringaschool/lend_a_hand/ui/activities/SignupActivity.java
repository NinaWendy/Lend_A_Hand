package com.moringaschool.lend_a_hand.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.lend_a_hand.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    public static  final  String TAG = SignupActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @BindView(R.id.editTextFistName)
    EditText firstName;
    @BindView(R.id.editTextLastName)
    EditText lastName;
    @BindView(R.id.editTextTextEmailAddress)
    EditText emailAddress;
    @BindView(R.id.editTextTextPassword)
    EditText password;
    @BindView(R.id.button)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                createNewUser();
            }
        });
        //getInstance of firebaseAuth object
        mAuth = FirebaseAuth.getInstance();
        
//        createAuthStateListener();

    }
    private void createNewUser(){
        final String name = firstName.getText().toString().trim();
        final String name2 = lastName.getText().toString().trim();
        String email = emailAddress.getText().toString().trim();
        String passCode = password.getText().toString().trim();

        boolean validName = isValidFirstName(name);
        boolean validName2 = isValidSecondName(name2);
        boolean validEmail = isValidEmail(email);
        boolean validPassword = isValidPassword(passCode);
        if (!validEmail || !validName || !validPassword) return;

        mAuth.createUserWithEmailAndPassword(email, passCode)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()){
                        Log.d(TAG, "Authentication is a success!");
                    }else{
                        Toast.makeText(SignupActivity.this, "ooops! Authentication Failed", Toast.LENGTH_LONG).show();

                    }
                });
    }
    //validate first name is entered
    private boolean isValidFirstName(String name){
        if(name.equals("")){
            firstName.setError("Please enter your name");
            return false;
        }
        return true;
    }
    //validate last name is entered
    private boolean isValidSecondName(String name2){
        if(name2.equals("")){
            lastName.setError("Please enter your name");
            return false;
        }
        return true;
    }

    //check the email address if it is valid
    private boolean isValidEmail(String email) {
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if(!isGoodEmail){
            emailAddress.setError("Please enter a valid email address");
            return false;
        }
        return true;
    }

    //check the password
    private boolean isValidPassword(String passCode){
        if(passCode.equals("") || passCode.length() < 6){
            password.setError("field required, Please create a strong password");
            return false;
        }
        return true;
    }




}