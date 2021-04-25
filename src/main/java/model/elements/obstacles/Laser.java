package model.elements.obstacles;

import model.Position;

import java.util.ArrayList;
import java.util.Arrays;

public class Laser extends Obstacle {

    public Laser(Position position) {
        super(position, new ArrayList<>(Arrays.asList(Arrays.asList('C'))));
    }
}
