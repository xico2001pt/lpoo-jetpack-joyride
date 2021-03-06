package org.jetpack.model.elements.player.playerStrategies;

import org.jetpack.model.CharColor;
import org.jetpack.model.Matrix;
import org.jetpack.model.elements.ImageLibrary;

public class NormalStrategy implements PlayerStrategy {
    @Override
    public int damageTaken() { return 1; }

    @Override
    public int coinTaken() { return 1; }

    @Override
    public long getDuration() {
        return Long.MAX_VALUE;
    }

    @Override
    public Matrix<CharColor> getImage() { return ImageLibrary.getPlayerImage(); }
}
