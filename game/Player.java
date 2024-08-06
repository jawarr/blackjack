package game;

import java.util.ArrayList;

public class Player {
    int chips, score, bet;
    boolean bust, win, push, stillDrawing;
    ArrayList<String> hand = new ArrayList<String>();

    //this is a method instead of a Constructor because it's called multiple times on the same object
    void initalize() {
        hand.clear();
        score = 0;
        bet = 50;
        bust = false;
        win = false;
        push = false;
        stillDrawing = true;
    }

    void getScore() {
        score = 0;
        //for each card in the hand, add the correct score
        hand.forEach((card) -> {
            Integer num = Integer.parseInt(card.replaceAll("[\\D]", ""));
            if (num < 11) {
                score += num;
            }
            else if (num < 14) {
                score += 10;
            }
            else {
                score += 11;
            }
        });
        
        //checking for any aces, and if the ace causes a bust you can use the ace as a 1 instead
        hand.forEach((card) -> {
            if ((card.contains("14")) && score > 21) {
                score -= 10;
            }
        });
    }
}
