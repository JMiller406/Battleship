import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 

    public static String getInput(final String prompt) {
        String inStr = "";
        System.out.print(prompt);
        try {
            inStr = reader.readLine();
        } catch (final IOException e) {
            System.out.println("Error reading from user");
        }
        // Add a blank line after the user input so prompts don't look bunched
        System.out.println();
        return inStr;
    }

        public static int getNumberBetween(final String prompt, int min, int max){
        while (true) {
            String input = getInput(prompt);
            int retVal = 0;
            try {
                retVal = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println(String.format("Invalid entry.  Please enter a number between %d and %d", min, max));
                continue;
            }

            // entry was an integer
            if(retVal < min || retVal > max){
                System.out.println(String.format("Invalid entry.  Please enter a number between %d and %d", min, max));
                continue;
            }

            // entry passed all tests
            return retVal;
        }
    }
}
