package model.elements;

import model.Position;

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
}
