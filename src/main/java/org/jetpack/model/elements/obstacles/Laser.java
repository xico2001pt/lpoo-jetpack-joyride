package org.jetpack.model.elements.obstacles;

import org.jetpack.model.Position;
import org.jetpack.model.elements.ImageLibrary;

public class Laser extends Obstacle {

    public Laser(Position position) {
        super(position, ImageLibrary.getLaserImage());
    }
}
