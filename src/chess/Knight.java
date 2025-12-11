package chess;

public class Knight extends Piece {

    public Knight(boolean isWhite, char letter) {
        if(letter != 'N' && letter != 'n') throw new IllegalArgumentException("Invalid symbol. N - White Knight and n - Black Knight");
        super(isWhite, letter);
    }

    @Override
    public boolean regularMovement(Coordinates from, Coordinates to) {
        int dx = Math.abs(to.getFirst() - from.getFirst());
        int dy = Math.abs(to.getSecond() - from.getSecond());

        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
