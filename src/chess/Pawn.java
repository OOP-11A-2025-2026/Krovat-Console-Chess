package chess;

public class Pawn extends Piece {
    // Fill free to change the names. I just needed some for the copy constructor
    // If you don't need them remove them from the object and copy constructor
    private boolean hasMoved = false;
    private boolean enPassantEligible = false;

    // copy constructor
    public Pawn(Pawn other) {
        super(other);
        this.hasMoved = other.hasMoved;
        this.enPassantEligible = other.enPassantEligible;
    }

    @Override
    public Pawn copy() {
        return new Pawn(this);
    }
}
