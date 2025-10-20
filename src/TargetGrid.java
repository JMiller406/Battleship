public class TargetGrid extends Grid {
    public TargetGrid(){
        super();
    }

    
    public void recieveShotResult(ShotResult result){
        // returns the shot result
        Coordinate shot = result.getShot();
        Cell cell = cellAtCoordinate(shot);

        switch (result.getType()) {
            case MISS -> cell.setState(CellState.MISS);
            case HIT -> cell.setState(CellState.HIT);
            case SUNK -> cell.setState(CellState.SUNK);
        }
    }

    // Do not need at the moment
    // public boolean verifyShot(Coordinate shot){
    //     // verifies if the shot is valid and has a shotResult
    //         return false;

    // }
}