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
        String p1Name = player1.getName();
        String p2Name = player2.getName();

        System.out.println("[Starting Game] The War has started! " + p1Name + " vs " + p2Name);
        ConsoleHelper.getInput("Press Enter to begin...");

        Player currentPlayer = player1;
        Player opponentPlayer = player2;

        // Main game loop
        while (true) {
            clearScreen();

            System.out.println("\n======================================");
            System.out.println("       " + currentPlayer.getName() + "'s Turn");
            System.out.println("======================================\n");

            // Print grids only for human players. Never display AI's ocean/target grids.
            if (!(currentPlayer instanceof AiPlayer)) {
                // Print Target first (so player's view shows target above ocean)
                System.out.println(currentPlayer.getName() + "'s Target Grid (Your Shots)");
                currentPlayer.getTargetGrid().printColoredCompact(false);

                // Print Ocean Grid last
                System.out.println("\n" + currentPlayer.getName() + "'s Ocean Grid (Your Ships)");
                currentPlayer.getOceanGrid().printColoredCompact(true);
            } else {
                System.out.println(currentPlayer.getName() + "'s turn (computer).\n");
            }

            // Keep asking for shot until valid (not a duplicate)
            ShotResult result = null;
            while (result == null) {
                // If AI, pick and resolve a move automatically
                if (currentPlayer instanceof AiPlayer) {
                    AiPlayer ai = (AiPlayer) currentPlayer;
                    Coordinate shotCoord = ai.chooseRandomUntestedCoordinate();
                    if (shotCoord == null) {
                        System.out.println("AI couldn't find a move; skipping turn.");
                        break;
                    }
                    result = Shot.processShot(currentPlayer, opponentPlayer, shotCoord);
                    break;
                }

                // Human player's input
                System.out.println(
                        "\n" + currentPlayer.getName() + ", take your shot at " + opponentPlayer.getName() + ":");
                Shot shot = Shot.getShotInput();

                try {
                    Coordinate shotCoord = new Coordinate(shot.getCoordinate());

                    // resolve the shot using the central Shot resolver
                    result = Shot.processShot(currentPlayer, opponentPlayer, shotCoord);

                } catch (Exception e) {
                    System.out.println("\nX " + e.getMessage());
                    System.out.println("Please try again.\n");
                    // result stays null, loop continues
                }
            }
            // Shot validation loop ends here

            // Display the result 
            displayShotResult(result, currentPlayer.getName(), opponentPlayer.getName());

            // Check if opponent lost (all ships sunk)
            if (opponentPlayer.getOceanGrid().areAllShipsSunk()) {
                clearScreen();
                System.out.println("\n");
                System.out.println("╔════════════════════════════════════╗");
                System.out.println("║                                    ║");
                System.out.println("║         WAR IS OVER!               ║");
                System.out.println("║                                    ║");
                System.out.println("║  " + currentPlayer.getName() + " WINS!                       ║");
                System.out.println("║                                    ║");
                System.out.println("║  All of " + opponentPlayer.getName() + "'s ships              ║");
                System.out.println("║  have been sunk!                   ║");
                System.out.println("║                                    ║");
                System.out.println("╚════════════════════════════════════╝");
                System.out.println();
                break; // Exit the main game loop
            }

            // Pause before switching to next player
            ConsoleHelper.getInput("\nPress Enter to end your turn...");

            // Switch players
            Player temp = currentPlayer;
            currentPlayer = opponentPlayer;
            opponentPlayer = temp;
        }
        // Main game loop ends here

        ConsoleHelper.getInput("\nPress Enter to return to main menu...");
    }

    /**
     * Resolve a shot from shooter to target at coord.
     * Updates target's OceanGrid and shooter's TargetGrid and returns a ShotResult.
     */
    private ShotResult processShot(Player shooter, Player target, Coordinate coord) {
        ShotResult.ResultType type = ShotResult.ResultType.MISS;
        ShotResult result = null;

        Cell targetCell = target.getOceanGrid().cellAtCoordinate(coord);

        if (targetCell.hasShip()) {
            Ship ship = targetCell.getShip();
            ship.registerHit(coord);
            if (ship.isSunk()) {
                // mark all ship cells as SUNK on the target's ocean
                for (Coordinate c : ship.getCoords()) {
                    Cell sc = target.getOceanGrid().cellAtCoordinate(c);
                    sc.setState(CellState.SUNK);
                }
                type = ShotResult.ResultType.SUNK;
                // increment shooter's hit count for sunk
                shooter.hitCount++;

                // Update shooter's target grid for every coordinate of the sunk ship
                for (Coordinate c : ship.getCoords()) {
                    ShotResult r = new ShotResult(ShotResult.ResultType.SUNK, c);
                    r.setShipName(ship.getName());
                    shooter.getTargetGrid().recieveShotResult(r);
                }

                result = new ShotResult(type, coord);
                result.setShipName(ship.getName());
                return result;
            } else {
                targetCell.setState(CellState.HIT);
                type = ShotResult.ResultType.HIT;
                // increment shooter's hit count
                shooter.hitCount++;

                result = new ShotResult(type, coord);
                // Inform shooter target grid of result
                shooter.getTargetGrid().recieveShotResult(result);
                return result;
            }
        } else {
            targetCell.setState(CellState.MISS);
            type = ShotResult.ResultType.MISS;

            result = new ShotResult(type, coord);
            // Inform shooter target grid of result
            shooter.getTargetGrid().recieveShotResult(result);
            return result;
        }
    }

    private void displayShotResult(ShotResult result, String attackerName, String defenderName) {
        System.out.println("\n========== SHOT RESULT ==========");

        switch (result.getType()) {
            case HIT:
                System.out.println("HIT! " + attackerName + " hit " + defenderName + "'s ship at " + result.getShot() + "!");
                break;
            case MISS:
                System.out.println("MISS... " + attackerName + " missed at " + result.getShot() + ".");
                break;
            case SUNK:
                System.out.println("SUNK! " + attackerName + " SUNK " + defenderName + "'s " + result.getShipName() + "!");
                break;
        }
        System.out.println("=================================\n");
    }

    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

}
