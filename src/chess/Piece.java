package chess;

abstract public class Piece {
    //regularMovement, color (black or white)
    private final boolean isWhite;
    private final char symbol;

    Piece(boolean isWhite, char symbol) {
        this.isWhite = isWhite;
        this.symbol = symbol;
    }

    public abstract boolean regularMovement(Coordinates from, Coordinates to);

    public boolean isWhite() {
        return isWhite;
    }

    public char getSymbol() {
        return symbol;
    }
}
