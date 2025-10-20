public class Cell {

    private CellState state;
    private Ship ship;

    public CellState CellState() {
        return state;
    }

    public Cell() {
        this.state = CellState.EMPTY;
        this.ship = null;
    }

    public Ship getShip() {
        return ship;
    }
    public void setShip(Ship ship) {
        if (state == CellState.EMPTY) {
            this.state = CellState.OCCUPIED;
        }
        this.ship = ship;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        if (state == CellState.SUNK) {
            throw new IllegalArgumentException("Cannot change state of a SUNK cell");
        }
        this.state = state;
    }

    public boolean hasShip() {
        return ship != null;
    }

    public String getSymbol() {
        switch (state) {
            case EMPTY: return " "; // blank space
            case OCCUPIED: return "O";// O displayed
            case HIT: return "X";// X displayed
            case MISS: return "M" ;// * displayed
            case SUNK: return "S";// S displayed
            default: return " ";// default "  "
        }
    }

    public String displayChar() {
        return getSymbol();
    }

    @Override
    public String toString() {
        return getSymbol();
    }
    
}
