package pieces;

import chess.Board;
import chess.Cell;

public class King extends Piece {
    public King(Colour colour) {
        super(colour);
    }

    @Override
    public boolean canMove(Board board, Cell cell, int destRow, int destCol) {
        int rowDiff = Math.abs(destRow - cell.getRow());
        int colDiff = Math.abs(destCol - cell.getCol());
        return (rowDiff <= 1 && colDiff <= 1);
    }
}