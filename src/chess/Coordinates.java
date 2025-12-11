package chess;

public class Coordinates {
    private int first;
    private int second;

    public Coordinates(int first, int second) {
        if(first < 0 || first > 7 || second < 0 || second > 7) throw new IllegalArgumentException("Invalid coordinates");
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public void setFirst(int first) {
        if(first < 0 || first > 7) throw new IllegalArgumentException("Invalid coordinates");
        this.first = first;
    }

    public void setSecond(int second) {
        if(second < 0 || second > 7) throw new IllegalArgumentException("Invalid coordinates");
        this.second = second;
    }
}
