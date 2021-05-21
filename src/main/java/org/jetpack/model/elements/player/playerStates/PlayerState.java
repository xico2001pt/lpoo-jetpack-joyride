package org.jetpack.model.elements.player.playerStates;

public interface PlayerState {
    int damageTaken();
    int coinTaken();
    long getDuration();
}
