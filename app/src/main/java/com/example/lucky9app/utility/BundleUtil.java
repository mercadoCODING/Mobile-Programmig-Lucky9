package com.example.lucky9app.utility;

import android.os.Bundle;

public class BundleUtil {

    public static String getUsername(Bundle bundle) {
        return bundle.getString("username", "DefaultUsername");
    }

    public static String getEmail(Bundle bundle) {
        return bundle.getString("email", "DefaultEmail");
    }

    public static String getPassword(Bundle bundle) {
        return bundle.getString("password", "DefaultPassword");
    }

    public static String getGender(Bundle bundle) {
        return bundle.getString("gender", "DefaultGender");
    }

    public static String getAge(Bundle bundle) {
        return bundle.getString("age", "DefaultAge");
    }

    public static String getContactNumber(Bundle bundle) {
        return bundle.getString("contact_number", "DefaultContactNumber");
    }

    public static String getAddress(Bundle bundle) {
        return bundle.getString("address", "DefaultAddress");
    }
}
