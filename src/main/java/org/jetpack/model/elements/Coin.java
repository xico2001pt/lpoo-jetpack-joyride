package org.jetpack.model.elements;

import org.jetpack.model.Position;

public class Coin extends Element {

    public Coin(Position position) {
        super(position, ImageLibrary.getCoinImage());
    }
}
