import java.util.ArrayList;
import java.util.List;

public class Tutorial {

    public static void run() {
        System.out.println("\n=== Battleship Tutorial ===\n");
        System.out.println("Welcome to Battleship! This short tutorial will show you how to take shots and the goal of the game.");
        ConsoleHelper.getInput("Press Enter to continue...");

        // Explain coordinates
        System.out.println("Coordinates:\n- Rows are A-J, columns are 1-10. Examples: A1, B5, J10.");
        ConsoleHelper.getInput("Press Enter to continue...");

        // Demonstrate ocean grid (with ships visible)
        try {
            OceanGrid demoOcean = new OceanGrid();
            // make a demo ship of length 3 at B2-B4
            Ship demoShip = new Ship("Destroyer", 3);
            List<Coordinate> coords = new ArrayList<>();
            coords.add(new Coordinate("B2"));
            coords.add(new Coordinate("B3"));
            coords.add(new Coordinate("B4"));
            demoShip.setCoords(coords);
            List<Ship> ships = new ArrayList<>();
            ships.add(demoShip);
            demoOcean.assignShips(ships);

            System.out.println("This is an example Ocean grid (ships shown). Ships occupy cells:");
            demoOcean.printColoredCompact(true);
            ConsoleHelper.getInput("Press Enter to continue...");

            // Demonstrate target grid with a hit and a miss
            TargetGrid demoTarget = new TargetGrid();
            // mark B3 as a hit and C5 as a miss
            demoTarget.recieveShotResult(new ShotResult(ShotResult.ResultType.HIT, new Coordinate("B3")));
            demoTarget.recieveShotResult(new ShotResult(ShotResult.ResultType.MISS, new Coordinate("C5")));

            System.out.println("This is an example Target grid (what you see when firing):");
            demoTarget.printColoredCompact(false);
            System.out.println("Red = hits, Black = miss, yellow = sunk, Blue = water. (Colors may vary by terminal.)");
            ConsoleHelper.getInput("Press Enter to try firing a sample shot...");

            // Let the user enter a sample shot and show result based on demoOcean
            System.out.println("Enter a shot to try (e.g., B3 will be a hit on the demo):");
            Shot s = Shot.getShotInput();
            Coordinate shotCoord = new Coordinate(s.getCoordinate());
            Cell oceanCell = demoOcean.cellAtCoordinate(shotCoord);
            if (oceanCell.hasShip()) {
                System.out.println("That was a hit!");
                // register hit on demo ship and update target grid
                demoShip.registerHit(shotCoord);
                if (demoShip.isSunk()) {
                    // mark sunk on ocean and target
                    for (Coordinate c : demoShip.getCoords()) {
                        demoOcean.cellAtCoordinate(c).setState(CellState.SUNK);
                        demoTarget.recieveShotResult(new ShotResult(ShotResult.ResultType.SUNK, c));
                    }
                } else {
                    demoOcean.cellAtCoordinate(shotCoord).setState(CellState.HIT);
                    demoTarget.recieveShotResult(new ShotResult(ShotResult.ResultType.HIT, shotCoord));
                }
            } else {
                System.out.println("Miss.");
                demoOcean.cellAtCoordinate(shotCoord).setState(CellState.MISS);
                demoTarget.recieveShotResult(new ShotResult(ShotResult.ResultType.MISS, shotCoord));
            }

            System.out.println("Your updated Target grid:");
            demoTarget.printColoredCompact(false);
            ConsoleHelper.getInput("Press Enter to finish the tutorial...");

        } catch (Exception e) {
            System.out.println("Tutorial encountered an error: " + e.getMessage());
            ConsoleHelper.getInput("Press Enter to return to the menu...");
            return;
        }

        System.out.println("Tutorial complete. Goal: sink all opponent ships by hitting every cell they occupy.");
        ConsoleHelper.getInput("Press Enter to return to the menu...");
    }
}
