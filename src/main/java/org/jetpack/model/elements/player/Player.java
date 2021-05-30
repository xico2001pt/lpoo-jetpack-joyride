package org.jetpack.model.elements.player;

import org.jetpack.model.Position;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.ImageLibrary;
import org.jetpack.model.elements.movements.DownMovement;
import org.jetpack.model.elements.player.playerStrategies.*;

public class Player extends Element {
    private static final int POWER_UP_COST = 10;
    private int lives;
    private int nCoins;
    private PlayerStrategy strategy;

    public Player(Position position) {
        super(position, ImageLibrary.getPlayerImage());
        this.lives = 3;
        this.nCoins = 0;
        this.strategy = new NormalStrategy();
        setMovement(new DownMovement());
    }

    public int getLives() {
        return lives;
    }

    public void takeDamage() {
        lives -= strategy.damageTaken();
    }

    public int getCoins() {
        return nCoins;
    }

    public void addCoin() {
        nCoins += strategy.coinTaken();
    }

    public boolean buyPowerUp() {
        if (nCoins < POWER_UP_COST) return false;
        nCoins -= POWER_UP_COST;
        return true;
    }

    public void setStrategy(PlayerStrategy state) {
        setImage(state.getImage());
        this.strategy = state;
    }
    
    public PlayerStrategy getStrategy() {
        return this.strategy;
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
