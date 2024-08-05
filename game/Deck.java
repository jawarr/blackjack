package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    
    ArrayList<String> cards = new ArrayList<String>();

    Deck () {
        for (int i = 2; i < 15; i++) {
            cards.add(i + "♤"); 
            cards.add(i + "♡"); 
            cards.add(i + "♧"); 
            cards.add(i + "♢");
        }
    }

    String drawCard() {
        Random rand = new Random();
        String card = cards.get(rand.nextInt(cards.size()));
        cards.remove(card);
        return card;
    }

    void shuffle (){
        Collections.shuffle(cards);
    }
}
