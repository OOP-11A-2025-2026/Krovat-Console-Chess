package chess;

public class King extends Piece {

    public King(boolean isWhite, char letter) {
        super(isWhite);
        if(letter != 'K' || letter != 'k') throw new IllegalArgumentException("K - White King\nk - Black King");
        this.letter = letter;
    }

    @Override
    public boolean regularMovement(Coordinates from, Coordinates to) {
        int dx = Math.abs(to.getFirst() - from.getFirst());
        int dy = Math.abs(to.getSecond() - from.getSecond());

        return (dx <= 1 && dy <= 1);
    }
}
