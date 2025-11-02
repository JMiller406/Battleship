public class ManualShipFactory extends ShipFactory {

    @Override
    protected Coordinate getStartCoordinate(Ship ship) {
        while (true) {
            String input = ConsoleHelper.getInput("Enter coordinate for " + ship.getName() + " (EX: 'A5'): ");
            try {
                return new Coordinate(input);
            } catch (Exception e) {
                System.out.println("Invalid coordinate. Please enter A-J for rows and 1-10 for columns.");
            }
        }
    }

    @Override
    protected Direction getStartDirection() {
        while (true) {
            int value = ConsoleHelper.getNumberBetween("Enter the direction (1=NORTH, 2=SOUTH, 3=EAST, 4=WEST): ", 1, 4);
            Direction dir = Direction.equivalentTo(value);
            if (dir != null) return dir;
            System.out.println("Invalid direction. Try again.");
        }
    }
}
