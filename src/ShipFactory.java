// import java.util.ArrayList;
// import java.util.List;

public abstract class ShipFactory {

	// Default implementation throws; subclasses may override if they support
	// automated or manual placement.
	protected Coordinate getStartCoordinate(Ship ship) {
		throw new UnsupportedOperationException("getStartCoordinate not implemented");
	}

	protected Direction getDirection() {
		throw new UnsupportedOperationException("getDirection not implemented");
	}

}