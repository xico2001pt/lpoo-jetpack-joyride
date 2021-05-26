package org.jetpack.model.elements.player;

import org.jetpack.model.Position;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.ImageLibrary;
import org.jetpack.model.elements.movements.DownMovement;
import org.jetpack.model.elements.player.playerStates.*;

public class Player extends Element {
    private static final int POWER_UP_COST = 10;
    private int lives;
    private int nCoins;
    private PlayerState state;

    public Player(Position position) {
        super(position, ImageLibrary.getPlayerImage());
        this.lives = 3;
        this.nCoins = 0;
        this.state = new NormalState();
        setMovement(new DownMovement());
    }

    public int getLives() {
        return lives;
    }

    public void takeDamage() {
        lives -= state.damageTaken();
    }

    public int getCoins() {
        return nCoins;
    }

    public void addCoin() {
        nCoins += state.coinTaken();
    }

    public boolean buyPowerUp() {
        if (nCoins < POWER_UP_COST) return false;
        nCoins -= POWER_UP_COST;
        return true;
    }

    public void setState(PlayerState state) {
        setImage(state.getImage());
        this.state = state;
    }
    public PlayerState getState() {
        return this.state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return lives == player.lives && nCoins == player.nCoins;
    }
}
