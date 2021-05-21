package org.jetpack.model.elements.obstacles;

import org.jetpack.model.Position;
import org.jetpack.model.elements.ImageLibrary;
import org.jetpack.model.elements.movements.StaticMovement;

import java.awt.*;

public class StaticLaser extends Obstacle {

    public StaticLaser(Position position, Dimension dimension) {
        super(position, ImageLibrary.getStaticLaserImage(dimension));
        setMovement(new StaticMovement());
    }
}
