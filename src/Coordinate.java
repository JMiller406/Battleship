import java.util.ArrayList;
import java.util.List;

// Coordinate represents a position on the board
public class Coordinate {
    private final int row;
    private final int col;

    public Coordinate(String value) throws Exception {
        String normalizedValue = value.toLowerCase();
        this.row = rowFromHumanValue(normalizedValue);
        this.col = columnFromHumanValue(normalizedValue);
    }

    public Coordinate(int row, int col) throws Exception {
        if (row < 0 || row >= 10 || col < 0 || col >= 10) {
            throw new IllegalArgumentException("Coordinate out of bounds: ");
        }
        this.row = row;
        this.col = col;
    }

    private int columnFromHumanValue(String normalizedValue) throws Exception {
        String remaining = normalizedValue.substring(1);
        int column = Integer.parseInt(remaining);
        column -= 1; // Convert to 0-based index
        if (column < 0 || column > 9) {
            throw new Exception("Invalid column for shot.");
        }
        return column;
    }

    private int rowFromHumanValue(String normalizedValue) throws Exception {
        char firstLetter = normalizedValue.charAt(0);
        int asciiValue = (int) firstLetter;
        int row = asciiValue - 97;
        if (row < 0 || row > 9) {
            throw new Exception("Invalid row for shot.");
        }
        return row;
    }

    public static Coordinate randomCoordinate() {
        try {
            return new Coordinate((int) (Math.random() * 10.0), (int) (Math.random() * 10.0));
        } catch (Exception e) {
            return null;
        }
    }

    // Getters for row and column
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public List<Coordinate> coordsInDirection(int length, Direction direction) throws Exception {
        List<Coordinate> retVal = new ArrayList<>();
        retVal.add(this); 

        for (int i = 1; i < length; i++) {
            int nextRow = retVal.get(i - 1).getRow();
            int nextCol = retVal.get(i - 1).getCol();
            switch (direction) {
                case NORTH -> nextRow -= 1;
                case SOUTH -> nextRow += 1;
                case EAST -> nextCol += 1;
                case WEST -> nextCol -= 1;
            }
            retVal.add(new Coordinate(nextRow, nextCol));
        }
        return retVal;
    }

    // Convert the coordinate from 0,3 to A4
    public static String toLabel(Coordinate coord) {
        char rowLetter = (char) ('A' + coord.getRow());
        return rowLetter + String.valueOf(coord.getCol() + 1);
    }

    // Define two coordinated as equal
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Coordinate that = (Coordinate) obj;
        return row == that.row && col == that.col;
    }

    // Hashcode for using Coordinate in collections
    @Override
    public String toString() {
        return Coordinate.toLabel(this);
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }
}
