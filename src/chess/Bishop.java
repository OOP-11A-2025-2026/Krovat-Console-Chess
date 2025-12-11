package chess;

public class Bishop extends Piece {

    // Copy constructor
    // If you have any mutable fields add them to it
    public Bishop(Bishop other) {
        super(other);
    }

    @Override
    public Bishop copy() {
        return new Bishop(this);
    }
}
