public class ShotResult {
    public enum ResultType {
    HIT,
    MISS,
    SUNK;
    }
    private ResultType type;
    private Coordinate shot;
    private String shipName;

    public ShotResult(ResultType type, Coordinate shot) {
        this.type = type;
        this.shot = shot;
    }

    public ResultType getType() {
        return type;
    }

    public Coordinate getShot() {
        return shot;
    }

    public void setShot(Coordinate shot) {
        this.shot = shot;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
    
}

