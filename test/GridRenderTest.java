public class GridRenderTest {

    public static void main(String[] args) {
        try {
            System.out.println("=== Scenario 1: Ocean with ships (revealed)");
            OceanGrid ocean1 = new OceanGrid();
            // place simple ships by marking OCCUPIED
            ocean1.cellAtCoordinate(new Coordinate(1,1)).setState(CellState.OCCUPIED);
            ocean1.cellAtCoordinate(new Coordinate(1,2)).setState(CellState.OCCUPIED);
            ocean1.cellAtCoordinate(new Coordinate(1,3)).setState(CellState.OCCUPIED);
            ocean1.cellAtCoordinate(new Coordinate(1,4)).setState(CellState.OCCUPIED);
            ocean1.cellAtCoordinate(new Coordinate(5,6)).setState(CellState.OCCUPIED);
            ocean1.cellAtCoordinate(new Coordinate(6,6)).setState(CellState.OCCUPIED);
            ocean1.cellAtCoordinate(new Coordinate(7,6)).setState(CellState.OCCUPIED);
            ocean1.printColoredCompact(true);

            System.out.println();
            System.out.println("=== Scenario 2: Ocean with hits and misses");
            OceanGrid ocean2 = new OceanGrid();
            // ship and hit/miss
            ocean2.cellAtCoordinate(new Coordinate(2,2)).setState(CellState.OCCUPIED);
            ocean2.cellAtCoordinate(new Coordinate(2,3)).setState(CellState.OCCUPIED);
            // hit overwrites occupied
            ocean2.cellAtCoordinate(new Coordinate(2,3)).setState(CellState.HIT);
            ocean2.cellAtCoordinate(new Coordinate(4,4)).setState(CellState.MISS);
            ocean2.cellAtCoordinate(new Coordinate(9,9)).setState(CellState.SUNK);
            ocean2.printColoredCompact(true);

            System.out.println();
            System.out.println("=== Scenario 3: Target with hits/misses (ships hidden)");
            TargetGrid target = new TargetGrid();
            target.cellAtCoordinate(new Coordinate(1,2)).setState(CellState.HIT);
            target.cellAtCoordinate(new Coordinate(3,3)).setState(CellState.MISS);
            target.cellAtCoordinate(new Coordinate(9,9)).setState(CellState.SUNK);
            target.printColoredCompact(false);

        } catch (Exception e) {
            System.out.println("Test setup failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
