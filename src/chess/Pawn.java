package chess;

public class Pawn extends Piece {
    private boolean hasMoved;
    private boolean enPassantEligible;

    public Pawn(boolean isWhite) {
        char letter = isWhite ? 'P' : 'p';
        super(isWhite, letter);
        this.hasMoved = false;
        this.enPassantEligible = false;
    }
    
    // Copy constructor
    public Pawn(Pawn other) {
        super(other);
        this.hasMoved = other.hasMoved;
        this.enPassantEligible = other.enPassantEligible;
    }

    @Override
    public Pawn copy() {
        return new Pawn(this);
    }
    
    // Accessors for Board logic
    public boolean getHasMoved() { return hasMoved; }
    public void setHasMoved(boolean moved) { this.hasMoved = moved; }
    public boolean isEnPassantEligible() { return enPassantEligible; }
    public void setEnPassantEligible(boolean enPassantEligible) { this.enPassantEligible = enPassantEligible; }

    @Override
public boolean regularMovement(Coordinates from, Coordinates to) {

    int direction = isWhite() ? -1 : 1;
    int rowDiff = to.getFirst() - from.getFirst();
    int colDiff = to.getSecond() - from.getSecond();

    // Same square
    if (rowDiff == 0 && colDiff == 0) return false;

    // One square forward
    if (colDiff == 0 && rowDiff == direction) return true;

    // Two squares forward (first move only)
    if (!hasMoved && colDiff == 0 && rowDiff == 2 * direction) return true;

    // Diagonal capture (Board confirms capture)
    if (Math.abs(colDiff) == 1 && rowDiff == direction) return true;

    return false;
}

}