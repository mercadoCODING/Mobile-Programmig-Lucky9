package com.example.lucky9app.utility;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lucky9app.R;

import java.util.Arrays;
import java.util.List;

public class SpinnerUtility {

    public static void setupSpinner(Context context, Spinner spinner){
        List<String> genderOptions = Arrays.asList("Male","Female","Other");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,genderOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}
