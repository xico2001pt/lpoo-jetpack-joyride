package org.jetpack.model.elements.obstacles;

import org.jetpack.model.Position;
import org.jetpack.model.elements.ImageLibrary;
import org.jetpack.model.elements.movements.ZigZagMovement;

public class ZigZag extends Obstacle {

    public ZigZag(Position position) {
        super(position, ImageLibrary.getZigZagImage());
        setMovement(new ZigZagMovement());
    }
}
