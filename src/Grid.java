public abstract class Grid {

    // 2d array of Cells
    private Cell[][] cells = new Cell[10][10];

    public Grid() {
        // Initialize the grid
        for(int row = 0; row < 10; row++) {
            for(int column = 0; column < 10; column++){
                Cell cell = new Cell();
            }
        }
    }

    public int rows() {
        return cells.length;
    }

    public int cols() {
        return cells[0].length;
    }

    
}
