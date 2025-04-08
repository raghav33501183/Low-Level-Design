package pieces;

import chess.Board;
import chess.Cell;

public class Pawn extends Piece {
    public Pawn(Colour colour) {
        super(colour);
    }

    @Override
    public boolean canMove(Board board, Cell cell, int destRow, int destCol) {
        int rowDiff = destRow - cell.getRow();
        int colDiff = Math.abs(destCol - cell.getCol());

        if (cell.getPiece().getColour() == Colour.WHITE) {
            return (rowDiff == 1 && colDiff == 0) ||
                    (cell.getRow() == 1 && rowDiff == 2 && colDiff == 0) ||
                    (rowDiff == 1 && colDiff == 1 && board.getPiece(destRow, destCol) != null);
        } else {
            return (rowDiff == -1 && colDiff == 0) ||
                    (cell.getRow() == 6 && rowDiff == -2 && colDiff == 0) ||
                    (rowDiff == -1 && colDiff == 1 && board.getPiece(destRow, destCol) != null);
        }
    }
}
