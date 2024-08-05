package game;

import java.util.ArrayList;
import java.util.Collections;

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
        String card = cards.get(0);
        cards.remove(card);
        return card;
    }

    void shuffle (){
        Collections.shuffle(cards);
    }
}
