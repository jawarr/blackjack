package game;

import java.util.ArrayList;

public class Player {
    int chips, score, bet;
    ArrayList<String> hand = new ArrayList<String>();

    void getScore() {
        score = 0;
        hand.forEach((card) -> {
            Integer num = Integer.parseInt(card.replaceAll("[\\D]", ""));
            if (num < 11) {
                score += num;
            }
            else if (num < 14) {
                score += 10;
            }
            else {
                if (score <= 10) {
                    score += 11;
                }
                else {
                    score += 1;
                }
            }
        });
    }
}
