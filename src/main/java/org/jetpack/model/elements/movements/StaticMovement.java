package org.jetpack.model.elements.movements;

import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;

public class StaticMovement extends MovementStrategy {
    private int counter;

    public StaticMovement() {
        this.counter = 0;
    }

    @Override
    public Position move(Position position, Arena arena) {
        counter++;
        if (counter < 20) return new Position(arena.getWidth() - 1, position.getY());
        else if (counter < 40) return new Position(0, position.getY());
        return new Position(-9999, 0);
    }
}
