package chess;

import pieces.Piece;

public class Cell {
    private Piece piece;
    private int row;
    private int col;

    public Cell(int x, int y) {
        this.row = x;
        this.col = y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
