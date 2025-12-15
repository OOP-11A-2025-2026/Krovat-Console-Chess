package chess;

public class King extends Piece {
    private boolean hasMoved;

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
        if(from.getFirst() == to.getFirst() && from.getSecond() == to.getSecond())
            throw new IllegalArgumentException("Invalid coordinates. The coordinates must not be the same");

        if(isWithinBoard(to)) {
            int dx = Math.abs(to.getFirst() - from.getFirst());
            int dy = Math.abs(to.getSecond() - from.getSecond());
            return dx <= 1 && dy <= 1 || dx * dy == 1;
        }
        return false;
    }

    public boolean hasMoved() { return hasMoved; }
    public void setHasMoved(boolean moved) { this.hasMoved = moved; }
}
