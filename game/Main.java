/*
    Blackjack rules: https://bicyclecards.com/how-to-play/blackjack

    What we need to do:
    - Give player a certain amount of chips (1000?)
    - Ask player what they want to bet
    - Draw two cards for player and dealer (only show one for dealer)
    - Prompt player to (H)it, (S)tand, or (D)ouble Down until they stand or bust (go over 21)
        - (we can use whatever keys for this, I just thought the first letter would be easy)
        - If player bust, bet chips are gone and redraw cards
        - I think we can skip Split, its not super necessary to play the game

    - If player does not bust, dealer hits until its hand is 17 or greater (maybe add a small delay between each card being shown)
    - Compare dealer and players hand
        - If player has higher hand or if dealer bust, double the player's chips they bet and redraw cards.
        - If dealer has higher hand, bet chips are gone and redraw cards
    
    Classes we might need:
    - Player: has fields for the player's hand and if they've busted
    - Dealer: has fields for the dealer's hand and if they've busted, and a method for them to play
    - Controller: methods to take and interpret input from user
    - Deck object: I think an Arraylist is probably the easiest
    - 
    -
    -

    If we have time:
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
        Player player = new Player();
        Player dealer = new Player();
        Deck deck = new Deck();
        
        player.initalize();
        player.chips = 1000;
        dealer.initalize();
        deck.shuffle();

        while (player.chips > 0) {
            
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
                        player.bet = 100;
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

            if (player.win) {
                player.chips += (player.bet / 3) * 2;
            }
            else if(player.push) {

            } else {
                player.chips -= player.bet;
            }
            

            Screen.display(player, dealer);
            
            switch (Controller.getInput()) {
                case 'R':
                case 'r':
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
    }
}