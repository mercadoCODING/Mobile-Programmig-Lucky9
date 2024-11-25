package com.example.lucky9app.activities;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucky9app.R;
import com.example.lucky9app.actions.SetCard;
import com.example.lucky9app.utility.SetAssetToImageView;

import java.util.ArrayList;
import java.util.List;

public class Lucky9GameActivity extends AppCompatActivity {

    private LinearLayout playerCardsContainer;
    private LinearLayout dealerCardsContainer;
    private Button restartButton;
    private Button dealButton;
    private TextView playerLabel;
    private TextView dealerLabel;

    private final List<String> playerCards = new ArrayList<>();
    private final List<String> dealerCards = new ArrayList<>();
    private SetCard setCard;
    private boolean cardsDealt = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky9_game);

        setCard = new SetCard(this);
        playerCardsContainer = findViewById(R.id.playerCardsContainer);
        dealerCardsContainer = findViewById(R.id.dealerCardsContainer);
        restartButton = findViewById(R.id.restartButton);
        dealButton = findViewById(R.id.dealButton);
        playerLabel = findViewById(R.id.playerLabel);
        dealerLabel = findViewById(R.id.dealerLabel);

        // Setup the game when the activity starts
        setupGame();

        // Restart button functionality
        restartButton.setOnClickListener(v -> restartGame());

        // Deal button functionality
        dealButton.setOnClickListener(v -> dealCards());
    }

    private void setupGame() {
        playerCardsContainer.removeAllViews();
        dealerCardsContainer.removeAllViews();

        // Add labels for Player and Dealer
        playerLabel.setText("Player");
        dealerLabel.setText("Dealer");

        // Draw two cards for the player and dealer
        String playerCard1 = setCard.drawCard();
        String playerCard2 = setCard.drawCard();
        String dealerCard1 = setCard.drawCard();
        String dealerCard2 = setCard.drawCard();

        // Add cards to respective lists
        playerCards.add(playerCard1);
        playerCards.add(playerCard2);
        dealerCards.add(dealerCard1);
        dealerCards.add(dealerCard2);

        // Show the cards face down initially
        showCardBack(playerCardsContainer);
        showCardBack(playerCardsContainer);
        showCardBack(dealerCardsContainer);
        showCardBack(dealerCardsContainer);

        cardsDealt = false;
    }

    private void showCardBack(LinearLayout container) {
        ImageView cardImageView = new ImageView(this);
        cardImageView.setImageResource(R.drawable.back_card);  // Set card back image

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 300);
        params.setMargins(8, 0, 8, 0);
        cardImageView.setLayoutParams(params);

        container.addView(cardImageView);
    }

    private void dealCards() {
        if (!cardsDealt) {
            // Flip the player cards
            for (int i = 0; i < playerCards.size(); i++) {
                String cardName = playerCards.get(i);
                ImageView cardView = (ImageView) playerCardsContainer.getChildAt(i);
                showCard(cardName, cardView);
                animateCardFlip(cardView);
            }

            // Flip the dealer cards
            for (int i = 0; i < dealerCards.size(); i++) {
                String cardName = dealerCards.get(i);
                ImageView cardView = (ImageView) dealerCardsContainer.getChildAt(i);
                showCard(cardName, cardView);
                animateCardFlip(cardView);
            }

            cardsDealt = true;

            // Determine the winner after dealing the cards
            determineWinner();
        }
    }

    private void showCard(String cardName, ImageView cardImageView) {
        // Load the card image based on the card name
        SetAssetToImageView.loadImageFromAssets(this, cardName, cardImageView);
    }

    private void determineWinner() {
        int playerTotal = calculateHandValue(playerCards);
        int dealerTotal = calculateHandValue(dealerCards);

        String result;
        if (playerTotal > dealerTotal) {
            result = "Player Wins!";
        } else if (playerTotal < dealerTotal) {
            result = "Dealer Wins!";
        } else {
            result = "It's a Tie!";
        }

        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    private int calculateHandValue(List<String> cards) {
        int total = 0;
        for (String card : cards) {
            int cardValue = setCard.getCardValue(card);
            total = (total + cardValue) % 10;  // Baccarat hand value is modulo 10
        }
        return total;
    }

    private void restartGame() {
        playerCards.clear();
        dealerCards.clear();
        setCard.createDeck();
        setupGame();
    }

    private void animateCardFlip(View card) {
        AnimatorSet flipIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_in);
        flipIn.setTarget(card);
        flipIn.start();
    }
}
