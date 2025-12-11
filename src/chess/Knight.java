package chess;

public class Knight extends Piece {

    public Knight(boolean isWhite) {
        char letter = isWhite ? 'N' : 'n';
        super(isWhite, letter);
    }

    // Copy constructor
    // If you have any mutable fields add them to it
    public Knight(Knight other) {
        super(other);
    }

    @Override
    public Knight copy() {
        return new Knight(this);
    }

    @Override
    public boolean regularMovement(Coordinates from, Coordinates to) {
        int dx = Math.abs(to.getFirst() - from.getFirst());
        int dy = Math.abs(to.getSecond() - from.getSecond());

        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
