package org.jetpack.model.elements.movements;

import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;

public abstract class MovementStrategy {

    public MovementStrategy() {}

    public abstract Position move(Position position, Arena arena);
}
