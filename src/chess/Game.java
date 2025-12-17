package chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private final ArrayList<String> moves = new ArrayList<>();
    private String defaultFile = "src/example.pgn";
    private char promotionChoice = ' ';
    private Board board;

    // =========================
    // GAME START
    // =========================
    public void start() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - New Game");
        System.out.println("2 - Load Game");
        System.out.print("Choice: ");

        String choice = scanner.nextLine().trim();
        boolean whiteTurn = true;
        board = new Board();

        if (choice.equals("2")) {
            try {
                loadGame(defaultFile);
                whiteTurn = (moves.size() % 2 == 0);
                System.out.println("Game loaded.");
            } catch (FileNotFoundException e) {
                System.out.println("Load failed: " + e.getMessage());
                return;
            }
        }
        else if (!choice.equals("1")) {
            System.out.println("Invalid choice.");
            return;
        }

        // =========================
        // MAIN GAME LOOP
        // =========================
        while (true) {

            System.out.println(board);
            System.out.print((whiteTurn ? "White" : "Black") + " to move: ");

            String input = scanner.nextLine().trim();

            // ---- SAVE ----
            if (input.startsWith("save")) {
                String[] parts = input.split("\\s+");
                if (parts.length != 2) {
                    System.out.println("Usage: save filename.pgn");
                    continue;
                }
                try {
                    saveGame(parts[1]);
                } catch (FileNotFoundException e) {
                    System.out.println("Save failed: " + e.getMessage());
                }
                continue;
            }

            // ---- RESIGN ----
            if (input.equalsIgnoreCase("resign")) {
                System.out.println((whiteTurn ? "Black" : "White") + " wins by resignation.");
                break;
            }

            // ---- DRAW ----
            if (input.equalsIgnoreCase("draw")) {
                System.out.println("Game drawn by agreement.");
                break;
            }

            try {
                Coordinates[] move = interpretMove(input, whiteTurn);
                Coordinates from = move[0];
                Coordinates to = move[1];

                int result = board.makeMove(from, to, whiteTurn, promotionChoice);
                promotionChoice = ' ';
                moves.add(input);

                if (result == 1) {
                    System.out.println(board);
                    System.out.println("CHECKMATE!");
                    System.out.println((whiteTurn ? "White" : "Black") + " wins.");
                    break;
                }
                else if (result == 2) {
                    System.out.println(board);
                    System.out.println("STALEMATE!");
                    System.out.println("Game drawn.");
                    break;
                }

                whiteTurn = !whiteTurn;

            } catch (InvalidMove e) {
                System.out.println("Invalid move: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // =========================
    // SAVE GAME (PGN)
    // =========================
    public void saveGame(String filename) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter(filename);
        int moveNumber = 1;

        for (int i = 0; i < moves.size(); i++) {
            if (i % 2 == 0) {
                writer.print(moveNumber + ". ");
                moveNumber++;
            }
            writer.print(moves.get(i) + " ");
        }

        writer.close();
        System.out.println("Game saved to " + filename);
    }

    // =========================
    // LOAD GAME (PGN)
    // =========================
    public void loadGame(String filename) throws FileNotFoundException {

        boolean whiteToMove = true;

        File file = new File(filename);
        if (!file.exists()) throw new FileNotFoundException("File doesn't exist!");
        if (file.length() == 0) throw new FileNotFoundException("File is empty!");

        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {

            String line = reader.nextLine().trim();
            if (line.isEmpty()) continue;
            if (line.startsWith("[")) continue;

            line = line.replaceAll("\\{.*?}", "");
            String[] tokens = line.split("\\s+");

            for (String token : tokens) {

                if (token.matches("\\d+\\.")) continue;

                moves.add(token);

                Coordinates[] move = interpretMove(token, whiteToMove);
                board.makeMove(move[0], move[1], whiteToMove, promotionChoice);
                promotionChoice = ' ';
                whiteToMove = !whiteToMove;
            }
        }

        reader.close();
    }

    // =========================
    // NOTATION INTERPRETER
    // =========================
    public Coordinates[] interpretMove(String notation, boolean whiteTurn) {

        notation = notation.replace("+", "").replace("#", "");

        // Castling
        if (notation.equals("O-O") || notation.equals("0-0")) {
            int row = whiteTurn ? 7 : 0;
            return new Coordinates[]{ new Coordinates(row, 4), new Coordinates(row, 6) };
        }

        if (notation.equals("O-O-O") || notation.equals("0-0-0")) {
            int row = whiteTurn ? 7 : 0;
            return new Coordinates[]{ new Coordinates(row, 4), new Coordinates(row, 2) };
        }

        // Promotion
        if (notation.contains("=")) {
            promotionChoice = notation.charAt(notation.length() - 1);
            notation = notation.substring(0, notation.indexOf("="));
        }

        // Capture
        notation = notation.replace("x", "");

        char file = notation.charAt(notation.length() - 2);
        char rank = notation.charAt(notation.length() - 1);
        Coordinates to = parseSquare(file, rank);

        char pieceChar = Character.isUpperCase(notation.charAt(0)) ? notation.charAt(0) : 'P';

        Character disFile = null;
        Character disRank = null;

        if (notation.length() == 4) {
            char d = notation.charAt(1);
            if (Character.isDigit(d)) disRank = d;
            else disFile = d;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Coordinates from = new Coordinates(i, j);
                Piece p = board.getPiece(from);

                if (p == null || p.isWhite() != whiteTurn) continue;
                if (!matchesPiece(p, pieceChar)) continue;

                if (disFile != null && j != disFile - 'a') continue;
                if (disRank != null && i != 8 - (disRank - '0')) continue;

                char promotion = promotionChoice == ' ' ? 'Q' : promotionChoice;

                if (board.isLegalMove(from, to, whiteTurn, promotion)) {
                    return new Coordinates[]{ from, to };
                }
            }
        }

        throw new InvalidMove("No legal move for notation: " + notation);
    }

    // =========================
    // HELPERS
    // =========================
    private Coordinates parseSquare(char file, char rank) {
        return new Coordinates(8 - (rank - '0'), file - 'a');
    }

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
}
