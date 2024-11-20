package com.example.lucky9app.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lucky9app.R;
import com.example.lucky9app.dbase.ImageDAO;
import com.example.lucky9app.dbase.UserDAO;
import com.example.lucky9app.utility.BundleUtil;

public class MenuActivity extends AppCompatActivity {

    private int userId;
    private ImageButton profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        profileButton = findViewById(R.id.profileButton);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String usernameForId = BundleUtil.getUsername(bundle);

            UserDAO userDAO = new UserDAO(this);
            userId = userDAO.getUserIdByUsername(usernameForId);
        }


        setProfilePicture();


    }

    private void setProfilePicture() {
        if (userId != -1) {
            ImageDAO imageDAO = new ImageDAO(this);
            byte[] profilePicture = imageDAO.getProfilePicture(userId);
            if (profilePicture != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(profilePicture, 0, profilePicture.length);
                profileButton.setImageBitmap(bitmap);
            } else {
                profileButton.setImageResource(R.drawable.dfaultpic);

            }
        }
    }
}
