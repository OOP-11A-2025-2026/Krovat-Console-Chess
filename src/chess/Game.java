package chess;

public class Game {
    // Creates Board
    // In a loop prints Board in the console and accepts input
    // Handles draws, winners and resigns

    // Has a save and load method that is in PGN format

    // Has an interpreter method that transforms chess notations into move coordinates
    // gets the next move, interprets it from the user and gives it to Board


    /// interpretator 

    // Interprets algebraic notation into from/to coordinates
    public Coordinates[] interpretMove(String notation, boolean whiteTurn) {

        notation = notation.trim()
                .replace("+", "")
                .replace("#", "");

        // Castling
        if (notation.equals("O-O")) {
            int row = whiteTurn ? 7 : 0;
            return new Coordinates[]{
                    new Coordinates(row, 4),
                    new Coordinates(row, 6)
            };
        }

        if (notation.equals("O-O-O")) {
            int row = whiteTurn ? 7 : 0;
            return new Coordinates[]{
                    new Coordinates(row, 4),
                    new Coordinates(row, 2)
            };
        }

        // Promotion
        if (notation.contains("=")) {
            notation = notation.substring(0, notation.indexOf("="));
        }

        // Destination
        char file = notation.charAt(notation.length() - 2);
        char rank = notation.charAt(notation.length() - 1);

        int toCol = file - 'a';
        int toRow = 8 - Character.getNumericValue(rank);

        // Pawn move
        if (Character.isLowerCase(notation.charAt(0))) {

            int fromCol = notation.contains("x")
                    ? notation.charAt(0) - 'a'
                    : toCol;

            int fromRow = whiteTurn ? toRow + 1 : toRow - 1;

            // Double move
            if (notation.length() == 2) {
                if (whiteTurn && toRow == 4) fromRow = 6;
                if (!whiteTurn && toRow == 3) fromRow = 1;
            }

            return new Coordinates[]{
                    new Coordinates(fromRow, fromCol),
                    new Coordinates(toRow, toCol)
            };
        }

        // Piece move (minimal, no disambiguation)
        int fromRow = whiteTurn ? toRow + 1 : toRow - 1;

        return new Coordinates[]{
                new Coordinates(fromRow, toCol),
                new Coordinates(toRow, toCol)
        };
    }


    /// Interpretator end
}













