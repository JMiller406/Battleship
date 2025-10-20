import java.util.Scanner;

public class Shot {
    private int row;
    private int column;

    public Shot(String value) throws Exception {
        String normalizedValue = value.toLowerCase();
        this.row = rowFromHumanValue(normalizedValue);
        this.column = columnFromHumanValue(normalizedValue);
                        
                            }
                        
                            private int columnFromHumanValue(String normalizedValue) throws Exception {
                                String remaining = normalizedValue.substring(1);
                                int column = Integer.parseInt(remaining);
                                column -= 1; // 0 based index
                                if (column < 0 || column > 9) {
                                    throw new Exception("Invalid column for shot.");
                                }
                                return column;
                    }
                    
                
                            private int rowFromHumanValue(String normalizedValue) throws Exception {
                char firstLetter = normalizedValue.charAt(0);
                int asciiValue = (int)firstLetter;
                int row = asciiValue - 97;
                if(row < 0 || row > 9){
                    throw new Exception("Invalid row for shot.");
                }
                return row;
            }
        
            public String getCoordinate() {
        char firstLetter = (char) (row + 97);
        String result = Character.toString(firstLetter) + Integer.toString(column + 1);
        return result.toUpperCase();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    // Static method to prompt user for a shot coordinate and return a Shot object
    public static Shot getShotInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your shot (e.g., B5): ");
        String input = scanner.nextLine();

        try {
            return new Shot(input);
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
            return getShotInput(); // Recursively retry until valid input is given
        }
    }

    

}

