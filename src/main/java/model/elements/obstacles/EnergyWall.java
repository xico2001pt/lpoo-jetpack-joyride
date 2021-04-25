package model.elements.obstacles;

import model.Matrix;
import model.Position;

import java.util.ArrayList;
import java.util.Arrays;

public class EnergyWall extends Obstacle {

    public EnergyWall(Position position) {
        super(position, new Matrix<Character>(1, 1));
    }
}
