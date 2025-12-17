package chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<String> moves = new ArrayList<>();
    private char promotionChoice = ' ';
    private Board board;

    // Creates Board
    // In a loop prints Board in the console and accepts input
    // Handles draws, winners and resigns

    // Has a save and load method that is in PGN format

    // Has an interpreter method that transforms chess notations into move coordinates
    // gets the next move, interprets it from the user and gives it to Board

    public void saveGame(String filename) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filename);

        int moveNumber = 1;
        for(int i = 0; i < moves.size(); i++) {
            if(i % 2 == 0) {
                writer.print(moveNumber + ". ");
                moveNumber++;
            }
            writer.print(moves.get(i) + " ");
        }
        System.out.println("Data saved to " + filename);
        writer.close();
    }

    public Board loadGame(String filename) throws FileNotFoundException {
        Board board = new Board();
        boolean whiteToMove = true;

        File file = new File(filename);
        if(!file.exists()) throw new FileNotFoundException("File doesn't exist!");
        if(file.length() == 0) throw new FileNotFoundException("File is empty!");
        Scanner reader = new Scanner(file);

        while(reader.hasNextLine()) {
            String line = reader.nextLine().trim();
            if(line.isEmpty()) continue;
            if(line.startsWith("[")) continue;

            line = line.replaceAll("\\{.*?}", "");
            String[] tokens = line.split("\\s+");

            for(String token : tokens) {
                if (token.matches("\\d+\\.")) continue;
                moves.add(token);
                Coordinates[] move = interpretMove(token, whiteToMove, board); //turns a move into from and to coordinates
                Coordinates from = move[0];
                Coordinates to = move[1];
                board.makeMove(from, to, whiteToMove, promotionChoice);
                promotionChoice = ' ';
                whiteToMove = !whiteToMove;
            }
        }

        reader.close();
        return board;
    }

    // Helper func to find square by given file and rank
    private Coordinates parseSquare(char file, char rank) {
        int col = file - 'a';
        int row = 8 - (rank - '0');
//        int row = 8 - Character.getNumericValue(rank);
        return new Coordinates(row, col);
    }

    // Helper func to test if a char matches a piece on board
    // In a notation only capital letters are used when referring to a piece
    private static boolean matchesPiece(Piece p, char c) {
        return switch (c) {
            case 'P' -> p instanceof Pawn;
            case 'N' -> p instanceof Knight;
            case 'B' -> p instanceof Bishop;
            case 'R' -> p instanceof Rook;
            case 'Q' -> p instanceof Queen;
            case 'K' -> p instanceof King;
            default -> false;
        };
    }

    // Interprets algebraic notation into from/to coordinates
    public Coordinates[] interpretMove(String notation, boolean whiteTurn) {

        notation = notation.trim()
                .replace("+", "")
                .replace("#", "");

        // Castling
        if (notation.equals("O-O") || notation.equals("0-0")) {
            int row = whiteTurn ? 7 : 0;
            return new Coordinates[]{
                    new Coordinates(row, 4),
                    new Coordinates(row, 6)
            };
        }

        if (notation.equals("O-O-O") || notation.equals("0-0-0")) {
            int row = whiteTurn ? 7 : 0;
            return new Coordinates[]{
                    new Coordinates(row, 4),
                    new Coordinates(row, 2)
            };
        }

        // Promotion
        if (notation.contains("=")) {
            promotionChoice = notation.charAt(notation.length() - 1);
            notation = notation.substring(0, notation.indexOf("="));
        }

        // Capture
        boolean isCapture = notation.contains("x");
        notation = notation.replace("x", "");

        // Destination
        char file = notation.charAt(notation.length() - 2);
        char rank = notation.charAt(notation.length() - 1);
        Coordinates to = parseSquare(file, rank);

//        // Pawn move
//        if (Character.isLowerCase(notation.charAt(0))) {
//
//            int fromCol = notation.contains("x")
//                    ? notation.charAt(0) - 'a'
//                    : toCol;
//
//            int fromRow = whiteTurn ? toRow + 1 : toRow - 1;
//
//            // Double move
//            if (notation.length() == 2) {
//                if (whiteTurn && toRow == 4) fromRow = 6;
//                if (!whiteTurn && toRow == 3) fromRow = 1;
//            }
//
//            return new Coordinates[]{
//                    new Coordinates(fromRow, fromCol),
//                    new Coordinates(toRow, toCol)
//            };
//        }
//
//        // Piece move (minimal, no disambiguation)
//        int fromRow = whiteTurn ? toRow + 1 : toRow - 1;
//        return new Coordinates[]{
//                new Coordinates(fromRow, toCol),
//                to
//        };

        // Find piece type
        char pieceChar = Character.isUpperCase(notation.charAt(0))
                ? notation.charAt(0)
                : 'P';

        // Disambiguation (file or rank)
        Character disFile = null;
        Character disRank = null;

        if (notation.length() == 4) {
            char d = notation.charAt(1);
            if (Character.isDigit(d)) disRank = d;
            else disFile = d;
        }

        // Search board for matching piece
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordinates from = new Coordinates(i, j);
                Piece p = board.getPiece(from);

                if (p == null) continue;
                if (p.isWhite() != whiteTurn) continue;

                if (!matchesPiece(p, pieceChar)) continue;

                if (disFile != null && j != disFile - 'a') continue;
                if (disRank != null && i != 8 - (disRank - '0')) continue;

//                try {
//                    board.makeMove(from, to, whiteTurn, 'Q');
//                    board.undoMove(); // rollback test move
//                    return new Coordinates[] { from, to };
//                } catch (InvalidMove e) {
//                    // Not this piece, could be another
//                }

                char promotion = promotionChoice == ' ' ? 'Q' : promotionChoice;

                if(board.isLegalMove(from, to, whiteTurn, promotion)) {
                    return new Coordinates[] { from, to };
                }
            }
        }

        throw new InvalidMove("No legal move found for notation: " + notation);
    }
}


