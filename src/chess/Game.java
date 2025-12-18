package chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private final ArrayList<String> moves = new ArrayList<>();
    private char promotionChoice = ' ';
    private Board board;
    private String gameResult = "*";

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
            System.out.print("Enter PGN filename to load: ");
            String filename = scanner.nextLine().trim();
            if (filename.isEmpty()) {
                System.out.println("No filename provided.");
                return;
            }
            try {
                loadGame(filename);
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

            if (!gameResult.equals("*")) {
                System.out.println("This game is already finished: " + gameResult);
                break;
            }

            System.out.println((whiteTurn ? "White" : "Black") + "'s turn");
            System.out.println("Options: [1] Move [2] Undo last move  [3] Save  [4] Offer Draw  [5] Resign");
            System.out.print("Choice: ");

            String input = scanner.nextLine().trim();

            // ---- SAVE ----
            if (input.equals("3")) {
                System.out.print("Enter filename (e.g. game.pgn): ");
                String filename = scanner.nextLine().trim();
                if (filename.isEmpty()) {
                    filename = "game.pgn";
                }
                try {
                    saveGame(filename);
                } catch (FileNotFoundException e) {
                    System.out.println("Save failed: " + e.getMessage());
                }
                continue;
            }

            // ---- DRAW ----
            if (input.equals("4")) {
                System.out.print("Opponent, do you accept the draw? (y/n): ");
                String response = scanner.nextLine().trim();
                if (response.equalsIgnoreCase("y")) {
                    System.out.println("Game drawn by agreement.");
                    break;
                } else {
                    System.out.println("Draw declined. Game continues.");
                    continue;
                }
            }

            // ---- RESIGN ----
            if (input.equals("5")) {
                System.out.println((whiteTurn ? "White" : "Black") + " resigns.");
                System.out.println((whiteTurn ? "Black" : "White") + " wins!");
                break;
            }

            // ---- MOVE ----
            if (input.equals("1")) {
                System.out.print("Enter move (e.g. e4, Nf3, O-O): ");
                input = scanner.nextLine().trim();
            }

            // ---- UNDO ----
            if(input.equals("2")) {
                board.undoMove();
                whiteTurn = !whiteTurn;
                continue;
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
        if (!file.exists()) {
            file = new File("src/" + filename);
        }
        if (!file.exists()) {
            file = new File("../src/" + filename);
        }
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
                if (token.matches("\\d+\\.\\.\\.")) continue;
                if (token.matches("1-0|0-1|1/2-1/2")) {
                    switch (token) {
                        case "1-0" -> gameResult = "White wins!";
                        case "0-1" -> gameResult = "Black wins!";
                        case "1/2-1/2" -> gameResult = "Draw!";
                        default -> System.out.println("Invalid outcome!");
                    }
                    return;
                }
                if (token.isEmpty()) continue;

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
        boolean isCapture = notation.contains("x");
        notation = notation.replace("x", "");

        char file = notation.charAt(notation.length() - 2);
        char rank = notation.charAt(notation.length() - 1);
        Coordinates to = parseSquare(file, rank);

        // Find piece type
        char pieceChar = Character.isUpperCase(notation.charAt(0))
                ? notation.charAt(0)
                : 'P';

        // Disambiguation
        Character disFile = null;
        Character disRank = null;

        if (notation.length() == 4) {
            char d = notation.charAt(1);
            if (Character.isDigit(d)) disRank = d;
            else disFile = d;
        }

        // Pawn capture disambiguation (exd5)
        if (pieceChar == 'P' && isCapture) {
            disFile = notation.charAt(0);
        }

        ArrayList<Coordinates> candidates = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordinates from = new Coordinates(i, j);
                Piece p = board.getPiece(from);

                if (p == null) continue;
                if (p.isWhite() != whiteTurn) continue;
                if (!matchesPiece(p, pieceChar)) continue;

                if (disFile != null && j != disFile - 'a') continue;
                if (disRank != null && i != 8 - (disRank - '0')) continue;

                char promotion = promotionChoice == ' ' ? 'Q' : promotionChoice;

                if (board.isLegalMove(from, to, whiteTurn, promotion)) {
                    candidates.add(from);
                }
            }
        }

        if (candidates.isEmpty()) {
            throw new InvalidMove("No legal move found for notation: " + notation);
        }

        if (candidates.size() > 1) {
            throw new InvalidMove("Ambiguous SAN: " + notation);
        }

        return new Coordinates[] { candidates.get(0), to };
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
