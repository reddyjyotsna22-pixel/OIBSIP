

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        String playAgain = "yes";
        int round = 1;

        while (playAgain.equalsIgnoreCase("yes")) {

            int[] difficulty = selectDifficulty(sc);

            int maxNumber = difficulty[0];
            int maxAttempts = difficulty[1];

            System.out.println("\n===== ROUND " + round + " =====");
            System.out.println("NUMBER GUESSING GAME");

            int attempts = playRound(
                sc,
                random,
                maxNumber,
                maxAttempts
            );

            System.out.println(
                "Round " + round +
                " - completed in " + attempts + " attempts."
            );

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = sc.next();

            round++;
        }

        System.out.println("Thanks for playing!");

        sc.close();
    }


    public static int[] selectDifficulty(Scanner sc) {

        System.out.println("\nChoose Difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.print("Enter your choice: ");

        int difficulty = sc.nextInt();

        int maxNumber;
        int maxAttempts;

        switch (difficulty) {

            case 1:
                maxNumber = 50;
                maxAttempts = 10;
                break;

            case 2:
                maxNumber = 100;
                maxAttempts = 7;
                break;

            case 3:
                maxNumber = 200;
                maxAttempts = 5;
                break;

            default:
                System.out.println("Invalid choice. Medium difficulty selected.");
                maxNumber = 100;
                maxAttempts = 7;
        }

        return new int[]{maxNumber, maxAttempts};
    }


    public static int playRound(
            Scanner sc,
            Random random,
            int maxNumber,
            int maxAttempts) {

        int number = random.nextInt(maxNumber) + 1;
        int guess = 0;
        int attempts = 0;

        System.out.println(
            "I have selected a number between 1 and "
            + maxNumber + "."
        );

        while (guess != number && attempts < maxAttempts) {

            System.out.println(
                "Attempt " + (attempts + 1)
                + " of " + maxAttempts
            );

            System.out.print("Enter your guess: ");

            if (!sc.hasNextInt()) {
                System.out.println(
                    "Invalid input! Please enter a number."
                );
                sc.next();
                continue;
            }

            guess = sc.nextInt();

            if (guess < 1 || guess > maxNumber) {
                System.out.println(
                    "Please enter a number between 1 and "
                    + maxNumber + "."
                );
                continue;
            }

            attempts++;

            if (guess > number) {
                System.out.println("Too High!");
            }
            else if (guess < number) {
                System.out.println("Too Low!");
            }
            else {
                System.out.println("Correct!");
            }
        }

        if (guess != number) {
            System.out.println("You Lost!");
            System.out.println("The number was: " + number);
        }
        else {
            System.out.println(
                "You guessed it in "
                + attempts + " attempts."
            );
        }

        return attempts;
    }
}
