package pieces;

import chess.Board;
import chess.Cell;

public abstract class Piece {
    private Colour colour;

    public Piece(Colour colour) {
        this.colour = colour;
    }

    public abstract boolean canMove(Board board, Cell cell, int destRow, int destCol);

    public Colour getColour() {
        return colour;
    }

    public void setColor(Colour colour) {
        this.colour = colour;
    }
}
