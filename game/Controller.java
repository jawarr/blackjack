package game;

import java.util.Scanner;

public class Controller {
    static Scanner sc = new Scanner(System.in);
    
    static char getInput () {
        return sc.next().charAt(0);
    }

    static int getBet() {
        return sc.nextInt();
    }
}
