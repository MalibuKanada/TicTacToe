public class Board {
    public static final int ROWS = 3;
    public static final int COLUMNS = 3;

    private int currentRow;
    private int currentColumn;


    Cell[][] cells;

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }


    public Board() {
        cells = new Cell[ROWS][COLUMNS];
        for (int row=0; row<ROWS; row++) {
            for (int column=0; column<COLUMNS; column++) {
                cells[row][column] = new Cell();
            }
        }
    }

    public void init() {
        for (int row=0; row<ROWS; row++) {
            for (int column=0; column<COLUMNS; column++) {
                cells[row][column].clear();
            }
        }
    }

    public boolean isDraw() {
        for (int row=0; row<ROWS; row++) {
            for (int column=0; column<COLUMNS; column++) {
                if(cells[row][column].getContent().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean hasWon(String activePlayer) {

       return(     cells[currentRow][0].getContent().equals(activePlayer) //3 in a row
                && cells[currentRow][1].getContent().equals(activePlayer)
                && cells[currentRow][2].getContent().equals(activePlayer)
                || cells[0][currentColumn].getContent().equals(activePlayer) // 3 in a column
                && cells[1][currentColumn].getContent().equals(activePlayer)
                && cells[2][currentColumn].getContent().equals(activePlayer)
                || cells[0][0].getContent().equals(activePlayer)
                && cells[1][1].getContent().equals(activePlayer)
                && cells[2][2].getContent().equals(activePlayer)
                || cells[0][2].getContent().equals(activePlayer)
                && cells[1][1].getContent().equals(activePlayer)
                && cells[2][0].getContent().equals(activePlayer)
        );
    }



    public void paint() {
        System.out.println("-------");
        for (int row=0; row<ROWS; row++) {
            System.out.print("|");
            for (int column=0; column<COLUMNS; column++) {
                cells[row][column].paint();
                System.out.print("|");
            }
            System.out.println();
            System.out.println("-------");
        }
    }
}
