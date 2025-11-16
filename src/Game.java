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
        // not used for Jason's purposes but don't want to break anything
        String p1Name = (player1 != null) ? player1.getName() : "Player1";
        String p2Name = (player2 != null) ? player2.getName() : "Player2";
        // I don't have any logic implemeted for a two player game yet so it might act unexpectedly if both players are human

        System.out.println("[Game] Starting game");
        // Simple alternating turn loop between player1 and player2.
        // Human players should use Shot.getShotInput(); AiPlayer will pick random untried shots.
        Player current = player1;
        Player other = player2;

        while (true) {
            if (current == null || other == null) break;

            System.out.println("\n--- " + current.getName() + "'s turn ---");

            Coordinate coord = null;

            if (current instanceof AiPlayer) {
                AiPlayer ai = (AiPlayer) current;
                coord = ai.chooseRandomUntestedCoordinate();
                if (coord == null) {
                    System.out.println("AI couldn't find a move; exiting.");
                    break;
                }
                System.out.println(current.getName() + " fires at " + coord);
            } else {
                // human: prompt until a valid, untried coordinate is entered
                while (coord == null) {
                    System.out.println(current.getName() + ", take your shot:");
                    Shot shot = Shot.getShotInput();
                    try {
                        Coordinate c = new Coordinate(shot.getCoordinate());
                        Cell tcell = current.getTargetGrid().cellAtCoordinate(c);
                        if (tcell.getState() != CellState.EMPTY) {
                            System.out.println("You already fired at " + c + ". Try again.");
                            continue;
                        }
                        coord = c;
                    } catch (Exception e) {
                        System.out.println("Invalid coordinate: " + e.getMessage());
                    }
                }
            }

            ShotResult result = processShot(current, other, coord);
            // show result and the shooter's target view
            System.out.println("Result: " + result.getType() + (result.getShipName() != null ? (" ("+result.getShipName()+")") : ""));
            System.out.println(current.getName() + "'s Target Grid:");
            current.getTargetGrid().printColoredCompact(false);

            // If the shooter was a human, wait for them to press Enter so they can view the result
            if (!(current instanceof AiPlayer)) {
                ConsoleHelper.getInput("Press Enter to continue to the next turn...");
            }

            // Check for end of game: did the defender lose all ships?
            if (other.getOceanGrid().areAllShipsSunk()) {
                System.out.println();
                System.out.println("*** " + current.getName() + " wins! ***");

                // show final boards: winner's target and loser's ocean (with ships visible)
                System.out.println(current.getName() + "'s Target Grid:");
                current.getTargetGrid().printColoredCompact(false);

                System.out.println(other.getName() + "'s Ocean Grid (final):");
                other.getOceanGrid().printColoredCompact(true);

                // simple stats
                System.out.println();
                System.out.println("Final stats:");
                System.out.println(current.getName() + " hits: " + current.hitCount);

                ConsoleHelper.getInput("Press Enter to return to the menu...");
                return; // exit the game loop and return to menu
            }

            // swap turns
            Player tmp = current; current = other; other = tmp;
        }
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

}
