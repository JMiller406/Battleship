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

    // Return a string for the full grid
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBorder());
        sb.append(getHeader());
        for (int row = 0; row < 10; row++) {
            sb.append(getRow(row));
            sb.append(getBorder());
        }
        return sb.toString();
    }

    // Methods to print grid with headers and borders
    private String getHeader() {
        StringBuilder sb = new StringBuilder("|   "); 
        for (int col = 1; col <= 10; col++) {
            sb.append("| ").append(col < 10 ? " " + col : col);
            } // ternary operator for formatting ? = if : else
            sb.append("| \n");
            return sb.toString();
        }

    // Return a string for the top/bottom border
    private String getBorder() {
        StringBuilder sb = new StringBuilder("+");
        for (int col = 0; col < 11; col++) {
            sb.append("---+");
        } 
        sb.append('\n');
        return sb.toString();
    }

    // Return a string for a single row
    private String getRow(int row) {
        StringBuilder sb = new StringBuilder();
        // Row label
        char rowLabel = (char) ('A' + row); // Converting 0-9 to A-J
        sb.append("| ").append(rowLabel).append(" ");
        for (int col = 1; col < 10; col++) {
            Cell cell = cells[row][col];
            sb.append("| ").append(cell.displayChar()).append(' ');
        }
        sb.append("|\n");
        return sb.toString();
    }

    

}

// StringBuilder is used for efficient string concatentation. The benefit of using StringBuilder over regular string concatenation is that it is more memory efficient and faster, especially when building large strings in loops. Regular string concatenation creates multiple immutable string objects, which can lead to increased memory usage and slower performance due to the overhead of creating and garbage collecting these objects. StringBuilder, on the other hand, maintains a mutable array of characters that can be modified in place, reducing the need for creating new string objects and improving performance.
        