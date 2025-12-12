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
        int direction = isWhite() ? -1 : 1; // -1 for White (up), +1 for Black (down)
        int rowDiff = to.getFirst() - from.getFirst();
        int colDiff = Math.abs(to.getSecond() - from.getSecond());

        if (rowDiff == 0 && colDiff == 0) return false;

        // One-Step Forward
        if (colDiff == 0 && rowDiff == direction) {
            return true;
        }

        // two-Step Forward
        if (!hasMoved && colDiff == 0 && rowDiff == 2 * direction) {
            return true;
        }

        // Diagonal Move
        // The pawn should only move diagonally if it takes another pawn
        // We will have to figure out some solution for that probably in Board
        if (colDiff == 1 && rowDiff == direction) {
            return true;
        }
        
        return false;
    }
}