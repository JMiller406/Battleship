public class Coordinate {
    private int row; // 0-9 for rows A-J
    private int col; // 0-9 for columns 1-10

    public Coordinate(int row, int col) {
        if (row < 0 || row >= 10 || col < 0 || col >= 10) {

        }
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
        return true;
        if (obj == null || getClass() != obj.getClass())
        return false;
        Coordinate that = (Coordinate) obj;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col; // simple hash code 
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

}
