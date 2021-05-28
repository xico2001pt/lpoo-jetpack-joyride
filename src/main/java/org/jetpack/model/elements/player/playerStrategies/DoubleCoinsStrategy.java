package org.jetpack.model.elements.player.playerStrategies;

import org.jetpack.model.CharColor;
import org.jetpack.model.Matrix;
import org.jetpack.model.elements.ImageLibrary;

public class DoubleCoinsStrategy implements PlayerStrategy {
    @Override
    public int damageTaken() { return 1; }

    @Override
    public int coinTaken() { return 2; }

    @Override
    public long getDuration() {
        return 10000;
    }

    @Override
    public Matrix<CharColor> getImage() { return ImageLibrary.getPlayerDoubleCoinStateImage(); }
}
