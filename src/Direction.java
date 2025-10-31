import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public enum Direction {
    NORTH(1), 
    SOUTH(2), 
    EAST(3), 
    WEST(4);

    // ↑ Key: integer value Value: Directions
    private int value;

    // Dictionary to map integer values to Directions
    private static Map<Integer, Direction> map = new HashMap<>();

    // Constructor
    private Direction(int value){
        this.value = value;
    }

    static {
        for(Direction dir : Direction.values()){
            map.put(dir.value, dir);
        }
    }

    public static Direction equivalentTo(int value){
        return map.get(value);
    } // for manualShipFactory to use

    public int getValue() {
        return this.value;
    }

    private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Direction randomDirection()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    } // For automaticShipFactory to use
}