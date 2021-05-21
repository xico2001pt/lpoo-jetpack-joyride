package org.jetpack.model.elements.movements;

import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;

public class DownMovement extends MovementStrategy {

    @Override
    public Position move(Position position, Arena arena) {
        return position.getDown();
    }
}
