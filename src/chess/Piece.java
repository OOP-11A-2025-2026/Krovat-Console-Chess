package chess;

abstract public class Piece {
    //regularMovement, color (black or white)
    private boolean isWhite;

    Piece(boolean isWhite){
        this.isWhite = isWhite;
    }

    public abstract boolean regularMovement();

    public boolean isWhite() {
        return isWhite;
    }
}
