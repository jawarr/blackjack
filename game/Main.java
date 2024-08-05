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

class Main {
    public static void main(String []args)
    { 
        Player player = new Player();
        Player dealer = new Player();
        Deck deck = new Deck();

        player.chips = 1000;
        player.bet = 50;
        deck.shuffle();

        while (player.chips > 0) {
            player.hand.add(deck.drawCard());
            player.hand.add(deck.drawCard());
            dealer.hand.add(deck.drawCard());
            dealer.hand.add(deck.drawCard());

            player.getScore();
            dealer.getScore();
            
            boolean stillDrawing = true;

            while (stillDrawing) {
                Screen.display(player, dealer);

                char input = Controller.input();
                switch (input) {
                    case 'H':
                        player.hand.add(deck.drawCard());
                        break;
                    case 'S':
                        stillDrawing = false;
                        break;
                    case 'D':
                        player.hand.add(deck.drawCard());
                        player.bet = 100;
                        stillDrawing = false;
                        break;
                    default:
                        break;
                }
                player.getScore();
            }
        }
    }
}