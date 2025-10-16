public class GameConfiguration {

    public static Game configure() {
        System.out.println("\n=== Game Configuration ===");

        int players = askOneOrTwoPlayers();
        String p1Name = getNameForPlayerNumber(1);
        String p2Name = (players == 2) ? getNameForPlayerNumber(2) : "Computer";
        int aiDifficulty = (players ==1) ? askAIDifficulty() : -1;
        int placementPreference = askHowToPlaceShip(); // 1 = auto, 2 = manual

        System.out.println("\nConfiguration Summary:");
        System.out.println("Players: " + players + " (" + p1Name + (players == 2 ? " vs " + p2Name : " vs AI") + ")");
        if (players == 1) System.out.println("AI Difficulty: " + aiDifficulty);
        System.out.println("Ship Placement: " + (placementPreference == 1 ? "Auto" : "Manual"));

        ConsoleHelper.getInput("Press Enter to continue...");

        Player player1 = new Player(p1Name); // new Player(p1Name);
        Player player2 = new Player(p2Name); // new Player(p2Name);
        
        return new Game(player1, player2, aiDifficulty, placementPreference);

        

    }

    public static int askOneOrTwoPlayers() {
        return ConsoleHelper.getNumberBetween("Players (1 = vs AI, 2 = vs Player): ", 1, 2);
    }

    public static int askHowToPlaceShip() {
        return ConsoleHelper.getNumberBetween("Placement (1 = Auto, 2 = Manual): ", 1, 2);
    }

    public static String getNameForPlayerNumber(int number) {
        String prompt = String.format("Enter name for player %d: ", number);
        String name = ConsoleHelper.getInput(prompt).trim();
        return name.isEmpty() ? "Player" + number : name;
    }

    public static int askAIDifficulty() {
        return ConsoleHelper.getNumberBetween("AI difficulty (1 = Easy, 2 = Normal, 3 = Hard): ", 1, 3);
    }






}