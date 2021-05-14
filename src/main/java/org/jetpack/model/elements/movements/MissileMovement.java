package org.jetpack.model.elements.movements;

import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;

public class MissileMovement extends MovementStrategy {

    public Position move(Position position, Arena arena) {
        if (position.getX() >= arena.getWidth()/2)
            return new Position(position.getLeft().getX(), arena.getPlayer().getPosition().getY());
        return position.getLeft();
    }
}
