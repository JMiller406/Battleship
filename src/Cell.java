public class Cell {
    private CellState state = CellState.EMPTY;
    private Ship Ship = null;

    public CellState CellState() {
        return state;
    }

    public void setState(CellState state){
        this.state = state;
    }
    public Ship getShip() {
        return Ship;
    }
    public void setShip(Ship ship) {
        this.Ship = ship;
    }
    
}
