package org.jetpack.model.elements.obstacles;

import org.jetpack.model.Position;
import org.jetpack.model.elements.ImageLibrary;
import org.jetpack.model.elements.movements.MissileMovement;

public class Missile extends Obstacle {

    public Missile(Position position) {
        super(position, ImageLibrary.getMissileImage());
        setMovement(new MissileMovement());
    }
}

