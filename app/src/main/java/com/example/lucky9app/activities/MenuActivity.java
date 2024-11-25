package com.example.lucky9app.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lucky9app.R;
import com.example.lucky9app.dbase.UserDAO;
import com.example.lucky9app.utility.ButtonClickUtility;

public class MenuActivity extends AppCompatActivity {

    private int userId;
    private ImageButton profileButton;

    private Bundle bundle;
    private byte[] profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        profileButton = findViewById(R.id.profileButton);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            String usernameForId = bundle.getString("username");
            UserDAO userDAO = new UserDAO(this);
            userId = userDAO.getUserIdByUsername(usernameForId);
            profilePicture = bundle.getByteArray("profilePictureBytes");
        }
        setProfilePicture();

        ImageButton matchGameButton = findViewById(R.id.matchGameButton);
        ImageButton lucky9Button = findViewById(R.id.lucky9Button);
        ImageButton leaderboardButton = findViewById(R.id.leaderboardButton);

        ButtonClickUtility.setOnClickListenerImage(matchGameButton,this,MatchGameActivity.class);
        ButtonClickUtility.setOnClickListenerImage(lucky9Button,this,Lucky9GameActivity.class);
        ButtonClickUtility.setOnClickListenerImage(leaderboardButton,this,LeaderboardsActivity.class);



        /*profileButton.setOnClickListener(v->{

        });*/
    }

    private void setProfilePicture() {
        if (userId != -1) {
                if (profilePicture != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(profilePicture, 0, profilePicture.length);
                    profileButton.setImageBitmap(bitmap);
                } else {
                    profileButton.setImageResource(R.drawable.dfaultpic);

                }

        }
    }

}
