package model.elements;

import model.Position;

public class Player extends Element {
    private int lives;
    private int nCoins;

    public Player(Position position) {
        super(position, ImageLibrary.getPlayerImage());
        this.lives = 1;
        this.nCoins = 0;
    }


}
