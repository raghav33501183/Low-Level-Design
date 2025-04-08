package chess;

import pieces.*;

public class Board {
    private Cell[][] cells;

    public Board() {
        cells = new Cell[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        for (var i = 0; i < 8; i++) {
            for (var j = 0; j < 8; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }

        cells[0][0].setPiece(new Rook(Colour.WHITE));
        cells[0][1].setPiece(new Knight(Colour.WHITE));
        cells[0][2].setPiece(new Bishop(Colour.WHITE));
        cells[0][3].setPiece(new Queen(Colour.WHITE));
        cells[0][4].setPiece(new King(Colour.WHITE));
        cells[0][5].setPiece(new Bishop(Colour.WHITE));
        cells[0][6].setPiece(new Knight(Colour.WHITE));
        cells[0][7].setPiece(new Rook(Colour.WHITE));

        for (var i = 0; i < 8; ++i) {
            cells[1][i].setPiece(new Pawn(Colour.WHITE));
        }

        cells[0][0].setPiece(new Rook(Colour.BLACK));
        cells[0][1].setPiece(new Knight(Colour.BLACK));
        cells[0][2].setPiece(new Bishop(Colour.BLACK));
        cells[0][3].setPiece(new Queen(Colour.BLACK));
        cells[0][4].setPiece(new King(Colour.BLACK));
        cells[0][5].setPiece(new Bishop(Colour.BLACK));
        cells[0][6].setPiece(new Knight(Colour.BLACK));
        cells[0][7].setPiece(new Rook(Colour.BLACK));

        for (var i = 0; i < 8; ++i) {
            cells[6][i].setPiece(new Pawn(Colour.BLACK));
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Piece getPiece(int row, int col) {
        return cells[row][col].getPiece();
    }

    public void setPiece(int row, int col, Piece piece) {
        cells[row][col].setPiece(piece);
    }

    public boolean isValidMove(Cell cell, int destRow, int destCol) {
        var piece = cell.getPiece();
        if (piece == null || destRow < 0 || destRow > 7 || destCol < 0 || destCol > 7) {
            return false;
        }

        var destPiece = cells[destRow][destCol].getPiece();
        return (destPiece == null || destPiece.getColour() != piece.getColour()) &&
                piece.canMove(this, cell, destRow, destCol);
    }

    public boolean isCheckmate(Colour color) {
        // TODO: Implement checkmate logic
        return false;
    }

    public boolean isStalemate(Colour color) {
        // TODO: Implement stalemate logic
        return false;
    }
}
