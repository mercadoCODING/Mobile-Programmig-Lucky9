package com.example.lucky9app.model;

public class CardMatch {
    private int id;
    private boolean matched;

    public CardMatch(int id) {
        this.id = id;
        this.matched = false;
    }

    public int getId() {
        return id;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}
