package com.example.lucky9app.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lucky9app.R;
import com.example.lucky9app.utility.ButtonClickUtility;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        Button buttonLogin = findViewById(R.id.login_button);
        Button buttonSignUp = findViewById(R.id.sign_up_button);


        ButtonClickUtility.setOnClickListenerLogin(buttonLogin,this);
        ButtonClickUtility.setOnClickListener(buttonSignUp,this, SignUpActivity.class);

    }
}