package com.example.lucky9app.utility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BundleUtil {

    // Get Bundle from the current activity's Intent
    public static Bundle getExtrasFromIntent(Activity activity) {
        Intent intent = activity.getIntent();
        if (intent != null) {
            return intent.getExtras();  // Return the extras from the intent
        }
        return null;  // Return null if there are no extras in the intent
    }

    // Utility methods to retrieve data from the Bundle
    public static String getUsername(Bundle bundle) {
        return bundle != null ? bundle.getString("username", "DefaultUsername") : "DefaultUsername";
    }

    public static String getEmail(Bundle bundle) {
        return bundle != null ? bundle.getString("email", "DefaultEmail") : "DefaultEmail";
    }

    public static String getPassword(Bundle bundle) {
        return bundle != null ? bundle.getString("password", "DefaultPassword") : "DefaultPassword";
    }

    public static String getGender(Bundle bundle) {
        return bundle != null ? bundle.getString("gender", "DefaultGender") : "DefaultGender";
    }

    public static String getAge(Bundle bundle) {
        return bundle != null ? bundle.getString("age", "DefaultAge") : "DefaultAge";
    }

    public static String getContactNumber(Bundle bundle) {
        return bundle != null ? bundle.getString("contact_number", "DefaultContactNumber") : "DefaultContactNumber";
    }

    public static String getAddress(Bundle bundle) {
        return bundle != null ? bundle.getString("address", "DefaultAddress") : "DefaultAddress";
    }
}
