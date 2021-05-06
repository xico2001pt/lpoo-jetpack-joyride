package model.elements.obstacles;

import model.Position;
import model.elements.ImageLibrary;

public class Laser extends Obstacle {

    public Laser(Position position) {
        super(position, ImageLibrary.getLaser2Image());
    }
}
