package chess;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        char letter = isWhite ? 'B' : 'b';
        super(isWhite, letter);
    }

    public Bishop(Bishop other) {
        super(other);
    }

    @Override
    public Bishop copy() {
        return new Bishop(this);
    }

    @Override
    public boolean regularMovement(Coordinates from, Coordinates to) {
        if(from.getFirst() == to.getFirst() && from.getSecond() == to.getSecond())
            throw new IllegalArgumentException("Invalid coordinates. The coordinates must not be the same");

        if(isWithinBoard(to)) {
            return Math.abs(from.getFirst() - to.getFirst()) ==
                    Math.abs(from.getSecond() - to.getSecond());
        }
        return false;
    }
}