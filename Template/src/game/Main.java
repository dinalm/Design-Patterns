package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many players? ");
        int players = Integer.parseInt(scanner.nextLine().trim());

        Game game = new NumberGuessingGame();
        game.play(players);
    }
}