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

*/ 

class blackjack
{
    public static void main(String []args)
    {
        System.out.println("\033[0;37m" + " ___\n|K  |\n| " + "\033[0;91m" + "♡" + "\033[0;37m" + " |\n|  K|\n ̅ ̅ ̅ ");
    }
}