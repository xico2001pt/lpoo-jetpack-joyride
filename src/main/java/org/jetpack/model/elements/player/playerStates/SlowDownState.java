package org.jetpack.model.elements.player.playerStates;

public class SlowDownState implements PlayerState {
    @Override
    public int damageTaken() { return 1; }

    @Override
    public int coinTaken() { return 1; }

    @Override
    public long getDuration() { return 4000; }
}
