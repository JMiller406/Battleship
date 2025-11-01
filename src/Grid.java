public abstract class Grid {

    private static final String RESET = "\u001B[0m";
    private static final String BG_LIGHT_BLUE = "\u001B[48;5;159m"; // empty / sea
    private static final String BG_GREY = "\u001B[100m"; // occupied (ship)
    private static final String BG_RED = "\u001B[48;5;196m"; // hit
    private static final String BG_YELLOW = "\u001B[48;5;226m"; // miss
    private static final String BG_MAGENTA = "\u001B[48;5;201m"; // sunk

   
    private static String bgFor(CellState state, boolean showShips) {
        if (state == null) return BG_LIGHT_BLUE;
        switch (state) {
            case HIT:
                return BG_RED;
            case MISS:
                return BG_YELLOW;
            case SUNK:
                return BG_MAGENTA;
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
        for(int row = 0; row < 10; row++) {
            for(int col = 0; col < 10; col++){
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
        final String RESET_LOCAL = RESET;

        StringBuilder sb = new StringBuilder();

        // Column headers
        sb.append("    ");
        for (int col = 1; col <= 10; col++) sb.append(String.format("%2d ", col));
        sb.append('\n');

        // Top border
        sb.append("   +");
        for (int i = 0; i < 10; i++) sb.append("--+");
        sb.append('\n');

        for (int r = 0; r < 10; r++) {
            sb.append(String.format(" %c |", (char)('A' + r)));
                for (int c = 0; c < 10; c++) {
                    Cell cell = cells[r][c];
                    String bg = bgFor(cell.getState(), showShips);
                    // Only render background color; do not show any symbol/letter.
                    sb.append(bg).append("  ").append(RESET_LOCAL).append("|");
                }
            sb.append('\n');
        }

        // Bottom border
        sb.append("   +");
        for (int i = 0; i < 10; i++) sb.append("--+");
        sb.append('\n');

        System.out.print(sb.toString());
    }

}