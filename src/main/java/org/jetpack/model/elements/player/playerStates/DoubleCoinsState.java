package org.jetpack.model.elements.player.playerStates;

public class DoubleCoinsState implements PlayerState {
    @Override
    public int damageTaken() { return 1; }

    @Override
    public int coinTaken() { return 2; }

    @Override
    public long getDuration() {
        return 2000;
    }
}
