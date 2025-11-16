import java.util.Random;

public class AiPlayer extends Player {
    private final Random rnd = new Random();

    public AiPlayer(String name) {
        super(name == null || name.isEmpty() ? "Computer" : name);
    }

    /**
     * Choose a random coordinate that has not been tried yet on the AI's target grid.
     * Returns null only in the unlikely event we cannot find an empty cell.
     */
    public Coordinate chooseRandomUntestedCoordinate() {
        // Try up to 200 attempts, then fallback to scanning
        for (int attempt = 0; attempt < 200; attempt++) {
            int r = rnd.nextInt(10);
            int c = rnd.nextInt(10);
            try {
                Coordinate coord = new Coordinate(r, c);
                Cell cell = getTargetGrid().cellAtCoordinate(coord);
                if (cell.getState() == CellState.EMPTY) return coord;
            } catch (Exception ignored) {
            }
        }

        // Fallback: scan grid for first EMPTY cell
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                try {
                    Coordinate coord = new Coordinate(r, c);
                    Cell cell = getTargetGrid().cellAtCoordinate(coord);
                    if (cell.getState() == CellState.EMPTY) return coord;
                } catch (Exception ignored) {
                }
            }
        }
        return null;
    }
}
