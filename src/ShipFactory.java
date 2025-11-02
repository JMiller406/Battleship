import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ShipFactory {

    // Size of the grid to prevent run off the grid
    protected static final int GRID_SIZE = 10;

    protected abstract Coordinate getStartCoordinate(Ship ship) throws Exception;

    protected abstract Direction getStartDirection() throws Exception;

    // ? = if : = else ternary operators
    // returns list of ship object sorted alph
    public List<Ship> getShipsSortedByName(Order order) {
        // creates new list from constants
        List<Ship> ships = createAllShips();
        // using lambda to sort list in place
        ships.sort(order == Order.ASC
                // alph (a-z)
                ? (s1, s2) -> s1.getName().compareTo(s2.getName())
                // desc (z-a)
                : (s1, s2) -> s2.getName().compareTo(s1.getName()));
        return ships;// return sorted list to caller
    }

    public List<Ship> getShipsSortedByLength(Order order) {
        List<Ship> ships = createAllShips();
        ships.sort(order == Order.ASC
                // shortest to longest
                ? (s1, s2) -> Integer.compare(s1.getLength(), s2.getLength())
                // longest to shortest
                : (s1, s2) -> Integer.compare(s2.getLength(), s1.getLength()));
        return ships;
    }

    // Helper to make ships from constants //
    protected List<Ship> createAllShips() {
        List<Ship> ships = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : Constants.shipSpecifications.entrySet()) {
            ships.add(new Ship(entry.getKey(), entry.getValue()));
        }
        return ships;
    }

    public List<Ship> getShips(List<Ship> ships) {
        List<Ship> placedShips = new ArrayList<>();

        // Loop the ships
        for (Ship ship : ships) {
            List<Coordinate> coords;

            while (true) {
                try {
                    Coordinate start = getStartCoordinate(ship);
                    Direction dir = getStartDirection();

                    coords = start.coordsInDirection(ship.getLength(), dir);

                    if (!isWithinBounds(coords) || isOverlapping(coords, placedShips)) {
                        continue;
                    }
                    ship.setCoords(coords);
                    placedShips.add(ship);
                    break;
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return placedShips;
    }

    // helper method to check if the ship can be placed at the given position
    protected boolean isWithinBounds(List<Coordinate> coords) {
        for (Coordinate c : coords) {
            if (c.getRow() < 0 || c.getRow() >= GRID_SIZE || c.getCol() < 0 || c.getCol() >= GRID_SIZE) {
                return false;
            }
        }
        return true;
    }

    // Helper method to prevent overlapping
    protected boolean isOverlapping(List<Coordinate> newCoords, List<Ship> existingShips) {
        for (Ship s : existingShips) {
            for (Coordinate existing : s.getCoords()) {
                if (newCoords.contains(existing)) {
                    return true;
                }
            }
        }
        return false;
    }
}