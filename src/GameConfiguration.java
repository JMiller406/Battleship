import java.util.List;

public class GameConfiguration {

    public static Game configure() {
        System.out.println("\n=== Game Configuration ===");

        int players = askOneOrTwoPlayers();
        String p1Name = getNameForPlayerNumber(1);
        String p2Name = (players == 2) ? getNameForPlayerNumber(2) : "Computer";
        int aiDifficulty = (players == 1) ? askAIDifficulty() : -1;
        String aiDifficultyName = (aiDifficulty > 0) ? difficultyName(aiDifficulty) : "N/A";
        if (players == 1 && aiDifficulty > 0) {
            System.out.println("Selected AI difficulty: " + aiDifficultyName);
        }
        int placementPreference = askHowToPlaceShip(); // 1 = auto, 2 = manual

        System.out.println("\nConfiguration Summary:");
        System.out.println("Players: " + players + " (" + p1Name + (players == 2 ? " vs " + p2Name : " vs AI") + ")");
        if (players == 1)
            System.out.println("AI Difficulty: " + aiDifficultyName);
        System.out.println("Ship Placement: " + (placementPreference == 1 ? "Auto" : "Manual"));

        ConsoleHelper.getInput("Press Enter to continue...");

        Player player1 = new Player(p1Name); // new Player(p1Name);
        Player player2 = new Player(p2Name); // new Player(p2Name);

        // If auto-placement requested, generate and assign ships for both players
        if (placementPreference == 1) {
            placeShipAutomatically(player1);
            // placeShipAutomatically(player2);
        } else {
            placeShipManually(player1);
            // placeShipManually(player2);
        }

        return new Game(player1, player2, aiDifficulty, placementPreference);

    }

    private static void placeShipAutomatically(Player player) {
        ShipFactory factory = new AutomaticShipFactory();
        List<Ship> ships = factory.getShips(factory.getShipsSortedByLength(Order.DESC));
        player.getOceanGrid().assignShips(ships);
    }

    public static void placeShipManually(Player player) {
        ShipFactory factory = new ManualShipFactory();
        List<Ship> ships = factory.getShips(factory.getShipsSortedByLength(Order.DESC));
        player.getOceanGrid().assignShips(ships);
    }

    public static int askOneOrTwoPlayers() {
        System.out.println("Players:");
        System.out.println("1. vs AI");
        System.out.println("2. vs Player");
        return ConsoleHelper.getNumberBetween("Enter your choice (1-2): ", 1, 2);
    }

    public static int askHowToPlaceShip() {
        System.out.println("Ship placement:");
        System.out.println("1. Auto");
        System.out.println("2. Manual");
        return ConsoleHelper.getNumberBetween("Enter your choice (1-2): ", 1, 2);
    }

    public static String getNameForPlayerNumber(int number) {
        String prompt = String.format("Enter name for player %d: ", number);
        String name = ConsoleHelper.getInput(prompt).trim();
        return name.isEmpty() ? "Player" + number : name;
    }

    public static int askAIDifficulty() {
        System.out.println("AI difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Normal");
        System.out.println("3. Hard");
        return ConsoleHelper.getNumberBetween("Enter your choice (1-3): ", 1, 3);
    }

    private static String difficultyName(int difficulty) {
        switch (difficulty) {
            case 1:
                return "Easy";
            case 2:
                return "Normal";
            case 3:
                return "Hard";
            default:
                return "Unknown";
        }
    }

}