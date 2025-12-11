package chess;

public class King extends Piece {

    public King(boolean isWhite) {
        char letter = isWhite ? 'K' : 'k';
        super(isWhite, letter);
    }

    // Copy constructor
    // If you have any mutable fields add them to it
    public King(King other) {
        super(other);
    }

    @Override
    public King copy() {
        return new King(this);
    }

    @Override
    public boolean regularMovement(Coordinates from, Coordinates to) {
        int dx = Math.abs(to.getFirst() - from.getFirst());
        int dy = Math.abs(to.getSecond() - from.getSecond());

        return (dx <= 1 && dy <= 1);
    }
}
