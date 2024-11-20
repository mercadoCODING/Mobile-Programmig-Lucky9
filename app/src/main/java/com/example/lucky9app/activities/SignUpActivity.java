package com.example.lucky9app.activities;

import android.os.Bundle;

import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucky9app.R;
import com.example.lucky9app.utility.ButtonClickUtility;
import com.example.lucky9app.utility.SpinnerUtility;


public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Spinner genderSpinner = findViewById(R.id.gender_spinner);
        SpinnerUtility.setupSpinner(this, genderSpinner);


        Button signUpButton = findViewById(R.id.sign_up_button);
        Button loginRedirectButton = findViewById(R.id.login_redirect_button);

        ButtonClickUtility.setOnClickListenerData(signUpButton, this, LoginActivity.class);
        ButtonClickUtility.setOnClickListener(loginRedirectButton,this, LoginActivity.class);

    }
}