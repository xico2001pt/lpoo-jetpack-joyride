package org.jetpack.model.elements.movements;

import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;

public class LinearMovement extends MovementStrategy {

    public Position move(Position position, Arena arena) {
        return position.getLeft();
    }
}
