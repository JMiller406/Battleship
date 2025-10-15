public abstract class Grid {

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

@Override
public String toString() {
    StringBuilder sb = new StringBuilder();

    // Column headers
    sb.append("    "); // 4 spaces for row labels
    for (int col = 1; col <= 10; col++) {
        sb.append(String.format(" %2d ", col)); // width = 3 chars per column
    }
    sb.append("\n");

    // Top border
    sb.append(getBorder());

    // Rows
    for (int row = 0; row < 10; row++) {
        sb.append(getRow(row));
        sb.append(getBorder());
    }

    return sb.toString();
}

private String getBorder() {
    StringBuilder sb = new StringBuilder("   +"); // 3 spaces for row labels
    for (int col = 0; col < 10; col++) {
        sb.append("---+");
    }
    sb.append("\n");
    return sb.toString();
}

private String getRow(int row) {
    StringBuilder sb = new StringBuilder();
    char rowLabel = (char) ('A' + row);
    sb.append(String.format(" %c |", rowLabel)); // row letter with separator

    for (int col = 0; col < 10; col++) {
        Cell cell = cells[row][col];
        sb.append(String.format(" %s |", cell.displayChar())); // each cell 3 chars wide
    }
    sb.append("\n");
    return sb.toString();
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
   
}