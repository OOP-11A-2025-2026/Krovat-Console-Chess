import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import chess.*;

public class TestPGN {

    public static void main(String[] args) throws IOException {
        Path pgnPath = Path.of("Krovat-Console-Chess/tests/Anand.pgn"); // your attached file

        if (!Files.exists(pgnPath)) {
            System.err.println("PGN file not found: " + pgnPath.toAbsolutePath());
            return;
        }

        try {
            String fullPgn = Files.readString(pgnPath);
            List<String> games = splitIntoGames(fullPgn);

            int passed = 0;
            int failed = 0;

            for (int i = 0; i < games.size(); i++) {
                String gamePgn = games.get(i);
                Path tempFile = null;

                try {
                    // Write one game to a temp PGN file
                    tempFile = Files.createTempFile("pgn_test_game_" + i, ".pgn");
                    Files.writeString(tempFile, gamePgn);

                    Game game = new Game();
                    game.loadGame(tempFile.toString());

                    passed++;
                } catch (Exception e) {
                    failed++;
                    System.err.println("Exception in game #" + (i + 1));
                    e.printStackTrace();
                } finally {
                    // Ensure temp file is always released
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
            System.err.println("Failed to read PGN file:");
            e.printStackTrace();
        }
    }


     // Splits a PGN file containing multiple games into individual game PGNs.
     // Metadata is preserved (Game.loadGame later ignores it).
     private static List<String> splitIntoGames(String pgn) {
        List<String> games = new ArrayList<>();

        // Each PGN game starts with an [Event ...] tag
        String[] rawGames = pgn.split("(?=\\[Event )");

        for (String g : rawGames) {
            g = g.trim();
            if (g.isEmpty()) continue;

            // Only keep finished games
            if (g.contains("1-0") || g.contains("0-1") || g.contains("1/2-1/2")) {
                games.add(g);
            }
        }

        return games;
    }
}
