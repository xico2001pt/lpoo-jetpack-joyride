package org.jetpack.model.elements;

import org.jetpack.model.Position;

public class Player extends Element {
    private int lives;
    private int nCoins;

    public Player(Position position) {
        super(position, ImageLibrary.getPlayerImage());
        this.lives = 3;
        this.nCoins = 0;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getCoins() {
        return nCoins;
    }

    public void setCoins(int coins) {
        nCoins = coins;
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
