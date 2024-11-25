package com.example.lucky9app.activities;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lucky9app.R;
import com.example.lucky9app.actions.SetCard;
import com.example.lucky9app.utility.ButtonClickUtility;
import com.example.lucky9app.utility.SetAssetToImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchGameActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private TextView scoreText;
    private Button restartButton,backButton;
    private SetCard setCard;
    private List<String> shuffledCards;
    private List<ImageView> flippedCards;
    private int matchedPairs;
    private int attempts;
    private boolean isFlippingCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_game);

        gridLayout = findViewById(R.id.gridLayout);
        scoreText = findViewById(R.id.scoreText);
        restartButton = findViewById(R.id.restartButton);
        backButton = findViewById(R.id.backButton);

        setCard = new SetCard(this);
        flippedCards = new ArrayList<>();
        matchedPairs = 0;
        attempts = 0;
        isFlippingCards = false;

        setup();
        restartButton.setOnClickListener(v -> restartGame());
        ButtonClickUtility.setOnClickListener(backButton,this,MenuActivity.class);

    }

    public void setup() {
        // Get shuffled card images
        shuffledCards = new ArrayList<>(setCard.getCardImages());
        Collections.shuffle(shuffledCards);

        // Limit the list to 15 pairs of cards (30 cards in total)
        List<String> selectedCards = new ArrayList<>(shuffledCards.subList(0, 15));


        shuffledCards.clear();
        shuffledCards.addAll(selectedCards);
        shuffledCards.addAll(selectedCards);


        Collections.shuffle(shuffledCards);

        gridLayout.removeAllViews();
        gridLayout.setRowCount(5);
        gridLayout.setColumnCount(6);

        // Set up the card grid
        for (String card : shuffledCards) {
            ImageView cardImageView = new ImageView(this);
            cardImageView.setImageResource(R.drawable.back_card);
            cardImageView.setTag(card);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 200;
            params.height = 200;
            cardImageView.setLayoutParams(params);

            cardImageView.setOnClickListener(cardClickListener);
            gridLayout.addView(cardImageView);
        }
        updateScore();
    }

    private final View.OnClickListener cardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isFlippingCards || flippedCards.size() == 2) {
                return;
            }

            ImageView clickedCard = (ImageView) v;


            if (flippedCards.contains(clickedCard)) {
                return;
            }

            String cardImageName = (String) clickedCard.getTag();


            SetAssetToImageView.loadImageFromAssets(MatchGameActivity.this, cardImageName, clickedCard);
            animateCardFlip(clickedCard);
            flippedCards.add(clickedCard);


            clickedCard.setClickable(false);

            if (flippedCards.size() == 2) {
                attempts++;
                isFlippingCards = true;
                checkMatch();
            }
        }
    };

    private void checkMatch() {
        ImageView card1 = flippedCards.get(0);
        ImageView card2 = flippedCards.get(1);

        String card1Name = (String) card1.getTag();
        String card2Name = (String) card2.getTag();

        if (card1Name.equals(card2Name)) {
            matchedPairs++;
            Toast.makeText(this, "Match!", Toast.LENGTH_SHORT).show();
            removeMatchedCards(card1, card2);
        } else {


            card1.postDelayed(() -> {
                card1.setImageResource(R.drawable.back_card);
                card2.setImageResource(R.drawable.back_card);

                flippedCards.clear();
                card1.setClickable(true);
                card2.setClickable(true);
                isFlippingCards = false;
            }, 1000);
            return;
        }
        updateScore();

        // Check if the game is won
        if (matchedPairs == shuffledCards.size() / 2) {
            Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
        } else {
            isFlippingCards = false;
        }
    }

    // Method to remove matched cards from the grid
    private void removeMatchedCards(ImageView card1, ImageView card2) {
        card1.setVisibility(View.INVISIBLE);
        card2.setVisibility(View.INVISIBLE);
        flippedCards.clear();
    }

    private void updateScore() {
        scoreText.setText("Attempts: " + attempts + " | Matches: " + matchedPairs);
    }

    private void restartGame() {
        matchedPairs = 0;
        attempts = 0;
        flippedCards.clear();
        isFlippingCards = false;
        setup();
    }

    private void animateCardFlip(ImageView card) {
        AnimatorSet flipIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_in);
        flipIn.setTarget(card);
        flipIn.start();
    }

}
