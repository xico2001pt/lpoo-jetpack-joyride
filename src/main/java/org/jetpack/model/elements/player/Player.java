package org.jetpack.model.elements.player;

import org.jetpack.model.Position;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.ImageLibrary;
import org.jetpack.model.elements.movements.DownMovement;
import org.jetpack.model.elements.player.playerStates.NormalState;
import org.jetpack.model.elements.player.playerStates.PlayerState;

public class Player extends Element {
    private int lives;
    private int nCoins;
    private PlayerState state;

    public Player(Position position) {
        super(position, ImageLibrary.getPlayerImage());
        this.lives = 3;
        this.nCoins = 0;
        setMovement(new DownMovement());
        this.state = new NormalState();
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

    public void setState(PlayerState state) {
        this.state = state;
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
