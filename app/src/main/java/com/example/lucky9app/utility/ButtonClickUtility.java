package com.example.lucky9app.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lucky9app.R;
import com.example.lucky9app.activities.MenuActivity;
import com.example.lucky9app.activities.SetupProfilePicActivity;
import com.example.lucky9app.dbase.ImageDAO;
import com.example.lucky9app.dbase.UserDAO;

public class ButtonClickUtility {

    public static void setOnClickListener(final Button button, final Context context, final Class<?> targetActivity){
        button.setOnClickListener(v -> {
            Intent intent = new Intent(context,targetActivity);
            context.startActivity(intent);
        });
    }

    public static void setOnClickListenerData(final Button button, final Activity activity, final Class<?> targetActivity){
        button.setOnClickListener(v -> {

            String username = ((EditText) activity.findViewById(R.id.username)).getText().toString().trim();
            String email = ((EditText) activity.findViewById(R.id.email)).getText().toString().trim();
            String password = ((EditText) activity.findViewById(R.id.password)).getText().toString().trim();
            String gender = ((Spinner) activity.findViewById(R.id.gender_spinner)).getSelectedItem().toString();
            String age = ((EditText) activity.findViewById(R.id.age)).getText().toString().trim();
            String contactNumber = ((EditText) activity.findViewById(R.id.contact_number)).getText().toString().trim();
            String address = ((EditText) activity.findViewById(R.id.address)).getText().toString().trim();

            // Validate user input
            if (ValidateInput.validateUserInput(activity, username, email, password, gender, age, contactNumber, address)) {

                UserDAO userDAO = new UserDAO(activity);


                boolean userAdded = userDAO.addUser(username, email, password, gender, Integer.parseInt(age), contactNumber, address);

                if (userAdded) {
                    Intent intent = new Intent(activity, targetActivity);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    bundle.putString("email", email);
                    bundle.putString("password", password);
                    bundle.putString("gender", gender);
                    bundle.putString("age", age);
                    bundle.putString("contact_number", contactNumber);
                    bundle.putString("address", address);
                    intent.putExtras(bundle);


                    AlertDialogUtil.showDialogWithAction(activity,"Success","Sign Up Success",(dialog, which) -> {


                        activity.startActivity(intent);
                    });
                } else {
                    AlertDialogUtil.showSimpleDialog(activity, "Error", "Sign up failed. Please try again.");
                }
            }
        });
    }

    public static void setOnClickListenerLogin(final Button button, final Activity activity) {
        button.setOnClickListener(v -> {
            String username = ((EditText) activity.findViewById(R.id.username)).getText().toString().trim();
            String password = ((EditText) activity.findViewById(R.id.password)).getText().toString().trim();

            if (ValidateInput.validateLoginInput(activity, username, password)) {
                UserDAO userDAO = new UserDAO(activity);

                boolean confirmUser = userDAO.loginUser(username, password);
                int userId = userDAO.getUserIdByUsername(username);
                if (confirmUser) {

                    ImageDAO imageDAO = new ImageDAO(activity);
                    byte[] profilePicture = imageDAO.getProfilePicture(userId);

                    if (profilePicture != null) {

                        AlertDialogUtil.showDialogWithAction(activity, "Success", "Login Success", (dialog, which) -> {
                            Intent intent = new Intent(activity, MenuActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("username", username);
                            bundle.putByteArray("profilePictureBytes",profilePicture);
                            intent.putExtras(bundle);
                            activity.startActivity(intent);
                        });
                    } else {
                        AlertDialogUtil.showDialogWithAction(activity, "Success", "Login Success", (dialog, which) -> {
                            Intent intent = new Intent(activity, SetupProfilePicActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("username",username);
                            intent.putExtras(bundle);
                            activity.startActivity(intent);
                        });
                    }
                } else {
                    AlertDialogUtil.showSimpleDialog(activity, "Error", "Invalid username or password. Please try again.");
                }
            }
        });
    }
}
