package com.example.lucky9app.model;

import android.widget.ImageView;

public class Card {
    private final String imageName;
    private final int value;
    private final ImageView imageView;
    private boolean isFlipped = false;

    public Card(String imageName, int value, ImageView imageView) {
        this.imageName = imageName;
        this.value = value;
        this.imageView = imageView;
    }

    public String getImageName() {
        return imageName;
    }

    public int getValue() {
        return value;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }
}