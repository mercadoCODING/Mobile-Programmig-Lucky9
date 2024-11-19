package com.example.lucky9app.utility;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class ButtonClickHandler {

    public static void setOnClickListener(final Button button, final Context context, final Class<?> targetActivity){
        button.setOnClickListener(v -> {
            Intent intent = new Intent(context,targetActivity);
            context.startActivity(intent);
        });
    }
}
