package chess;

public class Rook extends Piece {
    // Fill free to change the names. I just needed some for the copy constructor
    // If you don't need them remove them from the object and copy constructor
    private boolean hasMoved = false;

    // copy constructor
    public Rook(Rook other) {
        super(other);
        this.hasMoved = other.hasMoved;
    }

    @Override
    public Rook copy() {
        return new Rook(this);
    }
}
