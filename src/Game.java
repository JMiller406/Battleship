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
        // I don't have any logic implemeted for a two player game yet so it might act unexpectedly if both players are human

        System.out.println("[Game] Starting game");

    // Placeholder for the actual game loop — press Enter to return to menu
        ConsoleHelper.getInput("Press Enter to diplay example grids and end demo...");

        // Print Target first (so player's view shows target above ocean)
        System.out.println(p1Name + "'s Target:");
        if (player1 != null && player1.getTargetGrid() != null) {
            player1.getTargetGrid().printColoredCompact(false);
        } else {
            System.out.println("(no target grid available)");
        }

        System.out.println("\n" + p1Name + "'s Ocean:");
        if (player1 != null && player1.getOceanGrid() != null) {
            // use the compact colored renderer for final product
            player1.getOceanGrid().printColoredCompact(true);
        } else {
            System.out.println("(no ocean grid available)");
        }

        // Prompt player for a single shot (demo wiring of Shot.getShotInput)
        System.out.println();
        System.out.println(p1Name + ", take your shot:");
        Shot shot = Shot.getShotInput();
        try {
            Coordinate c = new Coordinate(shot.getCoordinate());
            System.out.println("You fired at " + c);
        } catch (Exception e) {
            System.out.println("Failed to parse shot coordinate: " + e.getMessage());
        }
    }

}
