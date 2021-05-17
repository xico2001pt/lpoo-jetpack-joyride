package org.jetpack.model.elements.movements;

import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;

public class ZigZagMovement extends MovementStrategy {
    private boolean up;
    private int counter;

    public ZigZagMovement() {
        this.up = true;
        this.counter = 0;
    }

    @Override
    public Position move(Position position, Arena arena) {
        counter++;
        if (counter % 5 == 0 || position.getY() == 0 || position.getY() == arena.getHeight() - 1) {
            counter = 0;
            up = !up;
        }
        if (up) return new Position(position.getLeft().getX(), position.getUp().getY());
        else return new Position(position.getLeft().getX(), position.getDown().getY());
    }
}
