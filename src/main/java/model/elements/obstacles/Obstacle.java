package model.elements.obstacles;

import model.Matrix;
import model.Position;
import model.elements.Element;

public abstract class Obstacle extends Element {

    public Obstacle(Position position, Matrix<Character> image) {
        super(position, image);
    }
}
