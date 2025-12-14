package chess;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        char letter = isWhite ? 'Q' : 'q';
        super(isWhite, letter);
    }

    public Queen(Queen other) {
        super(other);
    }

    @Override
    public Queen copy() {
        return new Queen(this);
    }

    @Override
    public boolean regularMovement(Coordinates from, Coordinates to) {
        if(from.getFirst() == to.getFirst() && from.getSecond() == to.getSecond())
            throw new IllegalArgumentException("Invalid coordinates. The coordinates must not be the same");
        if(isWithinBoard(to)) {
            if(from.getFirst() == to.getFirst() || from.getSecond() == to.getSecond())
                return true; // Up and down movement
            if(Math.abs(from.getSecond() - to.getSecond()) == Math.abs(from.getFirst() - to.getFirst()))
                return true; // Diagonal movement
        }

        return false;
    }
}
