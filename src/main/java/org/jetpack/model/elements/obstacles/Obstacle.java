package org.jetpack.model.elements.obstacles;

import org.jetpack.model.Matrix;
import org.jetpack.model.Position;
import org.jetpack.model.elements.Element;

public abstract class Obstacle extends Element {

    public Obstacle(Position position, Matrix<Character> image) {
        super(position, image);
    }
}
