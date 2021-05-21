package org.jetpack.model.elements.player.playerStates;

public class ImmortalState implements PlayerState {
    @Override
    public int damageTaken() { return 0; }

    @Override
    public int coinTaken() { return 1; }

    @Override
    public long getDuration() {
        return 2000;
    }
}
