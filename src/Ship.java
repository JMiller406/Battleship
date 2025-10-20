import java.util.List;
import java.util.ArrayList;

public class Ship {
    private int length;
    private String name;
    private List<Coordinate> coords = new ArrayList<>();
    private List<Coordinate> hits = new ArrayList<>();

    public Ship(String name, int length) { 
        this.length = length;
        this.name = name;
        if (length <2 || length > 5) {
            throw new IllegalArgumentException("Ship length must be between 2 and 5");
        }
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public List<Coordinate> getCoords() {
        return coords;
    }

    public void setCoords(List<Coordinate> coords) {
        if (coords == null || coords.size() != length) {
            throw new IllegalArgumentException("Invalid coordinates for ship length " + length);
        }
        this.coords = coords;
    }

    // Return True if sunk, False otherwise
    public boolean isSunk() {
        return !coords.isEmpty() && hits.size() == coords.size();
    }

    // Register the hits on the ship
    public void registerHit(Coordinate c) {
        if (coords.contains(c) && !hits.contains(c)) {
            hits.add(c);
        }
    }

    
}
