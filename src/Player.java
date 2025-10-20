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

    }

    protected void receiveShot() {

    }

    protected void receiveShotResult() {

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


