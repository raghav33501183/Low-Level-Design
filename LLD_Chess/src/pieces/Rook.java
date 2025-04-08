package pieces;

import chess.Board;
import chess.Cell;

public class Rook extends Piece {
    public Rook(Colour colour) {
        super(colour);
    }

    @Override
    public boolean canMove(Board board, Cell cell, int destRow, int destCol) {
        return (cell.getRow() == destRow || cell.getCol() == destCol);
    }
}
