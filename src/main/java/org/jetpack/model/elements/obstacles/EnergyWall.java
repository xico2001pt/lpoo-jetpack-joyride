package org.jetpack.model.elements.obstacles;

import org.jetpack.model.Position;
import org.jetpack.model.elements.ImageLibrary;

public class EnergyWall extends Obstacle {

    public EnergyWall(Position position) {
        super(position, ImageLibrary.getEnergyWall1Image());
    }
}
