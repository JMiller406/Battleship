import java.util.Scanner;

public class Tutorial {
    // Explains the way Battleship works with rules

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🛳️ Welcome to Battleship Tutorial!");
        System.out.println("In this tutorial, you'll learn the basic rules and gameplay mechanics of Battleship.");
        System.out.println("Press Enter to begin...");
        scanner.nextLine();

        explainSetup(scanner);
        explainGameplay(scanner);
        explainWinning(scanner);

        System.out.println("\n🎉 Tutorial complete! You're ready to play Battleship.");
        scanner.close();
    }

    // Step 1: Explain the board setup
    private static void explainSetup(Scanner scanner) {
        System.out.println("\n📦 Setup Phase:");
        System.out.println("Each player has a grid (in this case 10x10) where they place their ships.");
        System.out.println("Ships vary in length: Carrier (5), Battleship (4), Cruiser (3), Submarine (3), Destroyer (2).");
        System.out.println("Ships can be placed horizontally or vertically, but not diagonally.");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Step 2: Explain how turns work
    private static void explainGameplay(Scanner scanner) {
        System.out.println("\n🎯 Gameplay Phase:");
        System.out.println("Players take turns calling out grid coordinates (e.g., B5) to attack.");
        System.out.println("If a ship occupies that coordinate, it's a hit. Otherwise, it's a miss.");
        System.out.println("Hits are marked on the opponent's grid, and players track their own guesses.");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    // Step 3: Explain how to win
    private static void explainWinning(Scanner scanner) {
        System.out.println("\n🏁 Winning the Game:");
        System.out.println("The goal is to sink all of your opponent's ships before they sink yours.");
        System.out.println("A ship is sunk when all its coordinates have been hit.");
        System.out.println("Strategic guessing and memory are key to victory!");
        System.out.println("Press Enter to finish...");
        scanner.nextLine();
    }
}
