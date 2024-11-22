package com.example.lucky9app.actions;

import android.content.Context;
import android.content.res.AssetManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class SetCardValues {

    private final List<String> cardImages = new ArrayList<>();
    private final HashMap<String, Integer> cardValues = new HashMap<>();

    public SetCardValues(Context context){
        loadCardImagesFromAssets(context);
        assignCardValues();
    }

    private void loadCardImagesFromAssets(Context context) {
        try {
            AssetManager assetManager = context.getAssets();
            String[] files = assetManager.list("cards");
            if (files != null) {
                cardImages.addAll(Arrays.asList(files));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void assignCardValues() {
        for (String imageName : cardImages) {

            int value = generateCardValue(imageName);
            cardValues.put(imageName, value);
        }
    }

    private int generateCardValue(String imageName) {

        HashMap<String, Integer> valueMapping = new HashMap<>();

        //2 Cards Values
        valueMapping.put("2_of_clubs.png", 2);
        valueMapping.put("2_of_diamonds.png", 2);
        valueMapping.put("2_of_hearts.png", 2);
        valueMapping.put("2_of_spades.png", 2);

        //3 Cards Values
        valueMapping.put("3_of_clubs.png", 2);
        valueMapping.put("3_of_diamonds.png", 2);
        valueMapping.put("3_of_hearts.png", 2);
        valueMapping.put("3_of_spades.png", 2);
        //4 Cards Values
        valueMapping.put("4_of_clubs.png", 2);
        valueMapping.put("4_of_diamonds.png", 2);
        valueMapping.put("4_of_hearts.png", 2);
        valueMapping.put("4_of_spades.png", 2);


        //5 Cards Values
        valueMapping.put("5_of_clubs.png", 5);
        valueMapping.put("5_of_diamonds.png", 5);
        valueMapping.put("5_of_hearts.png", 5);
        valueMapping.put("5_of_spades.png", 5);

        //6 Cards Values
        valueMapping.put("6_of_clubs.png", 6);
        valueMapping.put("6_of_diamonds.png", 6);
        valueMapping.put("6_of_hearts.png", 6);
        valueMapping.put("6_of_spades.png", 6);

        //7 Cards Values
        valueMapping.put("7_of_clubs.png", 7);
        valueMapping.put("7_of_diamonds.png", 7);
        valueMapping.put("7_of_hearts.png", 7);
        valueMapping.put("7_of_spades.png", 7);

        //8 Cards Values
        valueMapping.put("8_of_clubs.png", 8);
        valueMapping.put("8_of_diamonds.png", 8);
        valueMapping.put("8_of_hearts.png", 8);
        valueMapping.put("8_of_spades.png", 8);

        //9 Cards Values
        valueMapping.put("9_of_clubs.png", 9);
        valueMapping.put("9_of_diamonds.png", 9);
        valueMapping.put("9_of_hearts.png", 9);
        valueMapping.put("9_of_spades.png", 9);

        //10 Cards Values
        valueMapping.put("10_of_clubs.png", 10);
        valueMapping.put("10_of_diamonds.png", 10);
        valueMapping.put("10_of_hearts.png", 10);
        valueMapping.put("10_of_spades.png", 10);

        //Jack Cards Values
        valueMapping.put("jack_of_clubs2.png", 11);
        valueMapping.put("jack_of_diamonds2.png", 11);
        valueMapping.put("jack_of_hearts2.png", 11);
        valueMapping.put("jack_of_spades2.png", 11);

        //Queen Cards Values
        valueMapping.put("queen_of_clubs2.png", 12);
        valueMapping.put("queen_of_diamonds2.png", 12);
        valueMapping.put("queen_of_hearts2.png", 12);
        valueMapping.put("queen_of_spades2.png", 12);

        //King Cards Values
        valueMapping.put("king_of_clubs2.png", 13);
        valueMapping.put("king_of_diamonds2.png", 13);
        valueMapping.put("king_of_hearts2.png", 13);
        valueMapping.put("king_of_spades2.png", 13);

        //Ace Cards Values
        valueMapping.put("ace_of_clubs2.png", 1);
        valueMapping.put("ace_of_diamonds2.png", 1);
        valueMapping.put("ace_of_hearts2.png", 1);
        valueMapping.put("ace_of_spades2.png", 1);

        Integer value = valueMapping.get(imageName);
        return (value != null) ? value : -1;
    }

    public int getCardValue(String imageName) {

        Integer value = cardValues.get(imageName);
        return (value != null) ? value : -1;
    }


}
