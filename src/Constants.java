import java.util.Map;

public class Constants {
    // private static final String COLOR_CARRIER = "\u001B[48;5;19m"; // navy blue
    // private static final String COLOR_BATTLESHIP = "\u001B[48;5;52m"; // dark red
    // private static final String COLOR_DESTROYER = "\u001B[48;5;94m"; // purple
    // private static final String COLOR_SUBMARINE = "\u001B[48;5;28m"; // green
    // private static final String COLOR_PATROL_BOAT = "\u001B[48;5;202m"; // orange

    public final static Map<String, Integer> shipSpecifications = Map.of(
            "Carrier", 5,
            "Battleship", 4,
            "Destroyer", 3,
            "Submarine", 3,
            "Patrol Boat", 2);
    
    // // Expose a mapping from ship name to background color for rendering
    // public final static Map<String, String> shipColors = Map.of(
    //     "Carrier", COLOR_CARRIER,
    //     "Battleship", COLOR_BATTLESHIP,
    //     "Destroyer", COLOR_DESTROYER,
    //     "Submarine", COLOR_SUBMARINE,
    //     "Patrol Boat", COLOR_PATROL_BOAT
    // );
}
