package game;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame extends Game {

    private static final int MIN = 1;
    private static final int MAX = 100;

    private int secretNumber;
    private int numberOfPlayers;
    private int winner;
    private int[] guessCounts;
    private Scanner scanner;

    @Override
    public void initializeGame(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.winner = -1;
        this.guessCounts = new int[numberOfPlayers];
        this.scanner = new Scanner(System.in);

        secretNumber = new Random().nextInt(MAX - MIN + 1) + MIN;

        System.out.println("=== Number Guessing Game ===");
        System.out.println("I have chosen a number between " + MIN + " and " + MAX + ".");
        System.out.println(numberOfPlayers + " player(s) will take turns guessing.");
        System.out.println("The first player to guess correctly wins!\n");
    }

    @Override
    public boolean endOfGame() {
        return winner != -1;
    }

    @Override
    public void playSingleTurn(int player) {
        System.out.print("Player " + (player + 1) + ", enter your guess: ");

        int guess = readGuess();
        guessCounts[player]++;

        if (guess == secretNumber) {
            winner = player;
        } else if (guess < secretNumber) {
            System.out.println("  Too low! Try higher.\n");
        } else {
            System.out.println("  Too high! Try lower.\n");
        }
    }

    @Override
    public void displayWinner() {
        System.out.println("\n*** Player " + (winner + 1) + " guessed the number " + secretNumber + "! ***");
        System.out.println("Guess counts:");
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("  Player " + (i + 1) + ": " + guessCounts[i] + " guess(es)");
        }
    }

    // Reads and validates the player's guess
    private int readGuess() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= MIN && value <= MAX) {
                    return value;
                }
                System.out.print("  Please enter a number between " + MIN + " and " + MAX + ": ");
            } catch (NumberFormatException e) {
                System.out.print("  Invalid input. Enter a whole number: ");
            }
        }
    }
}