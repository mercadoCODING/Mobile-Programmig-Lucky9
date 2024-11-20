package com.example.lucky9app.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogUtil {
    public static void showSimpleDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .setCancelable(false);
        builder.create().show();
    }

    // Method to show a dialog with a custom callback for buttons
    public static void showDialogWithCallback(Context context, String title, String message,
                                              String positiveButtonText, DialogInterface.OnClickListener positiveClickListener,
                                              String negativeButtonText, DialogInterface.OnClickListener negativeClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, positiveClickListener)
                .setNegativeButton(negativeButtonText, negativeClickListener)
                .setCancelable(true);
        builder.create().show();
    }
    public static void showDialogWithAction(Context context, String title, String message,
                                            DialogInterface.OnClickListener positiveClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", positiveClickListener)
                .setCancelable(false);
        builder.create().show();
    }
}
