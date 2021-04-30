package model.elements.obstacles;

import model.Matrix;
import model.Position;

public class EnergyWall extends Obstacle {

    public EnergyWall(Position position) {
        super(position, new Matrix<Character>(1, 1));
    }
}
