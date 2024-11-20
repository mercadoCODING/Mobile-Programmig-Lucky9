package com.example.lucky9app.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lucky9app.utility.ButtonClickUtility;
import com.example.lucky9app.R;


public class FrontActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.account_creation_activity);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        ButtonClickUtility.setOnClickListener(buttonLogin,this,LoginActivity.class);
        ButtonClickUtility.setOnClickListener(buttonSignUp,this,SignUpActivity.class);

    }
}