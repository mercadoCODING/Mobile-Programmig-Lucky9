package com.example.lucky9app.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lucky9app.R;
import com.example.lucky9app.dbase.ImageDAO;
import com.example.lucky9app.dbase.UserDAO;
import com.example.lucky9app.utility.BundleUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SetupProfilePicActivity extends AppCompatActivity {

    private int userId;
    private String username;
    private byte[] imageBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile_pic);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            username = bundle.getString("username");
            UserDAO userDAO = new UserDAO(this);
            userId = userDAO.getUserIdByUsername(username);
        }

        ImageView profilePicture = findViewById(R.id.profile_picture);
        Button selectPictureButton = findViewById(R.id.btn_select_picture);
        Button confirmButton = findViewById(R.id.btn_confirm);

        ActivityResultLauncher<Intent> selectImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri imageUri = data.getData();
                            profilePicture.setImageURI(imageUri);
                            imageBytes = resizeImage(imageUri);
                            if (imageBytes == null) {
                                Toast.makeText(this, "Error converting image to bytes", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });


        selectPictureButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            selectImageLauncher.launch(intent);
        });


        confirmButton.setOnClickListener(v -> {
            if (imageBytes != null && userId != -1 && bundle != null) {
                Intent intent = new Intent(this,MenuActivity.class);
                bundle.putString("username",username);
                bundle.putByteArray("profilePictureBytes",imageBytes);
                intent.putExtras(bundle);
                ImageDAO imageDao = new ImageDAO(this);
                imageDao.addProfilePicture(userId, imageBytes);
                this.startActivity(intent);
            } else {
                Toast.makeText(this, "No image selected or user not found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private byte[] resizeImage(Uri imageUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream);
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, 200, 200, true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}



