package pieces;

import chess.Board;
import chess.Cell;

public class Queen extends Piece {
    public Queen(Colour colour) {
        super(colour);
    }

    @Override
    public boolean canMove(Board board, Cell cell, int destRow, int destCol) {
        int rowDiff = Math.abs(destRow - cell.getRow());
        int colDiff = Math.abs(destCol - cell.getCol());
        return rowDiff == colDiff || cell.getRow() == destRow || cell.getCol() == destCol;
    }
}
