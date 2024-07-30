package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    public static ArrayList<String> cards = new ArrayList<String>();

    public static void addCards () {
        for (int i = 2; i < 14; i++) {
            cards.add(i + "♤"); 
            cards.add(i + "♡"); 
            cards.add(i + "♧");
            cards.add(i + "♢");  
        }
    }

    public static String drawCard() {
        Random rand = new Random();
        return cards.get(rand.nextInt(cards.size()));
    }

    public static void shuffle (){
        Collections.shuffle(cards);
    }
}
