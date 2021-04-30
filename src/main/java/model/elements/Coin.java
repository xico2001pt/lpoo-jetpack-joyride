package model.elements;

import model.Matrix;
import model.Position;

public class Coin extends Element {

    public Coin(Position position) {
        super(position, ImageLibrary.getCoinImage());
    }
}
