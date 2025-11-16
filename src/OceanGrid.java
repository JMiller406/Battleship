import java.util.List;
import java.util.ArrayList;

public class OceanGrid extends Grid {
    private List<Ship> ships = new ArrayList<>();

    public OceanGrid() {
        super();
    }

    /**
     * Return true when all assigned ships are sunk.
     * If no ships have been assigned, returns false.
     */
    public boolean areAllShipsSunk() {
        if (ships == null || ships.isEmpty()) return false;
        for (Ship s : ships) {
            if (!s.isSunk()) return false;
        }
        return true;
    }

    // Do not need at the moment
    // public void  recieveShotResult() {
    //     // recieves the shot result here
    // }

    // public boolean verifyShotCoordintates() {
    //     // verifies if the shot Coordinate is a valid input
    //     return true;
    // }


    public void assignShips(List<Ship> ships) {
        for (Ship ship : ships) {
            for (Coordinate c : ship.getCoords()) {
                Cell cell = cellAtCoordinate(c);

                if (cell.getState() == CellState.OCCUPIED) {
                    throw new IllegalArgumentException("Ships are overlapping" + c);
                }
                cell.setState(CellState.OCCUPIED);
                cell.setShip(ship);
            }
            this.ships.add(ship);
            
    }
    
    }
}