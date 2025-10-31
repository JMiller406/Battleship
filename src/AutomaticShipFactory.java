import java.util.Random;

public class AutomaticShipFactory extends ShipFactory {
    // private final Random rand = new Random();

    @Override
    protected Coordinate getStartCoordinate(Ship ship) {

        return Coordinate.randomCoordinate();
    }

    @Override
    protected Direction getDirection() {
        return Direction.randomDirection();
    }
}