package model.elements;

import model.Position;

import java.util.ArrayList;
import java.util.Arrays;

public class Player extends Element {
    private int lives;
    private int nCoins;

    public Player(Position position) {
        super(position, new ArrayList<>(Arrays.asList(Arrays.asList('C'))));
        this.lives = 1;
        this.nCoins = 0;
    }


}
