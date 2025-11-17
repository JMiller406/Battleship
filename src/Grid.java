public abstract class Grid {

    // Shot/Cell Status
    public static final String RESET = "\u001B[0m";
    public static final String BG_LIGHT_BLUE = "\u001B[48;5;159m"; // empty / sea
    public static final String BG_GREY = "\u001B[100m"; // occupied (ship)
    public static final String BG_RED = "\u001B[48;5;196m"; // hit
    public static final String BG_YELLOW = "\u001B[48;5;226m"; // sunk
    public static final String BG_WHITE = "\u001B[47m"; // miss background (white)
    public static final String BG_BLACK = "\u001B[40m"; // miss background (black)

    // Menu Colors
    public static final String U_RED = "\u001B[4;31m"; // underline red
    public static final String U_BLUE = "\u001B[4;34m"; // underline blue
    public static final String RED = "\u001B[31m"; // Red
    public static final String BLUE = "\u001B[34m"; // Blue
    public static final String YELLOW = "\u001B[33m"; // Yellow
    public static final String ORANGE = "\u001B[38;5;208m"; // Orange
    public static final String GREEN = "\u001B[0;32m"; // green
    public static final String BLACK = "\u001B[0;30m"; // black for the |border

    private static String bgFor(CellState state, boolean showShips) {
        if (state == null)
            return BG_LIGHT_BLUE;
        switch (state) {
            case HIT:
                return BG_RED;
            case MISS:
                return BG_BLACK;
            case SUNK:
                return BG_YELLOW;
            case OCCUPIED:
                return showShips ? BG_GREY : BG_LIGHT_BLUE;
            case EMPTY:
            default:
                return BG_LIGHT_BLUE;
        }
    }

    // 2d array of Cells
    protected Cell[][] cells = new Cell[10][10];

    // Constructor
    public Grid() {
        // Initialize the grid
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                cells[row][col] = new Cell();
            }
        }
    }

    public Cell cellAtCoordinate(Coordinate coord) {
        if (coord == null) {
            throw new IllegalArgumentException("Coordinate cannot be null");
        }
        int r = coord.getRow();
        int c = coord.getCol();
        if (r < 0 || r >= cells.length || c < 0 || c >= cells[0].length) {
            throw new IllegalArgumentException("Coordinate out of bounds: " + coord);
        }
        return cells[r][c];
    }

    public void printGrid() {
        boolean showShips = (this instanceof OceanGrid);
        printColoredCompact(showShips);
    }

    public void printColoredCompact(boolean showShips) {
    StringBuilder sb = new StringBuilder();

    // Column headers
    sb.append("   ");
    for (int col = 1; col <= 10; col++)
        sb.append(String.format("%3d", col));
    sb.append('\n');

    // Top border (continuous)
    sb.append("   +");
    for (int i = 0; i < 15; i++)
        sb.append("--"); // two dashes per cell
    sb.append("+\n");

    // Grid body
    for (int r = 0; r < 10; r++) {
        // Row label and left border
        sb.append(String.format(" %c ", (char) ('A' + r)))
          .append(BLACK)
          .append("|")
          .append(RESET);

        for (int c = 0; c < 10; c++) {
            Cell cell = cells[r][c];
            String bg = bgFor(cell.getState(), showShips);

            // Cell background (no internal dividers)
            sb.append(bg).append("   ").append(RESET);
        }

        // Right border
        sb.append(BLACK).append("|").append(RESET).append('\n');
    }

    // Bottom border (continuous)
    sb.append("   +");
    for (int i = 0; i < 15; i++)
        sb.append("--");
    sb.append("+\n");

    System.out.print(sb.toString());
}

}