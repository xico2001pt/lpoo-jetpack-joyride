package org.jetpack.model.elements.player.playerStrategies;

import org.jetpack.model.CharColor;
import org.jetpack.model.Matrix;

public interface PlayerStrategy {
    int damageTaken();
    int coinTaken();
    long getDuration();
    Matrix<CharColor> getImage();
}
