package ass3;

public class Coordinates {
    private int row;
    private int column;

    /**
     * Creates dimensions for a piece
     * Used for when a player win
     *
     * @param row    is the row of a piece
     * @param column is the column of a piece
     */
    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * @return the row of the piece
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the column of the piece
     */
    public int getCol() {
        return column;
    }
}
