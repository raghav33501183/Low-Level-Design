package chess;

import pieces.Piece;

public class Move {
    private Cell cell;
    private int destRow;
    private int destCol;

    public Move(Cell cell, int destRow, int destCol) {
        this.cell = cell;
        this.destRow = destRow;
        this.destCol = destCol;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getDestRow() {
        return destRow;
    }

    public void setDestRow(int destRow) {
        this.destRow = destRow;
    }

    public int getDestCol() {
        return destCol;
    }

    public void setDestCol(int destCol) {
        this.destCol = destCol;
    }
}
