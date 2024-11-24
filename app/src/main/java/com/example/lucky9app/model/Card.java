package com.example.lucky9app.model;

import android.widget.ImageView;

public class Card {
    private final String imageName;
    private boolean isFlipped;
    private boolean isMatched;
    public Card(String imageName) {
        this.imageName = imageName;
        this.isFlipped = false;
        this.isMatched = false;
    }

    public String getImageName() {
        return imageName;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }
}