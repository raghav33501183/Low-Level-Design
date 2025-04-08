package pieces;


import chess.Board;
import chess.Cell;

public class Bishop extends Piece {
    public Bishop(Colour color) {
        super(color);
    }

    @Override
    public boolean canMove(Board board, Cell cell, int destRow, int destCol) {
        int rowDiff = Math.abs(destRow - cell.getRow());
        int colDiff = Math.abs(destCol - cell.getCol());
        return (rowDiff == colDiff);
    }
}
