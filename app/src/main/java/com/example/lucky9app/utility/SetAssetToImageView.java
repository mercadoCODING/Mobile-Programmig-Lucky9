package com.example.lucky9app.utility;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class SetAssetToImageView {

    // Load image from assets and set it to the ImageView
    public static void loadImageFromAssets(Context context, String imageName, ImageView imageView) {
        try {
            AssetManager assetManager = context.getAssets(); // Access assets folder
            InputStream inputStream = assetManager.open("cards/" + imageName); // Open specific image

            // Decode and set the Bitmap to the ImageView
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(bitmap);

            // Close the stream
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
