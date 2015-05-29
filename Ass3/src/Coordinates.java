public class Coordinates {
    private int row;
    private int column;

    /**
     * Constructs a Coordinates object with the give row and column
     *
     * @param row    the coordinate for the row
     * @param column the coordinate for the column
     */
    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * @return the row coordinate
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the column coordinate
     */
    public int getCol() {
        return column;
    }

}