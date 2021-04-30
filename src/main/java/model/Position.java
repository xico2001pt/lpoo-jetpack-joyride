package model;

public class Position {
    private final int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getUp() {
        return new Position(x, y - 1);
    }

    public Position getDown() {
        return new Position(x, y + 1);
    }

    public Position getLeft() {
        return new Position(x - 1, y);
    }

    public Position getRight() {
        return new Position(x + 1, y);
    }

    public Position getIncrementedPosition(int xIncrement, int yIncrement) {
        return new Position(x + xIncrement, y + yIncrement);
    }
}
