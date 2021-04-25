package model.elements;

import model.Position;

import java.util.ArrayList;
import java.util.Arrays;

public class Coin extends Element {

    public Coin(Position position) {
        super(position, new ArrayList<>(Arrays.asList(Arrays.asList('C'))));
    }
}
