package model.elements.obstacles;

import model.Position;

import java.util.ArrayList;
import java.util.Arrays;

public class EnergyWall extends Obstacle {

    public EnergyWall(Position position) {
        super(position, new ArrayList<>(Arrays.asList(Arrays.asList('C'))));
    }
}
