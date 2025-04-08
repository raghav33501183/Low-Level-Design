package chess;

import pieces.Colour;

public class Player {
    private Colour colour;

    public Player(Colour colour) {
        this.colour = colour;
    }

    public Colour getColour() {
        return colour;
    }

    public void makeMove(Board board, Move move) {
        var cell = move.getCell();
        int destRow = move.getDestRow();
        int destCol = move.getDestCol();

        if (board.isValidMove(cell, destRow, destCol)) {
            board.setPiece(cell.getRow(), cell.getCol(), null);
            board.setPiece(destRow, destCol, cell.getPiece());
        } else {
            throw new InvalidMoveException("Invalid move!");
        }
    }
}