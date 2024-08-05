package game;

import java.util.ArrayList;

public class Screen {
    static void display (Player player, Player dealer) {
        System.out.print("\033[H\033[J");
        System.out.println(" ____  _            _     _            _    ");
        System.out.println("| __ )| | __ _  ___| | __(_) __ _  ___| | __");
        System.out.println("|  _ \\| |/ _` |/ __| |/ /| |/ _` |/ __| |/ /");
        System.out.println("| |_) | | (_| | (__|   < | | (_| | (__|   < ");
        System.out.println("|____/|_|\\__,_|\\___|_|\\_\\/ |\\__,_|\\___|_|\\_\\");
        System.out.println("                       |__/                 ");

        System.out.println("Dealer's Hand: " + dealer.score);
        printHand(dealer.hand);

        System.out.println("\nYour Hand: " + player.score);
        printHand(player.hand);


        System.out.println(player.chips + "⛃");
        System.out.println("\n(H)it, (S)tand, or (D)ouble Down?");
    }

    static void printHand(ArrayList<String> hand) {
        hand.forEach(card -> System.out.print("\033[0;37m" + " ___ "));
        System.out.print("\n");
        hand.forEach((card) -> {
            Integer num = Integer.parseInt(card.replaceAll("[\\D]", ""));
            if (num < 10) {
                System.out.print("|" + num + "  |");
            }
            else {
                switch (num) {
                    case 10:
                        System.out.print("|10 |");
                        break;
                    case 11:
                        System.out.print("|J  |");
                        break;
                    case 12:
                        System.out.print("|Q  |");
                        break;
                    case 13:
                        System.out.print("|K  |");
                        break;
                    case 14:
                        System.out.print("|A  |");
                        break;
                
                    default:
                        break;
                }
            }
        });
        System.out.print("\n");
        hand.forEach((card) -> {
            if (card.contains("♡")) {
                System.out.print("| " + "\033[0;31m" + "♡" + "\033[0;37m" + " |");
            }
            else if (card.contains("♤")) {
                System.out.print("| " + "\033[0;34m" + "♤" + "\033[0;37m" + " |");
            }
            else if (card.contains("♢")) {
                System.out.print("| " + "\033[0;31m" + "♢" + "\033[0;37m" + " |");
            }
            else if (card.contains("♧")) {
                System.out.print("| " + "\033[0;34m" + "♧" + "\033[0;37m" + " |");
            }
        });
        System.out.print("\n");
        hand.forEach((card) -> {
            Integer num = Integer.parseInt(card.replaceAll("[\\D]", ""));
            if (num < 10) {
                System.out.print("|  " + num + "|");
            }
            else {
                switch (num) {
                    case 10:
                        System.out.print("| 10|");
                        break;
                    case 11:
                        System.out.print("|  J|");
                        break;
                    case 12:
                        System.out.print("|  Q|");
                        break;
                    case 13:
                        System.out.print("|  K|");
                        break;
                    case 14:
                        System.out.print("|  A|");
                        break;
                
                    default:
                        break;
                }
            }
        });
        System.out.print("\n");
        hand.forEach(card -> System.out.print("\033[0;37m" + " ̅ ̅ ̅  "));
        System.out.print("\n");
    }
}