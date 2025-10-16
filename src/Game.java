public class Game {
    private final Player player1;
    private final Player player2;
    private final int aiDifficulty; // -1 if no AI
    private final int placementPreference; // 1 = auto, 2 = manual

    public Game(Player player1, Player player2, int aiDifficulty, int placementPreference) {
        this.player1 = player1;
        this.player2 = player2;
        this.aiDifficulty = aiDifficulty;
        this.placementPreference = placementPreference;
    }

    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public int getAiDifficulty() {
        return aiDifficulty;
    }
    public int getPlacementPreference() {
        return placementPreference;
    }

    public void start() {
        String p1Name = (player1 != null) ? player1.getName() : "Player1";
        String p2Name = (player2 != null) ? player2.getName() : "Player2";

        System.out.println("\n[Game] Starting game");
        System.out.println("Players: " + p1Name + " vs " + p2Name);
        if (aiDifficulty > 0) {
            System.out.println("AI difficulty: " + aiDifficulty);
        }
        System.out.println("Ship placement: " + (placementPreference == 1 ? "Auto" : "Manual"));

        // Placeholder for the actual game loop — press Enter to return to menu
        ConsoleHelper.getInput("Press Enter to end demo game and return to the menu...");
    }

}
