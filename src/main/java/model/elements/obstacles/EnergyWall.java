package model.elements.obstacles;

import model.Position;
import model.elements.ImageLibrary;

public class EnergyWall extends Obstacle {

    public EnergyWall(Position position) {
        super(position, ImageLibrary.getEnergyWall1Image());
    }
}
