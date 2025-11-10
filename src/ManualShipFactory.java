import java.util.ArrayList;
import java.util.List;

public class ManualShipFactory extends ShipFactory {

    @Override
    public List<Ship> getShips(List<Ship> ships) {
        List<Ship> placedShips = new ArrayList<>();

        for (Ship ship : ships) {
            while (true) {
                try {
                    // Ask for starting coordinate
                    Coordinate start = getStartCoordinate(ship);
                    // Ask for direction
                    Direction dir = getStartDirection();
                    // Generate coordinates in that direction
                    List<Coordinate> coords = start.coordsInDirection(ship.getLength(), dir);

                    // Check bounds
                    if (!isWithinBounds(coords)) {
                        System.out.println("Placement out of bounds. Try again for " + ship.getName() + ".");
                        continue;
                    }

                    // Check overlap
                    if (isOverlapping(coords, placedShips)) {
                        System.out.println("Placement overlaps another ship. Try again for " + ship.getName() + ".");
                        continue;
                    }

                    // Valid placement, assign to ship and add to list
                    ship.setCoords(coords);
                    placedShips.add(ship);
                    System.out.println(ship.getName() + " placed at: " + coords);
                    break; // Move to next ship

                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again for " + ship.getName() + ".");
                }
            }
        }
        return placedShips;
    }

    @Override
    protected Coordinate getStartCoordinate(Ship ship) {
        while (true) {
            try {
                String input = ConsoleHelper.getInput("Enter coordinate for " + ship.getName() + " (EX: 'A5'): ");
                return new Coordinate(input);
            } catch (Exception e) {
                System.out.println("Invalid coordinate. Please enter A-J for rows and 1-10 for columns.");
            }
        }

    }

    @Override
    protected Direction getStartDirection() {
        while (true) {
            int value = ConsoleHelper.getNumberBetween("Enter the direction (1=NORTH, 2=SOUTH, 3=EAST, 4=WEST): ", 1,
                    4);
            Direction dir = Direction.equivalentTo(value);
            if (dir != null)
                return dir;
            System.out.println("Invalid direction. Try again.");
        }
    }
}
