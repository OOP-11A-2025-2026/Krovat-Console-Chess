package chess;

public class Rook extends Piece {
    private boolean hasMoved;

    public Rook(boolean isWhite) {
        super(isWhite, isWhite ? 'R' : 'r');
    }

    public Rook(Rook other) {
        super(other);
    }

    @Override
    public Rook copy() {
        return new Rook(this);
    }

    @Override
    public boolean regularMovement(Coordinates from, Coordinates to) {
        if(from.getFirst() == to.getFirst() && from.getSecond() == to.getSecond())
            throw new IllegalArgumentException("Invalid coordinates. The coordinates must not be the same");

        if(from.getFirst() == to.getFirst()) return true;     // движение по ред
        if(from.getSecond() == to.getSecond()) return true;   // движение по колона

        return false;
    }

    public boolean hasMoved() { return hasMoved; }
    public void setHasMoved(boolean moved) { this.hasMoved = moved; }
}