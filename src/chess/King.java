package chess;

public class King extends Piece {

    public King(boolean isWhite, char letter) {
        if(letter != 'K' && letter != 'k') throw new IllegalArgumentException("Invalid symbol. K - White King and k - Black King");
        super(isWhite, letter);
    }

    @Override
    public boolean regularMovement(Coordinates from, Coordinates to) {
        int dx = Math.abs(to.getFirst() - from.getFirst());
        int dy = Math.abs(to.getSecond() - from.getSecond());

        return (dx <= 1 && dy <= 1);
    }
}
