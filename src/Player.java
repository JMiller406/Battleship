public class Player {
    String name;
    TargetGrid targetGrid;
    OceanGrid oceanGrid;
    int hitCount; //the amount of hits call it game
    
    // constructor used by GameConfiguration
    public Player(String name) {
        this.name = (name == null || name.isEmpty()) ? "Player" : name;
        this.targetGrid = new TargetGrid();
        this.oceanGrid = new OceanGrid();
        this.hitCount = 0;
    }

    public String getName() {
        return name;
    }

    protected void placeShips() {

    }

    protected void takeShot() {
        // focus on

    }

    protected Coordinate receiveShot(ShotResult ShotResult) {
        return ShotResult.getShot();

    }

    protected ShotResult receiveShotResult(ShotResult shotResult) {
        switch (shotResult.getType()) {
            case HIT:
                // Update game state, notify player, etc.
                System.out.println("Hit registered!");
                break;
            case MISS:
                System.out.println("Missed shot.");
                break;
            case SUNK:
                System.out.println("Ship sunk!");
                break;
        }
    
        return shotResult;
    }
    
    

    protected boolean areAllShipsSunk() {

        return false;

    }

    public OceanGrid getOceanGrid() {
        return oceanGrid;
    }

    public TargetGrid getTargetGrid() {
        return targetGrid;
    }







}


