package chess;

abstract public class Piece {
    //regularMovement, color (black or white)
    private final boolean isWhite;
    private final char symbol;

    public Piece(boolean isWhite, char symbol) {
        this.isWhite = isWhite;
        this.symbol = symbol;
    }

    // copy constructor
    public Piece(Piece other) {
        this.isWhite = other.isWhite;
        this.symbol = other.symbol;
    }

    public abstract Piece copy();

    public abstract boolean regularMovement(Coordinates from, Coordinates to);

    public boolean isWhite() {
        return isWhite;
    }

    public char getSymbol() {
        return symbol;
    }
}
