import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import chess.*;

// Stress tests a .pgn file with thousands of games

public class TestPGN {

    public static void main(String[] args) throws IOException {
        Path pgnPath = Path.of("Krovat-Console-Chess/tests/Anand.pgn"); // Path to the file to test

        // Check if a file exists
        if (!Files.exists(pgnPath)) {
//            System.out.println("PGN file not found: " + pgnPath.toAbsolutePath());
            throw new FileNotFoundException("PGN file not found: " + pgnPath.toAbsolutePath());
        }

        try {
            // Reads all content from a file into a String and splits all the games
            String fullPgn = Files.readString(pgnPath);
            List<String> games = splitIntoGames(fullPgn);

            // To keep track of how many tests passes or not
            int passed = 0;
            int failed = 0;

            for (int i = 0; i < games.size(); i++) {
                String gamePgn = games.get(i);
                Path tempFile = null;

                try {
                    // Write one game to a temp PGN file
                    tempFile = Files.createTempFile("pgn_test_game_" + i, ".pgn");
                    Files.writeString(tempFile, gamePgn);

                    // Load the game to see if it throws any exceptions
                    Game game = new Game();
                    game.loadGame(tempFile.toString());

                    // Didn't throw an exception so it passed
                    passed++;
                } catch (Exception e) {
                    // Exception thrown so it failed
                    failed++;
                    System.out.println("Exception in game #" + (i + 1));
                } finally {
                    // To ensure temp file is always removed
                    if (tempFile != null) {
                        try {
                            Files.deleteIfExists(tempFile);
                        } catch (IOException ignored) {
                        }
                    }
                }
            }

            System.out.println("\n===== PGN STRESS TEST SUMMARY =====");
            System.out.println("Total games: " + games.size());
            System.out.println("Passed: " + passed);
            System.out.println("Failed: " + failed);

        } catch (IOException e) {
//            System.out.println("Failed to read PGN file:");
            throw new IOException("Failed to read PGN file: " + e.getMessage());
        }
    }


     // Splits a String PGN file containing multiple games into individual String game PGNs.
     // Metadata is preserved (Game.loadGame later ignores it)
     private static List<String> splitIntoGames(String pgn) {
        List<String> games = new ArrayList<>();

        // Each PGN game starts with an [Event ...] meta tag
        String[] rawGames = pgn.split("(?=\\[Event )");

        for (String game : rawGames) {
            game = game.trim();
            if (game.isEmpty()) continue;

            // Only keep finished games to test
            // There probably won't be any problems to test unfinished games, but we decided to test only finished ones
            if (game.contains("1-0") || game.contains("0-1") || game.contains("1/2-1/2")) {
                games.add(game);
            }
        }

        return games;
    }
}
