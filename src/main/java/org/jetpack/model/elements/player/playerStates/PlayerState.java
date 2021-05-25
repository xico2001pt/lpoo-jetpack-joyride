package org.jetpack.model.elements.player.playerStates;

import org.jetpack.model.CharColor;
import org.jetpack.model.Matrix;

public interface PlayerState {
    int damageTaken();
    int coinTaken();
    long getDuration();
    Matrix<CharColor> getImage();
}
