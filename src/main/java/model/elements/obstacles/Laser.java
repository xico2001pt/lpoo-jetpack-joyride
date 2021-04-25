package model.elements.obstacles;

import model.Matrix;
import model.Position;

import java.util.ArrayList;
import java.util.Arrays;

public class Laser extends Obstacle {

    public Laser(Position position) {
        super(position, new Matrix<Character>(1, 1));
    }
}
