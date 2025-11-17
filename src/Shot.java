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
        String input = ConsoleHelper.getInput("Enter your shot (e.g., B5): ");

        try {
            return new Shot(input);
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
            return getShotInput(); // Recursively retry until valid input is given
        }
    }

    /**
     * Central shot resolver. Moves shot resolution out of Game so other flows can call it.
     */
    public static ShotResult processShot(Player shooter, Player target, Coordinate coord) {
        Cell targetCell = target.getOceanGrid().cellAtCoordinate(coord);

        if (targetCell.hasShip()) {
            Ship ship = targetCell.getShip();
            ship.registerHit(coord);
            if (ship.isSunk()) {
                // mark all ship cells as SUNK on the target's ocean
                for (Coordinate c : ship.getCoords()) {
                    Cell sc = target.getOceanGrid().cellAtCoordinate(c);
                    sc.setState(CellState.SUNK);
                }
                // increment shooter's hit count for sunk
                shooter.hitCount++;

                // Update shooter's target grid for every coordinate of the sunk ship
                for (Coordinate c : ship.getCoords()) {
                    ShotResult r = new ShotResult(ShotResult.ResultType.SUNK, c);
                    r.setShipName(ship.getName());
                    shooter.getTargetGrid().recieveShotResult(r);
                }

                ShotResult result = new ShotResult(ShotResult.ResultType.SUNK, coord);
                result.setShipName(ship.getName());
                return result;
            } else {
                targetCell.setState(CellState.HIT);
                // increment shooter's hit count
                shooter.hitCount++;

                ShotResult result = new ShotResult(ShotResult.ResultType.HIT, coord);
                shooter.getTargetGrid().recieveShotResult(result);
                return result;
            }
        } else {
            targetCell.setState(CellState.MISS);
            ShotResult result = new ShotResult(ShotResult.ResultType.MISS, coord);
            shooter.getTargetGrid().recieveShotResult(result);
            return result;
        }
    }

    

}

