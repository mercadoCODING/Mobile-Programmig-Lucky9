package com.example.lucky9app.utility;

import android.app.Activity;

public class ValidateInput {

    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";


    public static boolean validateUserInput(Activity activity, String username, String email, String password, String gender, String age, String contactNumber, String address){
        DataBaseValidation dbValidation = new DataBaseValidation(activity);

        if(username.isEmpty() || email.isEmpty() || password.isEmpty() || gender.isEmpty() || age.isEmpty() || contactNumber.isEmpty() || address.isEmpty()){
            AlertDialogUtil.showSimpleDialog(activity,"Invalid Input","Fill out all the Fields");
            return false;
        }

        if(dbValidation.usernameExists(username)){
            AlertDialogUtil.showSimpleDialog(activity,"Username Exists","Username already Exists");
            return false;
        }

        if(!password.matches(PASSWORD_PATTERN)){
            AlertDialogUtil.showSimpleDialog(activity,"Invalid Input","Password must contain 1 Special Character,1 Uppercase,1 Lowercase, and a Length of 8");
            return false;
        }

        if(!email.matches(EMAIL_PATTERN)){
            AlertDialogUtil.showSimpleDialog(activity,"Invalid Input","Invalid Email");
            return false;
        }

        if (!age.matches("\\d+")){
            AlertDialogUtil.showSimpleDialog(activity,"Invalid Input","Must be Digits");
            return false;
        }

        if(!contactNumber.matches("\\d+")){
            AlertDialogUtil.showSimpleDialog(activity,"Invalid Input","Must be Digits");
            return false;
        }
        return true;
    }


        public static  boolean validateLoginInput(Activity activity, String username, String password){
            DataBaseValidation dbValidation = new DataBaseValidation(activity);

            if(username.isEmpty() || password.isEmpty()){
                AlertDialogUtil.showSimpleDialog(activity,"Invalid Input","Fill out the Fields");
                return false;
            }

            if(!dbValidation.accountExists(username)){
                AlertDialogUtil.showSimpleDialog(activity,"No Account","Account doesn't Exist");
                return false;
            }

            return true;
        }
}
