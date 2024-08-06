/*
    Blackjack rules: https://bicyclecards.com/how-to-play/blackjack

    Things to add:
    - Save chips to a file
    - Print rules before starting
    - Add option to Split
    - Take unbuffered input (won't have to press enter every time)
    - 
    - 
*/

package game;

import java.util.concurrent.TimeUnit;

class Main {
    public static void main(String[] args)
    { 
        //initalizing objects
        Player player = new Player();
        Player dealer = new Player();
        Deck deck = new Deck();
        
        player.initalize();
        player.chips = 1000;
        dealer.initalize();
        deck.shuffle();

        while (player.chips > 0) {

            //getting the bet from the player and checking if its more than the chips they own
            Screen.betting = true;
            Screen.display(player, dealer);
            player.bet = Controller.getBet();
            if (player.bet > player.chips) {
                player.bet = player.chips;
            }
            player.chips -= player.bet;
            Screen.betting = false;

            //drawing two cards for the player and one for the dealer, with a short pause between each
            Screen.initializing = true;

            Screen.display(player, dealer);
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {Thread.currentThread().interrupt();}

            player.hand.add(deck.drawCard());
            player.getScore();
            Screen.display(player, dealer);
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {Thread.currentThread().interrupt();}
            

            dealer.hand.add(deck.drawCard());
            dealer.getScore();
            Screen.display(player, dealer);
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {Thread.currentThread().interrupt();}
            

            player.hand.add(deck.drawCard());
            player.getScore();
            Screen.display(player, dealer);
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {Thread.currentThread().interrupt();}
            
            Screen.initializing = false;


            
            while (player.stillDrawing && player.score <= 21) {
                Screen.display(player, dealer);
                char in = Controller.getInput();
                switch (in) {
                    case 'H':
                    case 'h':
                        player.hand.add(deck.drawCard());
                        break;
                    case 'S':
                    case 's':
                        player.stillDrawing = false;
                        break;
                    case 'D':
                    case 'd':
                        player.hand.add(deck.drawCard());
                        if (player.bet * 2 > player.chips ) player.bet = player.chips;
                        else player.bet *= 2;
                        player.stillDrawing = false;
                        break;
                    default:
                        break;
                }
                player.getScore();
            }

            //if player bust, quit before dealer draws
            if (player.score > 21) {
                player.bust = true;
                Screen.display(player, dealer);
            } 
            //dealer begins to draw
            else {
                while (!dealer.bust && dealer.score < 17 && dealer.score < player.score) {
                    try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {Thread.currentThread().interrupt();}
                    dealer.hand.add(deck.drawCard());
                    dealer.getScore();
                    Screen.display(player, dealer);
                    
                    if (dealer.score > 21) {
                        dealer.bust = true;
                    }
                }
                
                //set final game state
                if (player.score == dealer.score) {
                    player.push = true;
                }
                else if ((player.score > dealer.score) || dealer.bust) {
                    player.win = true;
                } 
                else {
                    dealer.win = true;
                }
            }

            //deciding how many chips to award
            if (player.win) 
                player.chips = player.chips + player.bet + (player.bet / 3) * 2;
            
                else if (player.push) 
                player.chips += player.bet;
            
            //prompting for quit or reset
            Screen.display(player, dealer);
            switch (Controller.getInput()) {
                case 'A':
                case 'a':
                    player.initalize();
                    dealer.initalize();
                    deck.shuffle();
                    continue;
                case 'Q':
                case 'q':
                    return;
                default:
                    player.initalize();
                    dealer.initalize();
                    deck.shuffle();
                    continue;
            }
        }
        System.out.println("\nOut of chips! Exiting...");
        return;
    }
}