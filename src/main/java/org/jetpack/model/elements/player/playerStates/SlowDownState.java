package org.jetpack.model.elements.player.playerStates;

import org.jetpack.model.CharColor;
import org.jetpack.model.Matrix;
import org.jetpack.model.elements.ImageLibrary;

public class SlowDownState implements PlayerState {
    @Override
    public int damageTaken() { return 1; }

    @Override
    public int coinTaken() { return 1; }

    @Override
    public long getDuration() { return 10000; }

    @Override
    public Matrix<CharColor> getImage() { return ImageLibrary.getPlayerSlowDownStateImage(); }
}
