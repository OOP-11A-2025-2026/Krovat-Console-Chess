package chess;

public class Queen extends Piece {

    Queen(boolean isWhite, char letter) {
        if(letter != 'q' && letter != 'Q') throw new IllegalArgumentException("Invalid symbol. Q - White Queen and q - Black Queen");
        super(isWhite, letter);
    }

    @Override
    public boolean regularMovement(Coordinates from, Coordinates to) {
        if(from.getFirst() == to.getFirst() && from.getSecond() == to.getSecond())
            throw new IllegalArgumentException("Invalid coordinates. The coordinates must not be the same");
        if(from.getFirst() == to.getFirst() || from.getSecond() == to.getSecond())
            return true; // Up and down movement
        if(Math.abs(from.getSecond() - to.getSecond()) == Math.abs(from.getFirst() - to.getFirst()))
            return true; // Diagonal movement

        return false;
    }
}
