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
        if(from.getFirst() == to.getFirst() && from.getSecond() == to.getSecond())
            throw new IllegalArgumentException("Invalid coordinates. The coordinates must not be the same");

        if(!isWithinBoard(to)) {
            return false;
        }

        int rowDiff = Math.abs(to.getFirst() - from.getFirst());
        int colDiff = Math.abs(to.getSecond() - from.getSecond());

        // Same square
        if (rowDiff == 0 && colDiff == 0) return false;

        // One square forward
        if (colDiff == 0 && rowDiff == 1) return true;

        // Two squares forward (first move only)
        if (!hasMoved && colDiff == 0 && rowDiff == 2) return true;

        // Diagonal capture (Board confirms capture)
        if (Math.abs(colDiff) == 1 && rowDiff == 1) return true;

        return false;
    }
}