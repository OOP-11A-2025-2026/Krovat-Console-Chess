package chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<String> moves = new ArrayList<>();

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
        char promotionChoice = 'Q';

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
                Coordinates[] move = interpretMove(token, whiteToMove, board, promotionChoice); //turns a move into from and to coordinates
                Coordinates from = move[0];
                Coordinates to = move[1];
                board.makeMove(from, to, whiteToMove, promotionChoice);
                whiteToMove = !whiteToMove;
            }
        }

        reader.close();
        return board;
    }

    // Interprets algebraic notation into from/to coordinates
    public static Coordinates[] interpretMove(String notation, boolean whiteTurn) {

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
        char promotion;
        if (notation.contains("=")) {
            promotion = notation.charAt(notation.length() - 1);
            notation = notation.substring(0, notation.indexOf("="));
        }

        // Capture
        boolean isCapture = notation.contains("x");
        notation = notation.replace("x", "");

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
}


