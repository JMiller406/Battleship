import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GridColorTest {

    @Test
    void printColoredCompact_emitsAnsiBackgrounds() throws Exception {
        OceanGrid ocean = new OceanGrid();
        ocean.cellAtCoordinate(new Coordinate(1,1)).setState(CellState.OCCUPIED);
        ocean.cellAtCoordinate(new Coordinate(2,2)).setState(CellState.HIT);
        ocean.cellAtCoordinate(new Coordinate(3,3)).setState(CellState.MISS);

        // Capture stdout so test runner output can display it
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            ocean.printColoredCompact(true);
        } finally {
            System.setOut(oldOut);
        }

        String rendered = out.toString();
        System.err.println("--- Grid renderer output START ---");
        System.err.println(rendered);
        System.err.println("--- Grid renderer output END ---");

        assertTrue(rendered.contains("\u001B["), "Expected ANSI escape codes in renderer output");
        assertTrue(rendered.contains("1  "), "Expected column headers in output");
    }
}
