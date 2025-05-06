package chess;

import pieces.Colour;

import java.util.Scanner;

public class ChessGame {
    private Board board;
    private Player[] players;
    private int currentPlayer;

    public ChessGame() {
        board = new Board();
        players = new Player[] {new Player(Colour.WHITE), new Player(Colour.BLACK)};
        currentPlayer = 0;
    }

    public void start() {
        // Game loop
        while (!isGameOver()) {
            var player = players[currentPlayer];
            System.out.println(player.getColour() + "'s turn.");

            // Get move from the player
            var move = getPlayerMove(player);

            // Make the move on the board
            try {
                player.makeMove(board, move);
            } catch (InvalidMoveException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again!");
                continue;
            }

            // Switch to the next player
            currentPlayer = (currentPlayer + 1) % 2;
        }

        // Display game result
        displayResult();
    }

    private boolean isGameOver() {
        return board.isCheckmate(players[0].getColour()) || board.isCheckmate(players[1].getColour()) ||
                board.isStalemate(players[0].getColour()) || board.isStalemate(players[1].getColour());
    }

    private Move getPlayerMove(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter source row: ");
        int sourceRow = scanner.nextInt();
        System.out.print("Enter source column: ");
        int sourceCol = scanner.nextInt();
        System.out.print("Enter destination row: ");
        int destRow = scanner.nextInt();
        System.out.print("Enter destination column: ");
        int destCol = scanner.nextInt();

        var piece = board.getPiece(sourceRow, sourceCol);
        if (piece == null || piece.getColour() != player.getColour()) {
            throw new InvalidMoveException("Invalid piece selection!");
        }

        return new Move(board.getCells()[sourceRow][sourceCol], destRow, destCol);
    }

    private void displayResult() {
        if (board.isCheckmate(Colour.WHITE)) {
            System.out.println("Black wins by checkmate!");
        } else if (board.isCheckmate(Colour.BLACK)) {
            System.out.println("White wins by checkmate!");
        } else if (board.isStalemate(Colour.WHITE) || board.isStalemate(Colour.BLACK)) {
            System.out.println("The game ends in a stalemate!");
        }
    }
}
